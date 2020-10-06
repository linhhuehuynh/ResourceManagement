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
import { ResourceItem } from './resource-item/resource-item.model';

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
  defaultResourceList: Resource[];
  columns: ResourceCol[]=[];

  items: MenuItem[];
  newColName: string;
  newColId: number;
  displayModalColumn: boolean;
  displayModalCSV: boolean;

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService, private resourceItem:ResourceItemService, private resourceCol: ResourceColService) {
    this.headers =[]
   }

  ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )

    this.resourceItem.getResource().then(data => {
      // this.defaultResourceList = data;
      this.resourceItem.getResourceItemList().then(response => {this.resourceRowList = response});
    })

    this.resourceCol.getAllResourceColumnName()
    .subscribe(columns => {
      if(columns == null) {} 
      else {this.headers = columns.sort((a, b) => {return a.id - b.id})}
    });

    console.log(this.headers)
    this.items = [
    {
        label: 'Add Row',
        icon: 'pi pi-align-left',
        command: () => {
        console.log("Add row")
        }
    },
    {
        label: 'Add Column',
        icon: 'fa fa-columns',
        command: () => this.showModalDialogColumn()
    },
    {
      label: 'Import CSV',
      icon: 'pi pi-file-excel',
      command: () => this.showModalDialogCSV()
  }
    ]
  }

  // Add New Column
  showModalDialogColumn() {
    this.displayModalColumn = true;
  } 

  addNewColName(col: ResourceCol){
    console.log(col)
    col.resourceColumnName = this.newColName;
    this.headers.push(col)

  this.displayModalColumn=false;
  }

  onSubmitColumn() {
    const col = {
      id: this.newColId,
      resourceColumnName : this.newColName
    }
    this.addNewColName(col);
  }


  // Import CSV File
  showModalDialogCSV() {
    this.displayModalCSV = true;
  }

  onSubmitFile() {

  }
  
}
