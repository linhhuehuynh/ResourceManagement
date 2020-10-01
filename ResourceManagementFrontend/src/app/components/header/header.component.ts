import { Component, OnInit } from '@angular/core';
import {MenuItem, MessageService, PrimeNGConfig} from 'primeng/api';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  providers:[MessageService],
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  // isExpanded = true;
  public username = "  Jack  ";
  public projectname = "";
  public createtime = "Novemeber-2015";
  // public isLogin = false;
  // public isShowing = false;
  // showSubmenu: boolean = false;

  items:MenuItem[];
  constructor(private messageService: MessageService,
    private primengConfig: PrimeNGConfig) { }

  ngOnInit(): void {
    this.primengConfig.ripple = true;

    this.items = [{
      label:this.username
    },
    {
      label:'Member since '+this.createtime
    }
    ]
  }
//microsoft container size
}
