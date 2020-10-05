import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Template } from './template.model'
import { Column } from './template.model'
import { TemplateService } from './template.service'
import { ProjectDisplayService } from '../../project-display-table/project-display.service'
import { ProjectColumn } from '../../../model/project-col.model'

@Component({
  selector: 'app-templatetable',
  templateUrl: './templatetable.component.html',
  styleUrls: ['./templatetable.component.css']
})
export class TemplatetableComponent implements OnInit {

  disabled: boolean = true;

  fields = [];

  // public columns = [{columnName:"name",columnId:null},{columnName:"cost_code",columnId:null},{columnName:"ediable",columnId:null},{columnName:"item_id",columnId:null}];//column name,column_id
  public columns:Column[];

  public columnNames:string[] = [];
  
  //projectcolumn getall by projectid,name->project name,cost_code->project_code saved 

  public newColumns: any[];

  public selectedColumns: any[];

  public templates: Template[]=[];

  public types:any[]= [{label:'Text',columnType:'Text'},{label:'Number',columnType:'Number'},{label:'Formula',columnType:'Formula'}];

  // public types=['Text','Number','Formula'];

  // public newTemplate : Template = {projectColumnName:"",columnType:"",formulaValue:""};

  // public newTypes: any[];

  // public items = [{"field":"","type":"","Formula":""},{"field":"","type":"","Formula":""},{"field":"","type":"","Formula":true}]

  public selectedTypes: any[] = [];

  success:boolean;
  message:any;
  title:string;

  public projectId = 1;
  projectColList: ProjectColumn[];

  constructor(private templateService:TemplateService, private router:Router,private projectDisplayService: ProjectDisplayService) {
    // this.types= [{name:'Text'},{name:'Number'},{name:'Formula'}];
    this.columns = [];
    this.selectedColumns = [];
    }

  ngOnInit(): void {
  // getcolumns,+columns(name,id)
    // this.selectedColumns.push(this.columns[0]);
    // this.templateService.getProjectColList(this.projectId).then(data => {this.projectColList = data});
    this.templateService.getProjectColList(this.projectId).subscribe(
      res=>{for(let projectcolumn of res) {
        if(projectcolumn.projectColumnName==='name') {
          this.selectedColumns.push(projectcolumn.projectColumnName);
        }
        this.columns.push({columnName:projectcolumn.projectColumnName,columnId:projectcolumn.id});
      }
      
      // this.selectedColumns.push(this.columns[0]);
    });
    console.log(this.selectedColumns);
  }

  AddNewTemplate() {
    // if(this.templates.length>=3) {
    //   alert("Please delete some templates until three");
    // } else  {
    //   this.templates.push(this.newTemplate);
    // }
    this.templates.push(new Template());
    
    // this.templates = this.items;
  }

  DeleteTheTemplate(template) {
    this.templates = this.templates.filter(t => {
      return t.projectColumnName !== template.projectColumnName
    } );
  }//delete

  SaveNewTemplate(templates:Template[]) {
    let signal = true;
    for(let column of this.columns) {
      this.columnNames.push(column.columnName);
    }
    for(let template of templates) {
      if(template.projectColumnName.length === 0) {
        alert("Please fill the name of the column");
        signal = false;
        break;
      }
      if(this.columnNames.includes(template.projectColumnName)) {
        alert("Please enter unexisted columns");
        this.DeleteTheTemplate(template);
        signal = false;
        break;
      }
    }
    if((templates.length != 0)&&(signal)) {
      this.templateService.saveTemplates(templates,this.projectId).subscribe(
        res=>{
          for(let template of templates) {
            this.columns.push({columnName:template.projectColumnName,columnId:null});
            this.DeleteTheTemplate(template);
          }
          this.success=true;
          this.message="You have saved templates successfully.";
          this.title="Thank you";
          
        },
        error =>{
          this.success=false;
          this.message=error;
          this.title="An error occurred.";
        }
      )
    }
    console.log(this.templates);

    //delete all,alert=>dialog
  }
  //post project column,default type number text formula, field empty,field not repeated by known and new created,projectcolumn ts model
  // return value, check repeat, show calculate drop down and regular check,hide
  //save to db,ngshow,left
  //dialog
}
