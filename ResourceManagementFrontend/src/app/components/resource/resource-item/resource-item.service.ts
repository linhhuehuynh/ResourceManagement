import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../../environments/environment';
import { ResourceItem } from './resource-item.model';
import { Resource } from './../resource-data.model';
import { ResourceRow } from './../resource-row/resource-row.model';
import { ResourceService } from './../resource.service';

const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class ResourceItemService {
  private resources: Resource[];
  private resourceRowList: ResourceRow[];

  constructor(private http: HttpClient, private resource:ResourceService) {
    this.resources = [];
    this.resourceRowList = []
   }

   getResource() {
    return this.http.get<Resource[]>(BACKEND_URL + '/resource')
    .toPromise()
    .then(res => {
      this.resources = res;
      return this.resources;
    })
  }

  // getAllResourcesExtraItem(){
  //  return this.http.get(BACKEND_URL + 'resourceitem');
  // }

  async getResourceItemList() {
    for (let resource of this.resources) {
      let row = new ResourceRow();
      row.id = resource.id;
      await this.http.get<ResourceItem[]>(BACKEND_URL + '/resourceitem/resource/' + row.id.toString()).toPromise()
      .then(response => {
        row.itemList = response;
        this.resourceRowList.push(row);
      });
      // .subscribe(
        // response => {
        //   row.itemList = response;
        //   this.resourceRowList.push(row);
        // }
      // );
    }
    console.log(this.resourceRowList);
    return this.resourceRowList;
  }
}
