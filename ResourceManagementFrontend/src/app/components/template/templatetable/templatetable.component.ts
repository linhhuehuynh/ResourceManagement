import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-templatetable',
  templateUrl: './templatetable.component.html',
  styleUrls: ['./templatetable.component.css']
})
export class TemplatetableComponent implements OnInit {

  disabled: boolean = true;

  // field1: string;

  // field2: string;

  // field3: string;
  fields = [];

  constructor() { }

  public columns = ["name","cost_code","ediable","item_id"];//column name,column_id

  //projectcolumn getall by projectid,name->project name,cost_code->project_code saved 

  public selectedColumns: any[] = [];

  ngOnInit(): void {
    this.selectedColumns.push(this.columns[0]);
    this.fields = ["quantity","price","total price"];
  }

  //post project column,default type number text formula, field empty,field not repeated by known and new created,projectcolumn ts model
}
