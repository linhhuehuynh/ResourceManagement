import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  rememberMe: string[] = [];
  constructor() { }

  ngOnInit(): void {
  }

  onLogin(form: NgForm) {
    if(form.invalid){ return;}
    console.log("Logged In!")
  }

}
