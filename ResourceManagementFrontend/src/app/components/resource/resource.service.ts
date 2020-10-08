import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { ResourceRow } from './resource-row/resource-row.model';
import { Router } from '@angular/router';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ResourceService {
  
  constructor(private http: HttpClient, private router: Router) { }

  getAllResources() {
    return this.http.get(BACKEND_URL + '/resource')
  }

  async saveInputCSV(rows: ResourceRow[]) {
    await this.http.delete(BACKEND_URL + '/resource', {responseType: 'text'}).toPromise().then(res => {});
    await this.http.delete(BACKEND_URL + '/resourcecolumn', {responseType: 'text'}).toPromise().then(res => {});
    for(let row of rows) {
      await this.http.post(BACKEND_URL + '/resource', {name: row.resource.name, code: row.resource.code}, {responseType: 'text'}).toPromise().then();
    }
  }
}
