import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
// import { OktaService } from '../authenticate/okta.service';
import { CartService } from '../cart.service';
import { CartItems, CartItems2, ProductCart, UserCart } from '../model/CartItems';
import { Product } from '../model/Product';
import { User } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  //https://developer.okta.com/blog/2017/04/17/angular-authentication-with-oidc
  // user !: User;
  itemsInCart !: Array<CartItems2>;
  userIdFromUser !: number;
  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService, private cartService: CartService) { }


  ngOnInit(): void {
    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)

    this.getCartItems(1);



  }


  getCartItems(userId: number) {


    this.cartService.getCartItems(userId).subscribe(data => {
      this.itemsInCart = data;
      console.log(JSON.stringify(this.itemsInCart ));
    }

    );
  }

  
  checkoutPage(){
    this.router.navigate(['/checkout']);
  }


}




