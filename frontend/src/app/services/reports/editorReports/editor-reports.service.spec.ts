import { TestBed } from '@angular/core/testing';

import { EditorReportsService } from './editor-reports.service';

describe('EditorReportsService', () => {
  let service: EditorReportsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditorReportsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
