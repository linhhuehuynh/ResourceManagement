import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/user/signup/signup.component';
import {LoginComponent} from './components/user/login/login.component';

const routes: Routes = [
  // {path:'', component: PostsComponent},
  {path:'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  // {path:'**', component: ErrorComponent}
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
