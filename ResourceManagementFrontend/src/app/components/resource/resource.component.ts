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

  displayResourceRowList: ResourceRow[];

  displayHeaders: ResourceCol[];

  
  isLoading = false;
  resourceRowList: ResourceRow[];
  headers: ResourceCol[];

  items: MenuItem[];
  editColumnMenu: MenuItem[];
  newColName: string;
  editColName: string;
  editColId: number;
  isNewRow = false;
  displayModalColumn: boolean;
  displayModalEditColumn: boolean;
  displayModalCSV: boolean;

  uploadedFile: File;
  isFileLoaded: boolean;
  inputResourceRowList: ResourceRow[];
  inputHeaders: ResourceCol[];

  @ViewChild('dt') table: Table;

  constructor(private resourceService: ResourceService, private authService: AuthService, private resourceItem:ResourceItemService, private resourceCol: ResourceColService) {

    
   }

  ngOnInit() {


    this.initData();

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
  }


  initData() {
    this.headers =[];
    this.isFileLoaded = false;
    this.uploadedFile = null;
    this.inputResourceRowList = [];
    this.inputHeaders = [];

    this.resourceItem.getResource().then(data => {

      // this.defaultResourceList = data;
      // this.isLoading=true;
      this.resourceItem.getResourceItemList().then(response => {
        this.resourceRowList = response;
        this.displayResourceRowList = this.resourceRowList;
      });
    })

    this.resourceCol.getAllResourceColumnName()
    .subscribe(columns => {
      // this.isLoading=true
      if(columns == null) {} 
      else {
        this.headers = columns.sort((a, b) => {return a.id - b.id})
        this.displayHeaders = this.headers;
      }
    });
  }


 //ADD NEW ROW
  addNewRow(){
    let newRow = new ResourceRow();
    newRow.resource = new Resource();
    newRow.resource.name=" "
    this.resourceRowList.push(newRow);
    this.isNewRow = true;

  }

// ADD NEW COLUMN
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

// EDIT COLUMN NAME
  showModalEditColumn(id: number) {
  this.displayModalEditColumn = true;
  this.editColId = id
  } 

  editColumn(col: ResourceCol){
  col.resourceColumnName = this.editColName;
  col.id = this.editColId;

  this.resourceCol.updateResourceColumn(col).subscribe(() => {
    this.resourceCol.getAllResourceColumnName().subscribe(columns => {
      if(columns == null) {} 
      else {this.headers = columns.sort((a, b) => {return a.id - b.id})}
    });
  })
  
  this.displayModalEditColumn=false;
  }

// DELETE A COLUMN
  onDeleteColumn(col: ResourceCol) {
    this.resourceCol.deleteColumnById(col).subscribe();
    this.headers = this.headers.filter(header => col.id !== header.id)
    this.resourceRowList.forEach(row => row.itemList = null);
  }

 
// Import CSV File
  showModalDialogCSV() {
    this.displayModalCSV = true;
  }

  uploadFileOnChange(files: FileList) {
    console.log(files);
    if(files && files.length > 0) {
      this.uploadedFile = files[0];
      this.isFileLoaded = true;
    } else {
      this.isFileLoaded = false;
      console.log("No File")
    }
  }

  onSubmitFile() {
    if(this.isFileLoaded && this.uploadedFile) {
      this.inputResourceRowList = [];
      this.inputHeaders = [];
      let reader: FileReader = new FileReader();
      reader.readAsText(this.uploadedFile);
      reader.onloadend = (e) => {
        let csv: any = reader.result;
        let allTextLines: string[] = [];
        allTextLines = csv.split(/\r|\n|\r/);
        allTextLines = allTextLines.filter(line => line != "");
        console.log(allTextLines);

        // read headers 
        // no need? because input csv should be on the fomular of resource
        
        let headersInput = allTextLines[0].split(/,/);
        for(let colName of headersInput) {
          let tmpCol = new ResourceCol();
          tmpCol.resourceColumnName = colName;
          this.inputHeaders.push(tmpCol);
        }
        console.log(this.inputHeaders);
        
        
        // read resource
        
        for(let i = 1; i < allTextLines.length; i ++) {
          let tmpItemList = allTextLines[i].split(/,/);
          let tmpResource = new Resource();
          tmpResource.name = tmpItemList[0];
          tmpResource.code = tmpItemList[1];
          let tmpResourceRow = new ResourceRow();
          tmpResourceRow.itemList = [];
          tmpResourceRow.resource = tmpResource;
          this.inputResourceRowList.push(tmpResourceRow);
        }
        console.log(this.inputResourceRowList);
        this.displayHeaders = [];
        this.displayResourceRowList = this.inputResourceRowList;
      }
    } else {
      console.log("No File Loaded")
    }
    this.displayModalCSV = false;
  }

  cancelCSVClick() {
    this.uploadedFile = null;
    this.isFileLoaded = false;
    this.displayModalCSV = false;
  }

  submitClicked() {
    if(this.isFileLoaded) {
      this.saveFile();
    } else {

    }
  }

  saveFile() {
    this.resourceService.saveInputCSV(this.inputResourceRowList).then(res => {
      this.resourceItem.emptyData();
      this.initData();
      alert("Imported CSV Seccessfully!");
    });

  }
  
  discardClicked() {
    if(this.isFileLoaded) {
      this.noSaveFile();
    } else {

    }
  }

  noSaveFile() {
    this.uploadedFile = null;
    this.inputResourceRowList = null;
    this.inputHeaders = null;
    this.isFileLoaded = false;

    this.displayResourceRowList = this.resourceRowList;
    this.displayHeaders = this.headers;
    alert("Canceled The CSV Import!");
  }
}
