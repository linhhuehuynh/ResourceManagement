import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule }    from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// import {MatSidenavModule} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon'
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {ToolbarModule} from 'primeng/toolbar';

import {CheckboxModule} from 'primeng/checkbox';
import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {InputTextModule} from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { MenuModule } from 'primeng/menu';
import { RippleModule } from 'primeng/ripple';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { LoginComponent } from './components/auth/login/login.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    NavigationComponent,
    HeaderComponent
  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    CheckboxModule,
    ButtonModule,
    CardModule,
    InputTextModule,
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
    RippleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
