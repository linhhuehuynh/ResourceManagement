import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { Project } from 'src/app/model/project.model';
import { ProjectRow } from 'src/app/model/project-row.model';
import { ProjectColumn } from 'src/app/model/project-col.model';
import { ProjectItem } from 'src/app/model/project-item.model';
import {environment} from '../../../environments/environment';
import { HttpHeaders } from '@angular/common/http';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ProjectDisplayService {

  jwt = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMzMiLCJleHAiOjE2MDE1MTc5ODksImlhdCI6MTYwMTQ4MTk4OX0.oa7QhLkaImI5fgRUSkxGfNAjt-BtM7oD3Jf-kpt-ZsY';

  header = new HttpHeaders().set('Authorization', this.jwt);

  private project: Project;
  private projectRowList: ProjectRow[];
  private projectColList: ProjectColumn[];
  private projectItemList: ProjectItem[] = new Array();

  constructor(private http: HttpClient, private router: Router) { };

  getProjectRowList(projectId: number) {
    ///projectrow/project/1
    console.log(BACKEND_URL + '/projectrow/project/' + projectId.toString());
    this.http.get<ProjectRow[]>(BACKEND_URL + '/projectrow/project/' + projectId.toString(), {'headers': this.header}).subscribe(
      data => {
        this.projectRowList = data;
        console.log(data);
      },
      error => {
        console.error(error.error);
      }
    );
    return this.projectRowList;
  }

  getProjectColList(projectId: number) {
    ///projectrow/project/1
    console.log(BACKEND_URL + '/projectcolumn/project/' + projectId.toString());
    this.http.get<ProjectColumn[]>(BACKEND_URL + '/projectcolumn/project/' + projectId.toString(), {'headers': this.header}).subscribe(
      data => {
        this.projectColList = data;
        console.log(data);
      },
      error => {
        console.error(error.error);
      }
    );
    return this.projectColList;
  }

  getProjectItemList() {
    console.log(BACKEND_URL + '/projectitem/row/');
    let projectItemInRowList = new Array()
    for(let projectRow of this.projectRowList) {
      this.http.get<ProjectItem[]>(BACKEND_URL + '/projectitem/row/' + projectRow.id.toString(), {'headers': this.header}).subscribe(
        data => {
          projectItemInRowList.push(data);
          // for(let projectItem of data) {
          //   this.projectItemList.push(projectItem);
          // }
        }
      );
    }
    console.log(projectItemInRowList);
    return projectItemInRowList;
  }
}
