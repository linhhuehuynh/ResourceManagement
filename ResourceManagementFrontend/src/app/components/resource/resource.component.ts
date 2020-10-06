import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { ResourceService } from './resource.service';
import { Resource } from './resource-data.model';
import { Subscription } from 'rxjs';
import { AuthService } from './../auth/auth.service';
import { MenuItem } from 'primeng/api';
import { ResourceItemService } from './resource-item/resource-item.service';
import { ResourceColService } from './resource-col/resource-col.service';
import { ResourceRow } from './resource-row/resource-row.model';
import { ResourceCol } from './resource-col/resource-col.model';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {
  isLoading = false;
  private authStatusSub: Subscription;
  resourceRowList: ResourceRow[];
  headers: ResourceCol[];
  items: MenuItem[];
  defaultResourceList: Resource[];

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService, private resourceItem:ResourceItemService, private resourceCol: ResourceColService) { }

  ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )

    this.resourceItem.getResource().then(data => {
      this.defaultResourceList = data;
      console.log("Default Resource List")
      console.log(this.defaultResourceList)
      this.resourceItem.getResourceItemList().then(response => {this.resourceRowList = response});
    })

    this.resourceCol.getAllResourceColumnName()
    .subscribe(columns => {
      if(columns == null) {} 
      else {this.headers = columns.sort((a, b) => {return a.id - b.id})}
    });

  this.items = [
    {
        label: 'Add Row',
        icon: 'pi pi-align-left',
        command: () => {
            console.log("update")
        }
    },
    {
        label: 'Add Column',
        icon: 'fa fa-columns',
        command: () => {
            console.log("delete")
        }
    },
    {
      label: 'Import CSV',
      icon: 'pi pi-file-excel',
      command: () => {
          console.log("delete")
      }
  }
    ]
  }
}
