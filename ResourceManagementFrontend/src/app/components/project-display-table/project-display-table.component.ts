import { Component, OnInit } from '@angular/core';
import { ProjectColumn } from 'src/app/model/project-col.model';
import { ProjectItem } from 'src/app/model/project-item.model';
import { ProjectRow } from 'src/app/model/project-row.model';
import { ProjectDisplayService } from './project-display.service';
import { ProjectRowDisplay } from './project-row-display.model';
import { Subscription } from 'rxjs';
import { AuthService } from './../auth/auth.service';
import { ProjectSelectorService } from '../project-selector/project-selector.service';

@Component({
  selector: 'app-project-display-table',
  templateUrl: './project-display-table.component.html',
  styleUrls: ['./project-display-table.component.css']
})
export class ProjectDisplayTableComponent implements OnInit {
  isLoading = false;
  private authStatusSub: Subscription;

  selectedProjectId: number;
  projectRowDisplayList: ProjectRowDisplay[];
  projectColList: ProjectColumn[];
  projectRowList: ProjectRow[];

  constructor(private projectDisplayService: ProjectDisplayService, private projectSelectorService: ProjectSelectorService, private authService: AuthService) { }

  ngOnInit(): void {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )
    
    this.isLoading=true;
    this.projectSelectorService.selectedProjectIdObservable.subscribe(id => {
      this.selectedProjectId = id;
      if(!this.projectDisplayService.tempelateChange) {
        this.projectDisplayService.loaded = false;
      }
      if(!this.projectDisplayService.loaded) {
        this.projectDisplayService.getProjectColList(this.selectedProjectId).then(data => {this.projectColList = data});
      } else {
        this.projectColList = this.projectDisplayService.getLoadedProjectColumnList();
      }
      this.projectDisplayService.getProjectRowList(this.selectedProjectId)
      .then(data => {
        this.projectRowList = data;
        if(!this.projectDisplayService.loaded) {
          this.projectRowDisplayList = this.projectDisplayService.getProjectItemList();
          this.projectDisplayService.loaded = true;
        } else {
          this.projectDisplayService.getLoadedProjectRowDisplayList().then(res => {
            this.projectRowDisplayList = res;
            this.projectDisplayService.tempelateChange = false;
          });
        }
      });
    });
    
    
    
  }

  save() {
    this.projectRowDisplayList = this.projectDisplayService.saveChangedItems(this.projectRowDisplayList);
  }

  itemUpdated(item: ProjectItem) {
    if(!item.changed) {
      item.changed = true;
    }
  }
}