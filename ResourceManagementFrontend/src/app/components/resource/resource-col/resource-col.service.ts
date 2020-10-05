import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../../environments/environment';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ResourceColService {

  constructor(private http: HttpClient) { }

  getAllResourceColumnName() {
    return this.http.get(BACKEND_URL + '/resourcecolumn');
  }
}
