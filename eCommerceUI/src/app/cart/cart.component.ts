import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../cart.service';
import { CartItems } from '../model/CartItems';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor( private route: ActivatedRoute,
    private router: Router, private cartService: CartService  ) { }
    

  ngOnInit(): void {
    this.getOneCartItem(9,11);
    this.addOneCartItem({cartItemsPKID:{userId:17, productId:17},count:1});
  }

  addOneCartItem(cartItem :CartItems){

    this.cartService.addOneCartItem(cartItem).subscribe();

  }

  getOneCartItem(userId: number, productId: number){
    this.cartService.getOneCartItem(userId,productId).subscribe(
      data => {
      console.log(JSON.stringify(data));
    })

  }

  

}
