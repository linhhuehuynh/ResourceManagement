import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectDisplayTableComponent } from './project-display-table.component';

describe('ProjectDisplayTableComponent', () => {
  let component: ProjectDisplayTableComponent;
  let fixture: ComponentFixture<ProjectDisplayTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectDisplayTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectDisplayTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
