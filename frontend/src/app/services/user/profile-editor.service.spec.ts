import { TestBed } from '@angular/core/testing';

import { ProfileEditorService } from './profile-editor.service';

describe('ProfileEditorService', () => {
  let service: ProfileEditorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileEditorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
