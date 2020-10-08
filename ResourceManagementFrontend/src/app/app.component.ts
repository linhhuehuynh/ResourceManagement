import { Component, OnInit } from '@angular/core';
import { AuthService } from './components/auth/auth.service';
import { TemplateService } from './components/template/templatetable/template.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ResourceManagementFrontend';

    constructor(
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.authService.autoAuthUser();
    
  }
}
