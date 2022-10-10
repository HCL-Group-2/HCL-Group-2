import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackForm2Component } from './feedback-form2.component';

describe('FeedbackForm2Component', () => {
  let component: FeedbackForm2Component;
  let fixture: ComponentFixture<FeedbackForm2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackForm2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackForm2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
