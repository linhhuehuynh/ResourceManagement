import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule }    from '@angular/forms';

// import {MatSidenavModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon'
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {ToolbarModule} from 'primeng/toolbar';

import { PasswordValidatorDirective } from './components/auth/password.directive';
import { AuthInterceptor } from './components/auth/auth-interceptors';


import {CheckboxModule} from 'primeng/checkbox';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';

import { SidebarModule } from 'primeng/sidebar';
import { MenuModule } from 'primeng/menu';
import { RippleModule } from 'primeng/ripple';

import {PasswordModule} from 'primeng/password';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {DialogModule} from 'primeng/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { LoginComponent } from './components/auth/login/login.component';

import { NavigationComponent } from './components/navigation/navigation.component';
import { HeaderComponent } from './components/header/header.component';
import { TemplatetableComponent } from './components/template/templatetable/templatetable.component';

import { ProjectDisplayTableComponent } from './components/project-display-table/project-display-table.component';
import { from } from 'rxjs';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,

    NavigationComponent,
    HeaderComponent,
    TemplatetableComponent,
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
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ToolbarModule,
    SidebarModule,
    MenuModule,
    RippleModule,
    TableModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
