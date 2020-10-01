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

  public columns = ["name","cost_code","ediable","item_id"];

  public selectedColumns: any[] = [];

  ngOnInit(): void {
    this.selectedColumns.push(this.columns[0]);
    this.fields = ["quantity","price","total price"];
  }

}
