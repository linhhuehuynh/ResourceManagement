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
import { ProjectCol } from './template.model'
import { ProjectDisplayService } from '../../project-display-table/project-display.service'
import { ProjectItem } from '../../../model/project-item.model'

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

    constructor(private http: HttpClient, private router: Router, private authService: AuthService, private projectDisplayService: ProjectDisplayService) {
        this.templateList = [];
        this.projectColList = [];
        // this.userIsAuthenticated = this.authService.getIsAuth();
    }

    createColumn(template:Template,projectId:number) {
        let colName:string = template.projectColumnName;
        let colType:string = template.columnType;
        let id:number = projectId;
        let col:ProjectCol = {projectColumnName:colName,columnType:colType,project:{id:id}}
        console.log(template);
        console.log({projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:projectId}});
        return this.http.post<ProjectColumn>(BACKEND_URL+"/projectcolumn",col);
    }
    //projectid在哪选择的

    createItem(projectRowId:number,projectColId:number) {
        let projectItem: ProjectItem;
        // projectItem.value=null;
        return this.http.post<ProjectItem>(BACKEND_URL+"/projectitem",{value:"1",projectRow:{id:projectRowId},projectColumn:{id:projectColId}})

    }
    
    // saveTemplates(templateList:Template[],projectId:number):Observable<ProjectCol[]>{
    //     console.log("I'm going to save this one");
    //     let colList:ProjectCol[]=[];
    //     let col:ProjectCol;
    //     for(let template of templateList) {
    //         console.log(template);
    //         console.log({projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:projectId}});//*
    //         let colName:string = template.projectColumnName;
    //         let colType:string = template.columnType;
    //         let id:number = projectId;
    //         col = {projectColumnName:colName,columnType:colType,project:{id:id}}
    //         this.create(template,projectId);
    //         //newcolumnlist+select
    //         // this.http.post(BACKEND_URL+'/projectcolumn',{projectColumnName:template.projectColumnName,columnType:template.columnType,project:{id:projectId}});
    //         colList.push(col);
    //         console.log("ok");
    //     }//xianxunhuan
    //     return this.http.post<ProjectCol[]>(BACKEND_URL+"/projectcolumn",colList);
    //     // console.log(templateList);
    //     // return of(templateList);
    // } 
    //We couldn't add columns list

    getProjectColList(projectId: number):Observable<ProjectColumn[]> {
        return this.http.get<ProjectColumn[]>(BACKEND_URL + '/projectcolumn/project/' + projectId.toString())
        // .toPromise()
        // .then(res => {
        //   this.projectColList = res;
        //   return this.projectColList;
        // })
    }


}

