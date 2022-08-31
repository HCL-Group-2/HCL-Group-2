import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
// import { OktaService } from '../authenticate/okta.service';
import { CartService } from '../cart.service';
import { CartItems, ProductCart, UserCart } from '../model/CartItems';
import { Product } from '../model/Product';
import { User } from '../model/User';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
//https://developer.okta.com/blog/2017/04/17/angular-authentication-with-oidc
  user !: UserCart;
  product !: ProductCart;
  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService) { }


  ngOnInit(): void {
   //this.addOneCartItem({ quantity: 2, user: { id: 1 }, product: { id: 2 } });
    
   
   // testing purpose
    // const claims = this.oktaService.getClaims();
    // console.log("claims from cart component " + claims);
    // console.log("claims from cart component email" + claims['email']);

    // if (claims) {
    //   console.log(claims);
      



    // }
  }

  addOneCartItem(cartItem: CartItems) {

    this.cartService.addOneCartItem(cartItem).subscribe();

  }
  checkoutPage(){
    this.router.navigate(['/checkout']);
  }


}

  


