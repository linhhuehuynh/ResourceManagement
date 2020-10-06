import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { ResourceCol } from '../resource/resource-col/resource-col.model';
import { ResourceDisplay } from './resource-display.model';
import { ProjectColumn } from '../../model/project-col.model';
import { ProjectRow } from 'src/app/model/project-row.model';
import { Router } from '@angular/router';
import { ProjectSelectorService } from '../project-selector/project-selector.service';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ResourceSelectService {
  
  constructor(private http: HttpClient, private projectSelectorService: ProjectSelectorService, private router: Router) { }

  async saveSelelectedData(projectId: number, colList: ResourceCol[], rowList: ResourceDisplay[]) {
    await this.http.delete(BACKEND_URL + "/projectcolumn/project/" + projectId.toString(), {responseType: 'text'}).toPromise().then(data => {
      console.log("All Columns Deleted")
    })
    await this.http.delete(BACKEND_URL + "/projectrow/project/" + projectId.toString(), {responseType: 'text'}).toPromise().then(data => {
      console.log("All Rows Deleted")
    })
    let colMap = new Map();
    for(let col of colList) {
      await this.http.post<ProjectColumn>(BACKEND_URL + "/projectcolumn", {
        projectColumnName: col.resourceColumnName,
        columnType: "string",
        project: {
          id: projectId
        }
      }).toPromise().then(res => {
        colMap.set(col.id, res.id);
      });
    }
    for(let row of rowList) {
      let rowId: number;
      await this.http.post<ProjectRow>(BACKEND_URL + "/projectrow", {
        project: {
          id: projectId
        }
      }).toPromise().then(res => {
        rowId = res.id;
      });
      await this.http.post(BACKEND_URL + "/projectitem", {
        value: row.resource.name,
        projectRow: {
          id: rowId
        },
        projectColumn: {
          id: colMap.get(-4)
        }
      }).toPromise();
      await this.http.post(BACKEND_URL + "/projectitem", {
        value: row.resource.code,
        projectRow: {
          id: rowId
        },
        projectColumn: {
          id: colMap.get(-3)
        }
      }).toPromise();
      await this.http.post(BACKEND_URL + "/projectitem", {
        value: "true",
        projectRow: {
          id: rowId
        },
        projectColumn: {
          id: colMap.get(-2)
        }
      }).toPromise();
      await this.http.post(BACKEND_URL + "/projectitem", {
        value: row.resource.id.toString(),
        projectRow: {
          id: rowId
        },
        projectColumn: {
          id: colMap.get(-1)
        }
      }).toPromise();
      for(let item of row.itemList) {
        await this.http.post(BACKEND_URL + "/projectitem", {
          value: item.resourceExtraItemValue,
          projectRow: {
            id: rowId
          },
          projectColumn: {
            id: colMap.get(item.resourceColumn.id)
          }
        }).toPromise();
      }
    }
    // this.projectSelectorService.setSelectedProjectId(projectId);
    this.router.navigate(['/project']);
  }
}
