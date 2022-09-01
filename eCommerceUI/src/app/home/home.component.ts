import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../cart.service';
import { CartItems } from '../model/CartItems';
import { Product } from '../model/Product';
import { User } from '../model/User';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  name: string = "";
  products !: Array<Product>;
  user !: User;
  selectedQuantity: number = 0;
  selectedProduct !: CartItems;
  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService,
    private productService: ProductService,
    private cartService: CartService,
    public cartDialog: MatDialog
  ) { }

  myControl = new FormControl('');
  options: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];


  ngOnInit(): void {

    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)
    this.getUser(1);
    this.getProducts();


  }
  getUser(userId: number) {
    this.userService.getUser(userId).subscribe(data => {
      this.user = data;
    })
  }
  updateUser(user: User) {

    this.userService.updateUser(user).subscribe();

  }
  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    }

    );
  }

  openCartDialog(event: any, productID: number) {

    if (productID != undefined) {
      console.log('product id selected ' + productID);
      this.cartDialog.open(CartDialog, {
        data: {
          name: ' in the cart placeholder',
        },
      });
      console.log('selected item quantity ' + this.selectedQuantity);
      
      let itemCount = this.myControl.value;
      if(itemCount != null){
        this.selectedProduct = { 'quantity': +itemCount  , 'user': { 'id': 1 }, 'product': { 'id': productID } };
        this.cartService.addOneCartItem(this.selectedProduct).subscribe();

      }

    }
    return undefined;
  }


}
@Component({
  selector: 'cartDialog-dialog',
  templateUrl: 'cartDialog-dialog.html',
})
export class CartDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: {name: string}) {}
}
