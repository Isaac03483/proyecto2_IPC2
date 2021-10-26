import { TestBed } from '@angular/core/testing';

import { ManagerReportsService } from './manager-reports.service';

describe('ManagerReportsService', () => {
  let service: ManagerReportsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManagerReportsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
