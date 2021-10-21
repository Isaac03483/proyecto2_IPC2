import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateSubscriptionComponent } from './generate-subscription.component';

describe('GenerateSubscriptionComponent', () => {
  let component: GenerateSubscriptionComponent;
  let fixture: ComponentFixture<GenerateSubscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateSubscriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
