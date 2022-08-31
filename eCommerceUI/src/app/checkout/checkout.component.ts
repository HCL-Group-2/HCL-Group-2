import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckoutService } from '../checkout.service';
import { Checkout } from '../model/Checkout';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  //user !: CheckOut;
  //address !: Address;
  //creditCard!: CreditCard;

  checkout:Checkout = {
    firstname: '',
    lastname: '',
    id: 0,
    items: [],
    totalPrice: 0,
    paymentId: '',
    createdAt: '',
    status: '',
    address: ''
  };
  submitted = false;
  constructor(private route: ActivatedRoute,
  private router: Router, private checkoutService: CheckoutService) { }

  ngOnInit(): void {
    //this.addOneCheckout({ quantity: 1, checkout: { id: 1 }, address: { id: 1 }, creditCard: { id: 1 }  });
  }
  addOneCheckout(checkoutOrder: Checkout) {

    this.checkoutService.addOneCheckout(checkoutOrder).subscribe();
  }
  saveCheckout():void{
    const data = {
      firstname: this.checkout.firstname,
      lastname: this.checkout.lastname,
      id: this.checkout.id,
      items: this.checkout.items,
      totalPrice: this.checkout.totalPrice,
      address: this.checkout.address,
      paymentId: this.checkout.paymentId,
      createdAt: this.checkout.createdAt,
      status: this.checkout.status,
    };
    this.checkoutService.postCheckout(this.checkout)
    .subscribe({
      next:(res: any)=>{
        console.log(res);
        this.submitted = true;
      },
      error:(e: any)=>console.error(e)
    });
  }
  }
  