import { TestBed } from '@angular/core/testing';

import { ResourceSelectService } from './resource-select.service';

describe('ResourceSelectService', () => {
  let service: ResourceSelectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResourceSelectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
