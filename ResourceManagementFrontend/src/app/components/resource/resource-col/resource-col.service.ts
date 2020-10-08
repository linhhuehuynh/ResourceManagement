import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from './../../../../environments/environment';
import { ResourceCol } from './resource-col.model';
import { Observable } from 'rxjs';

const BACKEND_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class ResourceColService {

  constructor(private http: HttpClient) { }

  getAllResourceColumnName() {
    return this.http.get<ResourceCol[]>(BACKEND_URL + '/resourcecolumn');
  }

  createResourceColumn(col: ResourceCol){
    return this.http.post(BACKEND_URL + '/resourcecolumn', col);
  }

  updateResourceColumn(col: ResourceCol): Observable<ResourceCol> {
    return this.http.put<ResourceCol>(`${BACKEND_URL}/resourcecolumn/${col.id}`, col);
  }

  deleteColumnById(col: ResourceCol): Observable<ResourceCol> {
    return this.http.delete<ResourceCol>(`${BACKEND_URL}/resourcecolumn/${col.id}`);
  }

}
