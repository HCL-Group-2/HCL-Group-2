import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { CheckoutComponent } from './checkout.component';

describe('CheckoutComponent', () => {
  let component: CheckoutComponent;
  let fixture: ComponentFixture<CheckoutComponent>;

  beforeEach(async () => {
    
    window.Stripe = function () {
      // your mock here
      return {
        elements: () => ({
          create: () => ({
            mount: () => ({ /* your card */ })
          })
        })
      }
    }

    await TestBed.configureTestingModule({
      imports:[HttpClientModule,MatDialogModule],
      providers:[HttpClient, FormBuilder],
      declarations: [CheckoutComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(CheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
