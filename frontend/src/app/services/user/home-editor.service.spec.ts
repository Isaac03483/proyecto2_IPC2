import { TestBed } from '@angular/core/testing';

import { HomeEditorService } from './home-editor.service';

describe('HomeEditorService', () => {
  let service: HomeEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HomeEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
