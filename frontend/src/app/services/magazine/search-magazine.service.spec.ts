import { TestBed } from '@angular/core/testing';

import { SearchMagazineService } from './search-magazine.service';

describe('SearchMagazineService', () => {
  let service: SearchMagazineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchMagazineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
