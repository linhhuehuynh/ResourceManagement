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

  deleteColumnById(col: ResourceCol): Observable<ResourceCol> {
    console.log(`service${col.id}`)
    return this.http.delete<ResourceCol>(`${BACKEND_URL}/resourcecolumn/${col.id}`);
  }

}
