import { Template } from './template.model';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService} from '../../auth/auth.service'
import { Observable } from 'rxjs'
import { of } from 'rxjs'


const BACKEND_URL = environment.apiUrl;

@Injectable({
    providedIn: 'root'
  })
export class TemplateService {
    token: any;
    header: any;
    userIsAuthenticated=false;

    projectId = 1;

    public templateList: Template[];

    public types = ["Text","Number","Formula"];

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
        this.templateList = [];
        this.userIsAuthenticated = this.authService.getIsAuth();
    }

    saveTemplates(templateList:Template[]):Observable<Template[]>{
        for(let template of templateList) {
            this.http.post(BACKEND_URL+'/projectcolumn',{projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:this.projectId}});
        }
        return of(templateList);
    }

    // getColumns()
}

