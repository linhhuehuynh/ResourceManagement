import { Component, OnInit } from '@angular/core';
import {MenuItem, MessageService, PrimeNGConfig} from 'primeng/api';
import { AuthService } from './../auth/auth.service';
import { Router } from '@angular/router';


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

    private primengConfig: PrimeNGConfig, private authService:AuthService) { }


  ngOnInit(): void {
    this.primengConfig.ripple = true;

    this.items = [{
      label:this.username
    },
    {
      label:'Member since '+this.createtime
    },
    {
      label:'profile',
      command:() => {
        this.showProfile();
      }
    },
    {
      label:"sign out",
      command:() => {
        this.signout();
      }
    }
    ]

  }
  signout() {
    throw new Error('Method not implemented.');
  }
  showProfile() {
    this.router.navigate(["/login"]);
  }

  logout() {
this.authService.logout();
  }
//microsoft container size
}
