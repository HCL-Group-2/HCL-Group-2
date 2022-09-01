import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from 'src/app/cart.service';
import { CartItems2 } from 'src/app/model/CartItems';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutForm: FormGroup = new FormGroup([]);
  cartItemsCheckout !: Array<CartItems2>
  checkoutTotal : number = 0;
  constructor(private formBuilder: FormBuilder,  private cartService: CartService) {

  }

  ngOnInit(): void {

    this.checkoutForm = this.formBuilder.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      shippingaddress: this.formBuilder.group({
        address1: ['', [Validators.required]],
        address2: [''],
        city: ['', [Validators.required]],
        state: ['', [Validators.required]],
        zipcode: ['', [Validators.required]]
      }),
      payment: this.formBuilder.group({
        nameOnCard: ['', [Validators.required]],
        creditCardNumber: ['', [Validators.required]],
        expirationDate: ['', [Validators.required]],
        verificationCode: ['', [Validators.required]]
      })
    })

    this.getCartItems(1);

  }


  getCartItems(userId: number) {

    this.cartService.getCartItems(userId).subscribe(data => {
      this.cartItemsCheckout = data;
      for (let cc in this.cartItemsCheckout) { 
         this.checkoutTotal += (this.cartItemsCheckout[cc].quantity * this.cartItemsCheckout[cc].product.price);
     }
     
Source: https://www.holadevs.com/pregunta/107232/how-can-i-add-up-the-total-in-ngfor
      console.log(JSON.stringify(this.cartItemsCheckout));
    }

    );
  }


}
