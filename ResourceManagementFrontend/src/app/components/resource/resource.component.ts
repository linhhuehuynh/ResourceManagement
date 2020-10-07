import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { ResourceService } from './resource.service';
import { Resource } from './resource-data.model';
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
  resourceRowList: ResourceRow[];
  headers: ResourceCol[];

  items: MenuItem[];
  editColumnMenu: MenuItem[];
  newColName: string;
  isNewRow = false;
  displayModalColumn: boolean;
  displayModalCSV: boolean;

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService, private resourceItem:ResourceItemService, private resourceCol: ResourceColService) {
    this.headers =[];
   }

  ngOnInit() {
    this.resourceItem.getResource().then(data => {
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
        command: () => this.addNewRow(),
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

    this.editColumnMenu = [
      {
          label: 'Edit Column',
          icon: 'far fa-edit',
          command: () => console.log("edit column"),
      },
      {
          label: 'Delete Column',
          icon: 'fas fa-trash-alt',
          command: (e) => this.onDeleteColumn(e)
      }
      ]
  }

  //Add New Row
  addNewRow(){
    let newRow = new ResourceRow();
    newRow.resource = new Resource();
    newRow.resource.name=" "
    this.resourceRowList.push(newRow);
    this.isNewRow = true;

  }

  // Add New Column
  showModalDialogColumn() {
    this.displayModalColumn = true;
  } 

  onSubmitColumn() {
    const col = new ResourceCol();
    col.resourceColumnName = this.newColName;
    this.headers.push(col);

    this.resourceCol.createResourceColumn(col).subscribe((data:any) => {
      col.id = data.id;

      this.resourceRowList.forEach(row => {
        if(row.itemList == undefined) {
          row.itemList = [];
        }
        
        let item = new ResourceItem();
        item.resourceColumn = new ResourceCol();
        item.resource = new Resource();
        item.resourceExtraItemValue = " ";
        item.resource.id = row.resource.id;
        item.resourceColumn.id = col.id;

        this.resourceItem.createResourceExtraItem(item).subscribe();
        console.log(item)
  
        row.itemList.push(item);
      } )
    });
    this.displayModalColumn=false;
  }

  onDeleteColumn(col: ResourceCol) {
    // console.log(col)
    // this.resourceCol.deleteColumnById(col).subscribe(()=> console.log(col))

    this.headers = this.headers.filter(header => col.id !== header.id)

    this.resourceRowList.forEach(row => row.itemList = null);
  }

 

  // Import CSV File
  showModalDialogCSV() {
    this.displayModalCSV = true;
  }

  onSubmitFile() {

  }
  
}
