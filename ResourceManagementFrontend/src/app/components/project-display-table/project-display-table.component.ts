import { Component, OnInit } from '@angular/core';
import { ProjectColumn } from 'src/app/model/project-col.model';
import { ProjectItem } from 'src/app/model/project-item.model';
import { ProjectRow } from 'src/app/model/project-row.model';
import { ProjectDisplayService } from './project-display.service';
import { ProjectRowDisplay } from './project-row-display.model';

@Component({
  selector: 'app-project-display-table',
  templateUrl: './project-display-table.component.html',
  styleUrls: ['./project-display-table.component.css']
})
export class ProjectDisplayTableComponent implements OnInit {

  private projectId = 1;

  projectRowDisplayList: ProjectRowDisplay[];
  projectColList: ProjectColumn[];
  projectRowList: ProjectRow[];
  word = "sdsds";

  constructor(private projectDisplayService: ProjectDisplayService) { }

  ngOnInit(): void {
    this.projectDisplayService.getProjectColList(this.projectId).then(data => {this.projectColList = data});
    this.projectDisplayService.getProjectRowList(this.projectId)
    .then(data => {
      this.projectRowList = data;
      this.projectRowDisplayList = this.projectDisplayService.getProjectItemList();
    });
    
  }

  save() {
    this.projectRowDisplayList = this.projectDisplayService.saveChangedItems(this.projectRowDisplayList);
  }

  itemUpdated(item: ProjectItem) {
    console.log(item);
    item.changed = true;
  }
  onEnter(item: ProjectItem) {
    console.log(item);
    item.changed = true;
  }
}