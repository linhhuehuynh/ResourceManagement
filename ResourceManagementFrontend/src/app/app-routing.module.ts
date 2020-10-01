import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/auth/signup/signup.component';
import {LoginComponent} from './components/auth/login/login.component';
import {ProjectDisplayTableComponent} from './components/project-display-table/project-display-table.component';
import { AuthGuard } from './components/auth/auth.guard';

const routes: Routes = [
  // {path:'', component: PostsComponent},
  {path:'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'project', component: ProjectDisplayTableComponent, canActivate: [AuthGuard] },
  // {path:'**', component: ErrorComponent}
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule],
providers: [AuthGuard]
})
export class AppRoutingModule { }
