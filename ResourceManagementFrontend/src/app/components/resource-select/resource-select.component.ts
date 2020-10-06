import { Component, OnInit } from '@angular/core';
import { ResourceDisplay } from './resource-display.model';
import {MenuItem} from 'primeng/api';
import { ResourceCol } from '../resource/resource-col/resource-col.model';
import { ResourceColService } from '../resource/resource-col/resource-col.service';
import { ResourceItemService } from '../resource/resource-item/resource-item.service';
import { Resource } from '../resource/resource-data.model';
import { ProjectSelectorService } from '../project-selector/project-selector.service';

@Component({
  selector: 'app-resource-select',
  templateUrl: './resource-select.component.html',
  styleUrls: ['./resource-select.component.css']
})
export class ResourceSelectComponent implements OnInit {

  selectedProjectId: number;
  resourceList: ResourceDisplay[];
  resourceSelectedList: ResourceDisplay[];
  resourceColumnList: ResourceCol[];
  resourceRowList: Resource[];
  items: MenuItem[];

  constructor(
    private resourceColService: ResourceColService, 
    private resourceItemService: ResourceItemService, 
    private projectSelectorService: ProjectSelectorService
    ) { }

  ngOnInit(): void {
    this.resourceList = [];
    this.resourceSelectedList = [];
    this.resourceRowList = [];
    this.resourceColumnList = [];
    this.projectSelectorService.selectedProjectIdObservable.subscribe(id => this.selectedProjectId = id);

    this.resourceColService.getAllResourceColumnName().subscribe(
      data => {
        this.resourceColumnList = data
      }
    );

    this.resourceItemService.getResource().then(data => {
      this.resourceRowList = data;
      this.resourceItemService.getResourceItemList().then(data => {
        for(let row of data) {
          let res = new ResourceDisplay();
          res.resource = row.resource;
          res.itemList = row.itemList;
          res.chosen = false;
          this.resourceList.push(res);
        }
      });
    })
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
  }

  unselect() {
    let selected = this.resourceSelectedList.filter(resource => resource.chosen);
    this.resourceSelectedList = this.resourceSelectedList.filter(resource => !resource.chosen)
    for(let resource of selected) {
      resource.chosen = false;
    }
    this.resourceList = this.resourceList.concat(selected);
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
    console.log(this.selectedProjectId);
  }
}
