import { ElementRef } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CheckoutService } from 'src/app/checkout.service';
import { Address, Checkout, CreditCard, OrderNumber } from 'src/app/model/Checkout';
import { CheckoutOrder } from 'src/app/model/CheckoutOrder';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutOrder:CheckoutOrder = new CheckoutOrder(); 
  form: FormGroup = new FormGroup([]);
  
  checkout!: Checkout;
  address!: Address;
  creditCard!: CreditCard;

  
  isDisabled:boolean=false;

  input!: ElementRef<HTMLInputElement>;


  constructor(private route: ActivatedRoute,
    private router: Router, private checkoutService: CheckoutService , private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      firstName:[null, [Validators.required]],
      lastName:[null, [Validators.required]],
      address:[null, [Validators.required]],
      email:[null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      
    });
  }
  addOneCheckout(checkout: Checkout){
    this.checkoutService.addOneCheckout(this.form.value).subscribe();
  }
}
