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
    if(!this.projectDisplayService.loaded) {
      this.isLoading=true;
      this.projectSelectorService.selectedProjectIdObservable.subscribe(id => {
        this.selectedProjectId = id;
        this.projectDisplayService.getProjectColList(this.selectedProjectId).then(data => {this.projectColList = data});
        this.projectDisplayService.getProjectRowList(this.selectedProjectId)
        .then(data => {
          this.projectRowList = data;
          this.projectRowDisplayList = this.projectDisplayService.getProjectItemList();
        });
      });
      
      this.projectDisplayService.loaded = true;
    } else {
      this.isLoading=true;
      this.projectColList = this.projectDisplayService.getLoadedProjectColumnList();
      this.projectRowList = this.projectDisplayService.getLoadedProjectRowList();
      this.projectRowDisplayList = this.projectDisplayService.getLoadedProjectRowDisplayList();
      console.log("Not getting data");
    }
  }

  save() {
    this.projectRowDisplayList = this.projectDisplayService.saveChangedItems(this.projectRowDisplayList);
  }

  itemUpdated(item: ProjectItem) {
    if(!item.changed) {
      item.changed = true;
    }
  }

  cols() {
    console.log(this.projectColList);
  }
  rows() {
    console.log(this.projectRowList);
  }
  items() {
    console.log(this.projectRowDisplayList);
  }
}