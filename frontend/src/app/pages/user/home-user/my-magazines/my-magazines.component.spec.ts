import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyMagazinesComponent } from './my-magazines.component';

describe('MyMagazinesComponent', () => {
  let component: MyMagazinesComponent;
  let fixture: ComponentFixture<MyMagazinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyMagazinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyMagazinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
