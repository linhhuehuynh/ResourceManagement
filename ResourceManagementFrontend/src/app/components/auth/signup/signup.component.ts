import { Component, OnInit, OnDestroy } from '@angular/core';
import {NgForm} from '@angular/forms';
import {Subscription} from "rxjs";
import { AuthData } from '../auth-data.model';

import { AuthService } from './../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, OnDestroy {
  title: string;
  message: any;
  success: boolean;
  private authStatusSub: Subscription;
  isLoading = false;

  display=false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {this.isLoading = false;
      }
    );
  }

  onSignup(form: NgForm) {
    if(form.invalid){ return;}
    this.isLoading=true;
    this.authService.createUser(form.value.username, form.value.password).subscribe(
      res => {
        this.success = true;
        this.message = "You have registered successfully."
        this.title="Thank you!"
        setTimeout(() => this.router.navigate(["/login"]), 2000)
      },
      error => {
        console.log(error);
        this.success=false;
        this.title="An error occured!"
        this.message = error.error;
      }
    )
  }

  showDialog() {
    this.display=true;
  }
  
  ngOnDestroy() {
    this.authStatusSub.unsubscribe();
  }
}
