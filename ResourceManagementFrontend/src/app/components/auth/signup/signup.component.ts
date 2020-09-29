import { Component, OnInit, OnDestroy } from '@angular/core';
import {NgForm} from '@angular/forms';
import {Subscription} from "rxjs";
import { AuthService } from './../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, OnDestroy {
  private authStatusSub: Subscription;
  isLoading = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {this.isLoading = false;
      }
    );
  }

  onSignup(form: NgForm) {
    if(form.invalid){ return;}
    this.isLoading=true;
    this.authService.createUser(form.value.username, form.value.confirmPassword);
    console.log(form);
  }
  
  ngOnDestroy() {
    this.authStatusSub.unsubscribe();
  }
}
