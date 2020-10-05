import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Project } from 'src/app/model/project.model';
import {environment} from '../../../environments/environment';
import { AuthService } from './../auth/auth.service';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ProjectSelectorService {
  token: any;
  header: any;
  userIsAuthenticated=false;

  private projectList: Project[];

  constructor(private http: HttpClient, private authService: AuthService) { 
    this.projectList = [];
    this.userIsAuthenticated = this.authService.getIsAuth();
  }

  getProjectList() {
    return this.http.get<Project[]>(BACKEND_URL + '/project')
    .toPromise()
    .then(res => {
      this.projectList = res;
      console.log(this.projectList);
      return this.projectList;
    })
  }

  //current projectid
}
