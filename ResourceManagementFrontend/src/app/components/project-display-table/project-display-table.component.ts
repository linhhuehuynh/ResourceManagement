import { Component, OnInit } from '@angular/core';
import { ProjectDisplayService } from './project-display.service';

@Component({
  selector: 'app-project-display-table',
  templateUrl: './project-display-table.component.html',
  styleUrls: ['./project-display-table.component.css']
})
export class ProjectDisplayTableComponent implements OnInit {

  private projectId = 1;

  constructor(private projectDisplayService: ProjectDisplayService) { }

  ngOnInit(): void {
    this.projectDisplayService.getProjectRowList(this.projectId);
    this.projectDisplayService.getProjectColList(this.projectId);
    
  }

  loadingData() {
    this.projectDisplayService.getProjectItemList();
  }

}
