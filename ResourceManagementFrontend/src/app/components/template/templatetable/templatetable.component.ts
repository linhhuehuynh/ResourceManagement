import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Template } from './template.model'
import { TemplateService } from './template.service'
import { Observable } from 'rxjs'

@Component({
  selector: 'app-templatetable',
  templateUrl: './templatetable.component.html',
  styleUrls: ['./templatetable.component.css']
})
export class TemplatetableComponent implements OnInit {

  disabled: boolean = true;

  fields = [];

  public columns = [{columnName:"name",columnId:null},{columnName:"cost_code",columnId:null},{columnName:"ediable",columnId:null},{columnName:"item_id",columnId:null}];//column name,column_id

  public columnNames:string[] = [];
  
  //projectcolumn getall by projectid,name->project name,cost_code->project_code saved 

  public newColumns: any[];

  public selectedColumns: any[] = [];

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

  constructor(private templateService:TemplateService, private router:Router) {
    // this.types= [{name:'Text'},{name:'Number'},{name:'Formula'}];
    }

  ngOnInit(): void {
  // getcolumns,+columns(name,id)

    this.selectedColumns.push(this.columns[0].columnName);
    
    
    // this.fields = ["quantity","price","total price"];
    // this.newTemplate = new Template;
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
      this.templateService.saveTemplates(templates).subscribe(
        res=>{
          for(let template of templates) {
            this.columns.push({columnName:template.projectColumnName,columnId:null});
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
  //save/delete same time,save to db,ngshow,left
  //dialog
}
