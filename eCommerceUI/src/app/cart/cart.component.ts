import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
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
  itemsInCart2 !: Array<CartItems2>;

  userIdFromUser !: number;

  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService, 
    private cartService: CartService, public cartDialog2: MatDialog,) { }


  ngOnInit(): void {
    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)

    this.getCartItems(1);



  }


  getCartItems(userId: number) {

    this.cartService.getCartItems(userId).subscribe(data => {
      this.itemsInCart = data;
  
    }

    );
  }
  

  deleteCartItem(event: any,cartId: number){

    console.log('trying to get cart id in the delete cart function ' + cartId);
    if(cartId !== undefined){
      this.cartService.deleteCartItem(cartId).subscribe();
      this.cartDialog2.open(CartDialog2, {
        data: {
          name: ' in the CartDialog2 placeholder',
        },
      });

    }

  }

  
  checkoutPage(){
 
    this.router.navigate(['/checkout']);
  }


}
@Component({
  selector: 'cartDialog-dialog2',
  templateUrl: 'cartDialog-dialog2.html',
})
export class CartDialog2 {
  constructor( public dialogRef: MatDialogRef<CartDialog2>, private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) { }

  onNoClick(): void {
    this.dialogRef.close();
    this.router.navigate(['/home']);

  }

}





