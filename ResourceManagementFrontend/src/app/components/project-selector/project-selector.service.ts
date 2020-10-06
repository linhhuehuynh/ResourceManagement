import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Project } from 'src/app/model/project.model';
import {environment} from '../../../environments/environment';
import { AuthService } from './../auth/auth.service';
import { BehaviorSubject, Observable } from 'rxjs';

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ProjectSelectorService {
  token: any;
  header: any;
  userIsAuthenticated=false;

  private projectList: Project[];
  private selectedProjectId : BehaviorSubject<number>  = new BehaviorSubject<number>(0);
  selectedProjectIdObservable: Observable<number>  = this.selectedProjectId.asObservable();

  constructor(private http: HttpClient, private authService: AuthService) { 
    this.projectList = [];
    this.userIsAuthenticated = this.authService.getIsAuth();
    this.initselectedProjectId();
  }

  initselectedProjectId() {
    this.getProjectList()
      .then(data => {
          this.projectList = data;
          let defaultId = this.projectList.length > 0 ? this.projectList[0].id : undefined;
          this.setSelectedProjectId(defaultId);
      });
  }

  getProjectList() {
    return this.http.get<Project[]>(BACKEND_URL + '/project')
    .toPromise()
    .then(res => {
      this.projectList = res;
      return this.projectList;
    })
  }


  setSelectedProjectId(id: number) {
    this.selectedProjectId.next(id);
  }
}
