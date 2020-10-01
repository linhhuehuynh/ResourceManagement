import { Component, OnInit, OnDestroy } from '@angular/core';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title: string;
  message: any;
  success: boolean;
  isLoading = false;
  private authStatusSub: Subscription;

  display=false;

  rememberMe: string[] = [];
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authStatusSub = this.authService.getAuthStatusListener().subscribe(
      authStatus => {
        this.isLoading = false;
      }
    )
  }

  onLogin(form: NgForm) {
    if(form.invalid){ return;}
    this.isLoading = true;
    this.authService.login(form.value.username, form.value.password);

    //Get error message
    this.message = this.authService.message;
  }

  showDialog() {

    this.display=true;
  }
  
  ngOnDestroy() {
    this.authStatusSub.unsubscribe();
  }

}
