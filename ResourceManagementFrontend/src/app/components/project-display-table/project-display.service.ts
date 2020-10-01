import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { Project } from 'src/app/model/project.model';
import { ProjectRow } from 'src/app/model/project-row.model';
import { ProjectColumn } from 'src/app/model/project-col.model';
import { ProjectItem } from 'src/app/model/project-item.model';
import {environment} from '../../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { ProjectRowDisplay } from './project-row-display.model';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ProjectDisplayService {

  jwt = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMzMiLCJleHAiOjE2MDE2MDEwNzYsImlhdCI6MTYwMTU2NTA3Nn0.cuEcs-MVYo5tvVgc0qplUy--7ShVuIjts1Lcjv8yo40';

  header = new HttpHeaders().set('Authorization', this.jwt);

  private project: Project;
  private projectRowList: ProjectRow[];
  private projectColList: ProjectColumn[];
  private projectRowDisplayList: ProjectRowDisplay[];

  constructor(private http: HttpClient, private router: Router) { 
    this.project = null;
    this.projectRowList = [];
    this.projectColList = [];
    this.projectRowDisplayList = [];
  };

  getProjectRowList(projectId: number) {
    return this.http.get<ProjectRow[]>(BACKEND_URL + '/projectrow/project/' + projectId.toString(), {'headers': this.header})
    .toPromise()
    .then(res => {
      this.projectRowList = res;
      return this.projectRowList;
    })
    // .catch(error => {console.error(error.error)});

    // this.http.get<ProjectRow[]>(BACKEND_URL + '/projectrow/project/' + projectId.toString(), {'headers': this.header}).subscribe(
    //   data => {
    //     this.projectRowList = data;
    //     console.log(data);
    //   },
    //   error => {
    //     console.error(error.error);
    //   }
    // );
    // return this.projectRowList;
  }

  getProjectColList(projectId: number) {
    return this.http.get<ProjectColumn[]>(BACKEND_URL + '/projectcolumn/project/' + projectId.toString(), {'headers': this.header})
    .toPromise()
    .then(res => {
      this.projectColList = res;
      return this.projectColList;
    })
    // .catch(error => {console.error(error.error)});

    // this.http.get<ProjectColumn[]>(BACKEND_URL + '/projectcolumn/project/' + projectId.toString(), {'headers': this.header}).subscribe(
    //   data => {
    //     this.projectColList = data;
    //     console.log(data);
    //   },
    //   error => {
    //     console.error(error.error);
    //   }
    // );
    // return this.projectColList;
  }

  getProjectItemList() {
    for(let projectRow of this.projectRowList) {
      let tmp = new ProjectRowDisplay();
      tmp.id = projectRow.id;
      this.http.get<ProjectItem[]>(BACKEND_URL + '/projectitem/row/' + projectRow.id.toString(), {'headers': this.header}).subscribe(
        data => {
          for(let item of data) {
            item.changed = false;
          }
          tmp.itemList = data;
          this.projectRowDisplayList.push(tmp);
        }
      );
    }
    return this.projectRowDisplayList;
    // let promise = new Promise<ProjectRowDisplay[]>((resolve, reject) => {
    //   for(let projectRow of this.projectRowList) {
    //     let tmp = new ProjectRowDisplay();
    //     tmp.id = projectRow.id;
    //     this.http.get<ProjectItem[]>(BACKEND_URL + '/projectitem/row/' + projectRow.id.toString(), {'headers': this.header})
    //     .toPromise()
    //     .then(data => {
    //       tmp.itemList = data;
    //     })
    //     // .catch(error => {console.error(error.error)});
    //     this.projectRowDisplayList.push(tmp);
    //   }
    //   return this.projectRowDisplayList;
    // });
    // for(let projectRow of this.projectRowList) {
    //   this.http.get<ProjectItem[]>(BACKEND_URL + '/projectitem/row/' + projectRow.id.toString(), {'headers': this.header}).subscribe(
    //     data => {
    //       projectItemInRowList.push(data);
    //       // for(let projectItem of data) {
    //       //   this.projectItemList.push(projectItem);
    //       // }
    //     }
    //   );
    // }
    // console.log(this.projectRowDisplayList);
    // return promise;
  }

  saveChangedItems(projectRowDisplayList: ProjectRowDisplay[]) {
    for(let projectRow of projectRowDisplayList) {
      for(let item of projectRow.itemList) {
        if(item.changed) {
          this.http.put(BACKEND_URL + '/projectitem/' + item.id, 
          {
            value: item.value,
            projectRow: {id: item.projectRow.id},
            projectColumn: {id: item.projectColumn.id}
          }, 
          {'headers': this.header}).subscribe(data => {alert("Update Successfully!")});
          item.changed = false;
        }
      }
    }
    return projectRowDisplayList;
  }
}