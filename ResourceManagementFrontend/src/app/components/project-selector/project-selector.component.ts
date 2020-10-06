import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/model/project.model';
import { ProjectSelectorService } from './project-selector.service';
import { Subscription } from 'rxjs';
import { AuthService } from './../auth/auth.service';

@Component({
  selector: 'app-project-selector',
  templateUrl: './project-selector.component.html',
  styleUrls: ['./project-selector.component.css']
})
export class ProjectSelectorComponent implements OnInit {
  isLoading = false;
  private authStatusSub: Subscription;

  projectList: Project[];
  projectListMap: Map<number, Project>
  selectedProject: Project;
  selectedProjectId: number;

  constructor(private projectSelectorService: ProjectSelectorService, private authService: AuthService) { }

  async ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    );

    this.projectListMap = new Map();
    await this.projectSelectorService.getProjectList()
    .then(data => {
        this.projectList = data;
        for(let pro of this.projectList) {
          this.projectListMap.set(pro.id, pro);
        }
    });

    this.projectSelectorService.selectedProjectIdObservable.subscribe(id => {
      this.selectedProjectId = id;
    });
    this.projectSelectorService.selectedProjectIdObservable.subscribe(id => {
      this.selectedProject = this.projectListMap.get(id);
    });

    
  }

  onChangeProject() {
    this.projectSelectorService.setSelectedProjectId(this.selectedProject.id);
  }

}
