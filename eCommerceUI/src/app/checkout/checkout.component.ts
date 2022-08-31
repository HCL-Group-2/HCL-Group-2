import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckoutService } from '../checkout.service';
import { Checkout, Address, CreditCard, CheckOut, } from '../model/Checkout';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  user !: CheckOut;
  address !: Address;
  creditCard!: CreditCard;
    
  constructor(private route: ActivatedRoute,
  private router: Router, private checkoutService: CheckoutService) { }

  ngOnInit(): void {
    this.addOneCheckout({ quantity: 1, checkout: { id: 1 }, address: { id: 1 }, creditCard: { id: 1 }  });
  }
  addOneCheckout(checkoutOrder: Checkout) {

    this.checkoutService.addOneCheckout(checkoutOrder).subscribe();
  }
}
