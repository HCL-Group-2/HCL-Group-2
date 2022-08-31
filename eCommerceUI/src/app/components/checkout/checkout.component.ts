import { Component, OnInit } from '@angular/core';
import { CheckoutOrder } from 'src/app/model/CheckoutOrder';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutOrder:CheckoutOrder = new CheckoutOrder(); 
  
  isDisabled:boolean=false;

  constructor() { }

  ngOnInit(): void {
  }

}
