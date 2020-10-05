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
  resourceDisplayList: ResourceRow[];
  headers: any;
  items: MenuItem[];

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService, private resourceItem:ResourceItemService, private resourceCol: ResourceColService) { }

  ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )

    this.resourceCol.getAllResourceColumnName().subscribe(columns => this.headers = columns);
    this.resourceItem.getResource().then(data => {
      this.resourceItem.getResourceItemList().then(data => {this.resourceDisplayList = data});
      // console.log(this.resourceDisplayList)
    })


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
