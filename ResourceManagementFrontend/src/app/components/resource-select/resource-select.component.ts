import { Component, OnInit } from '@angular/core';
import { ResourceDisplay } from './resource-display.model';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-resource-select',
  templateUrl: './resource-select.component.html',
  styleUrls: ['./resource-select.component.css']
})
export class ResourceSelectComponent implements OnInit {

  resourceList: ResourceDisplay[];
  resourceSelectedList: ResourceDisplay[];
  resourceColumnList: any[];
  items: MenuItem[];

  constructor() { }

  ngOnInit(): void {
    this.resourceList = [];
    this.resourceSelectedList = [];
    let r1 = new ResourceDisplay;
    r1.id = 1;
    r1.itemList = ["00", "01"];
    r1.chosen = false;
    let r2 = new ResourceDisplay;
    r2.id = 2;
    r2.itemList = ["10", "11"];
    r2.chosen = false;
    let r3 = new ResourceDisplay;
    r3.id = 3;
    r3.itemList = ["20", "21"];
    r3.chosen = false;
    let r4 = new ResourceDisplay;
    r4.id = 4;
    r4.itemList = ["30", "31"];
    r4.chosen = false;
    let r5 = new ResourceDisplay;
    r5.id = 5;
    r5.itemList = ["40", "41"];
    r5.chosen = false;
    this.resourceList.push(r1);
    this.resourceList.push(r2);
    this.resourceList.push(r3);
    this.resourceList.push(r4);
    this.resourceList.push(r5);

    this.items = [
      {
        label: "Select all",
        command: () => this.selectAll()
      }, 
      {
        label: "Clean Selection",
        command: () => this.selectAllUndo()
      }
    ]
  }

  select() {
    let selected = this.resourceList.filter(resource => resource.chosen);
    this.resourceList = this.resourceList.filter(resource => !resource.chosen)
    for(let resource of selected) {
      resource.chosen = false;
    }
    this.resourceSelectedList = this.resourceSelectedList.concat(selected);
    console.log(this.resourceSelectedList);
  }

  unselect() {
    let selected = this.resourceSelectedList.filter(resource => resource.chosen);
    this.resourceSelectedList = this.resourceSelectedList.filter(resource => !resource.chosen)
    for(let resource of selected) {
      resource.chosen = false;
    }
    this.resourceList = this.resourceList.concat(selected);
    console.log(this.resourceList);
  }

  selectAll() {
    for(let resource of this.resourceList) {
      resource.chosen = true;
    }
  }

  selectAllUndo() {
    for(let resource of this.resourceList) {
      resource.chosen = false;
    }
  }

  // unselectAll() {
  //   for(let resource of this.resourceSelectedList) {
  //     resource.chosen = true;
  //   }
  // }

  // unselectAllUndo() {
  //   for(let resource of this.resourceSelectedList) {
  //     resource.chosen = false;
  //   }
  // }

  save() {
    console.log(this.resourceSelectedList);
  }
}
