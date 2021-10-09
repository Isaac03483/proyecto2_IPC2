import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagazinesAdminComponent } from './magazines-admin.component';

describe('MagazinesAdminComponent', () => {
  let component: MagazinesAdminComponent;
  let fixture: ComponentFixture<MagazinesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MagazinesAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MagazinesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
