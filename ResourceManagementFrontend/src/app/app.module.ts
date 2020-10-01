import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule }    from '@angular/forms';
import { PasswordValidatorDirective } from './components/auth/password.directive';
import { AuthInterceptor } from './components/auth/auth-interceptors';


import {CheckboxModule} from 'primeng/checkbox';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';

import {PasswordModule} from 'primeng/password';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {DialogModule} from 'primeng/dialog';


import {TableModule} from 'primeng/table';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { LoginComponent } from './components/auth/login/login.component';
import { ProjectDisplayTableComponent } from './components/project-display-table/project-display-table.component';
import { from } from 'rxjs';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    
    PasswordValidatorDirective,
    ProjectDisplayTableComponent
  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    CheckboxModule,
    ButtonModule,
    InputTextModule,
    MessageModule,
    MessagesModule,
    DialogModule,
    PasswordModule,

    FormsModule,
    HttpClientModule,
    TableModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
