import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { ResourceService } from './resource.service';
import { Resource } from './resource-data.model';
import { Subscription } from 'rxjs';
import { AuthService } from './../auth/auth.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {
  isLoading = false;
  private authStatusSub: Subscription;
  resource: any
  cols: any[];
  items: MenuItem[];

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService) { }

  ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )

    this.cols = [
      { field: 'name', header: 'Resource Name' },
      { field: 'code', header: 'Resource Code' },
  ];

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
    ],

      this.resourceService.getAllResources().subscribe(res => this.resource = res);
  }
}
