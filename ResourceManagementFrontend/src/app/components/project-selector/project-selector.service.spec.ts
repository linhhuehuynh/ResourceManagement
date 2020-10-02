import { TestBed } from '@angular/core/testing';

import { ProjectSelectorService } from './project-selector.service';

describe('ProjectSelectorService', () => {
  let service: ProjectSelectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectSelectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
