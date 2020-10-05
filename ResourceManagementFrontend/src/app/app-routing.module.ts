import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/auth/signup/signup.component';
import {LoginComponent} from './components/auth/login/login.component';
import {TemplatetableComponent} from './components/template/templatetable/templatetable.component'
import { ResourceComponent } from './components/resource/resource.component';
import {ProjectDisplayTableComponent} from './components/project-display-table/project-display-table.component';
import { AuthGuard } from './components/auth/auth.guard';
import { ProjectSelectorComponent } from './components/project-selector/project-selector.component';
import { ResourceSelectComponent } from './components/resource-select/resource-select.component';

const routes: Routes = [
  // {path:'', component: PostsComponent},
  {path:'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'project', component: ProjectDisplayTableComponent, canActivate:[AuthGuard] },
  {path:'templatetable',component:TemplatetableComponent, canActivate:[AuthGuard]},
  {path: 'projectselector', component: ProjectSelectorComponent},
  {path: 'resourceselect', component: ResourceSelectComponent},
  {path: 'resource', component: ResourceComponent, canActivate:[AuthGuard]},
  // {path:'**', component: ErrorComponent}
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule],
providers: [AuthGuard]
})
export class AppRoutingModule { }
