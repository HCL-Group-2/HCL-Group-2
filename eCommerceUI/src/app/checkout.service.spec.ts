import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { CheckoutService } from './checkout.service';

describe('CheckOutService', () => {
  let service: CheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientModule ]
    });
    service = TestBed.inject(CheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
