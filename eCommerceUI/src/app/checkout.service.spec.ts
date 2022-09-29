import { TestBed } from '@angular/core/testing';

import { CheckoutService } from './checkout.service';

describe('CheckOutService', () => {
  let service: CheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      
    });
    service = TestBed.inject(CheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
