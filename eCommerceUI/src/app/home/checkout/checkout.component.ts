import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckoutService } from '../checkout.service';
import { UserCheckout, Address, CreditCard, Checkout } from '../model/Checkout';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  user !: UserCheckout;
  address !: Address;
  creditCard!: CreditCard;
    
  constructor(private route: ActivatedRoute,
  private router: Router, private checkoutService: CheckoutService) { }

  ngOnInit(): void {
    this.addOneOrder({ quantity: 1, user: { id: 1 }, address: { id: 1 }, creditCard: { id: 1 }  });
  }
  addOneOrder(checkoutOrder: Checkout) {

    this.checkoutService.addOneOrder(checkoutOrder).subscribe();
  }
}
