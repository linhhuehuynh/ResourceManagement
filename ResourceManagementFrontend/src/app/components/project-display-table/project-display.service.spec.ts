import { TestBed } from '@angular/core/testing';

import { ProjectDisplayService } from './project-display.service';

describe('ProjectDisplayService', () => {
  let service: ProjectDisplayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectDisplayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
