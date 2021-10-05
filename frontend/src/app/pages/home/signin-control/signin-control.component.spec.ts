import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigninControlComponent } from './signin-control.component';

describe('SigninControlComponent', () => {
  let component: SigninControlComponent;
  let fixture: ComponentFixture<SigninControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SigninControlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SigninControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
