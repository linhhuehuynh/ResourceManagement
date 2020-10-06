import { Template } from './template.model';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService} from '../../auth/auth.service'
import { Observable } from 'rxjs'
import { of } from 'rxjs'
import { ProjectColumn } from 'src/app/model/project-col.model';


const BACKEND_URL = environment.apiUrl;

@Injectable({
    providedIn: 'root'
  })
export class TemplateService {
    token: any;
    header: any;
    userIsAuthenticated=false;

    // projectId = 1;

    public templateList: Template[];

    public types = ["Text","Number","Formula"];
    projectColList: ProjectColumn[];

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        this.templateList = [];
        this.projectColList = [];
        // this.userIsAuthenticated = this.authService.getIsAuth();
    }

    saveTemplates(templateList:Template[],projectId):Observable<Template[]>{
        console.log("I'm going to save this one");
        for(let template of templateList) {
            console.log(template);
            console.log({projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:projectId}});//*
            //newcolumnlist+select
            this.http.post(BACKEND_URL+'/projectcolumn',{projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:projectId}});
        }
        return of(templateList);
    }

    getProjectColList(projectId: number):Observable<ProjectColumn[]> {
        return this.http.get<ProjectColumn[]>(BACKEND_URL + '/projectcolumn/project/' + projectId.toString())
        // .toPromise()
        // .then(res => {
        //   this.projectColList = res;
        //   return this.projectColList;
        // })
    }
}

