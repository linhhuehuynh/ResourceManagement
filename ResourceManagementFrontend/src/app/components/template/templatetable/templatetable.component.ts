import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Template } from './template.model'
import { Column } from './template.model'
import { Type } from './template.model'
import { TemplateService } from './template.service'
import { ProjectDisplayService } from '../../project-display-table/project-display.service'
import { ProjectColumn } from '../../../model/project-col.model'
import { ProjectSelectorService } from '../../project-selector/project-selector.service'
import { async } from '@angular/core/testing';


@Component({
  selector: 'app-templatetable',
  templateUrl: './templatetable.component.html',
  styleUrls: ['./templatetable.component.css']
})
export class TemplatetableComponent implements OnInit {

  disabled: boolean = true;

  fields = [];

  // public columns = [{columnName:"name",columnId:null},{columnName:"cost_code",columnId:null},{columnName:"ediable",columnId:null},{columnName:"item_id",columnId:null}];//column name,column_id
  public columns:ProjectColumn[];

  public resultColumns:ProjectColumn[];//selected+newlist

  public columnNames:string[] = [];
  
  //projectcolumn getall by projectid,name->project name,cost_code->project_code saved 

  public newColumns: ProjectColumn[];

  public newColumnsId: number[];

  public selectedColumns: any[];

  public templates: Template[]=[];

  public types:Type[]= [{label:'Text',columnType:'Text'},{label:'Number',columnType:'Number'},{label:'Formula',columnType:'Formula'}];
  
  // public types = [{columnType:'Text'},{columnType:'Number'},{columnType:'Formula'}];

  // public types:any[];

  // public newTemplate : Template = {projectColumnName:"",columnType:"",formulaValue:""};

  // public newTypes: any[];

  // public items = [{"field":"","type":"","Formula":""},{"field":"","type":"","Formula":""},{"field":"","type":"","Formula":true}]

  public selectedTypes: Type[];

  success:boolean;
  message:any;
  title:string;

  // public projectId = 1;
  projectColList: ProjectColumn[];

  selectedProjectId: number;

  constructor(private templateService:TemplateService, private router: Router,private projectSelectorService: ProjectSelectorService,private projectDisplayService:ProjectDisplayService) {
    // this.types= [{name:'Text'},{name:'Number'},{name:'Formula'}];
    this.columns = [];
    this.selectedColumns = [];
    this.selectedTypes = [];
    this.newColumns = [];
    this.newColumnsId = [];
    this.resultColumns=[];
    }

  ngOnInit(): void {
    this.columns = [];
    this.selectedColumns = [];
    this.selectedTypes = []
    this.projectSelectorService.selectedProjectIdObservable.subscribe(id=>this.selectedProjectId=id);
    this.newColumns = [];
    this.newColumnsId = [];
  // getcolumns,+columns(name,id)
    // this.selectedColumns.push(this.columns[0]);
    // this.templateService.getProjectColList(this.projectId).then(data => {this.projectColList = data});
    this.templateService.getProjectColList(this.selectedProjectId).subscribe(
      res=>{for(let projectcolumn of res) {
        if(projectcolumn.projectColumnName==='NAME') {
          this.selectedColumns.push(projectcolumn.projectColumnName);
        }
        this.columns.push({projectColumnName:projectcolumn.projectColumnName,id:projectcolumn.id,columnType:projectcolumn.columnType});
        // console.log({projectColumnName:projectcolumn.projectColumnName,id:projectcolumn.id,columnType:projectcolumn.columnType})
      }
      
      // this.selectedColumns.push(this.columns[0]);
    });
    
    console.log(this.selectedColumns);
  }

  AddNewTemplate() {
    let template:Template = new Template();
    template.projectColumnName="";
    template.columnType="Text";
    template.formulaValue="";
    console.log(template);
    this.templates.push(template);
    // this.templates = this.items;
  }

  DeleteTheTemplate(template) {
    console.log(this.templates);
    this.templates = this.templates.filter(t => {
      return t.projectColumnName !== template.projectColumnName
    } );
    console.log(this.templates);
  }//delete

  SaveNewTemplate(templates:Template[]) {
    let signal = true;
    let Rowlist = this.projectDisplayService.getLoadedProjectRowList();
    for(let column of this.columns) {
      this.columnNames.push(column.projectColumnName);
    }
    console.log(templates)
    if(templates.length!==0) {
      for(let template of templates) {
        if(template.projectColumnName === "") {
          alert("Please fill the name of the column");
          this.DeleteTheTemplate(template);
          signal = false;
          break;
        } else
        if(this.columnNames.includes(template.projectColumnName)) {
          alert("Please enter unexisted columns");
          this.DeleteTheTemplate(template);
          signal = false;
          break;
        } else  
        {
          if(this.FormulaCheck(this.columns,template.formulaValue)) {
            this.templateService.createColumn(template,this.selectedProjectId).subscribe(
              res=>{
                this.columns.push(res);
                // this.newColumns.push(res);
                // this.newColumnsId.push(res.id);
                this.DeleteTheTemplate(template);
                this.resultColumns.push(res);
                for(let row of Rowlist) {
                  this.Addnewitems(row.id,res.id); //Add item
                }
              }
            )
          } else {
            // this.DeleteTheTemplate(template);
            break;
          }  
        }
      
        // //delete all,alert=>dialog
        // this.Addnewitems(Rowlist[0].id, this.newColumnsId)
        console.log(this.selectedColumns)
        for(let name of this.selectedColumns) {
          for(let col of this.columns) {
            if(col.projectColumnName === name) {
              this.resultColumns.push(col);
            }
          }
        }
        console.log(this.resultColumns)
      }
    } else {
      alert("Please pass unempyty lists as templates")
    }

    console.log("new col")
    console.log(this.resultColumns);
    this.projectDisplayService.tempelateChange = true;
    this.projectDisplayService.updateData(this.resultColumns);
    this.router.navigate(["/project"]);
  }

  onChangeTypes(event,template:Template) {
    template.columnType = event.value;
    console.log(this.templates)
  }
  
  //post project column,default type number text formula, field empty,field not repeated by known and new created,projectcolumn ts model
  // return value, check repeat, show calculate drop down and regular check,hide
  //ngshow,left
  //dialog

  Addnewitems(rowId:number,colId:number) {
    this.templateService.createItem(rowId,colId).subscribe(res=>{
      console.log(res);
    })
  }
  

  FormulaCheck(templateArr:ProjectColumn[],formula:string) {
    let nameArr:string[] = []
    let signal:boolean = true;
    for(let template of templateArr) {
      nameArr.push(template.projectColumnName);
    }
    if(formula !=="") {
      if(formula.split("").includes("+")||formula.split("").includes("-")||formula.split("").includes("*")||formula.split("").includes("/")||formula.split("").includes("%")) {
        if(formula.split("").includes("+")) {
          let forArr = formula.split("+"); 
          if(forArr.length <= 1) {
            alert("Not correct operation");
            signal = false;
          } else{
            for(let arr of forArr) {
              if(!nameArr.includes(arr)) {
                signal = false;
              }
            }
          }  
          } else
          if(formula.split("").includes("-")) {
            let forArr = formula.split("-"); 
            if(forArr.length <= 1) {
              alert("Not correct operation");
              signal = false;
            } else {
              for(let arr of forArr) {
                if(!nameArr.includes(arr)) {
                  signal = false;
                }
              }
            }    
          } else
          if(formula.split("").includes("*")) {
            let forArr = formula.split("*"); 
            if(forArr.length <= 1) {
              alert("Not correct operation");
              signal = false;
            } else {
              for(let arr of forArr) {
                if(!nameArr.includes(arr)) {
                  signal = false;
                }
              }
            }    
          } else 
          if(formula.split("").includes("/")) {
            let forArr = formula.split("/");
            if(forArr.length <= 1) {
              alert("Not correct operation");
              signal = false;
            } else {
              for(let arr of forArr) {
                if(!nameArr.includes(arr)) {
                  signal = false;
                }
              }
            } 
          } else 
          if(formula.split("").includes("%")) {
            let forArr = formula.split("%");
            if(forArr.length <= 1) {
              alert("Not correct operation");
              signal = false;
            } else {
              for(let arr of forArr) {
                if(!nameArr.includes(arr)) {
                  signal = false;
                }
              }
            }     
          }
        } 
        else {
          alert("You need to use '+' or '-' or '*' or '/' or '%' as correct opeartors");
          signal = false;
      }
    }
    if(signal === false) {
      alert("Wrong formula, column name should in columns");
    }
    return signal;
  } 
}
