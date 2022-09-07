import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OktaAuthStateService } from '@okta/okta-angular';
import { AuthState } from '@okta/okta-auth-js';
import { Observable, filter, map } from 'rxjs';
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

  public name$!: Observable<string>;

  name: string = "";
  products !: Array<Product>;
  user !: User;
  selectedQuantity: number = 0;
  selectedProduct !: CartItems;
  cartQuantityForm: FormGroup = new FormGroup([]);

  turnOnAddToCart : boolean = false;

  //Use this.storage.getKey('userId;) to retrive the userId of the logged in user
  storage: Storage = sessionStorage;
  



  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private cartService: CartService,
    public cartDialog: MatDialog,
    private _oktaAuthStateService: OktaAuthStateService
  ) { }



  ngOnInit(): void {

    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)
    let userId = +this.storage.getItem('userId')!;
    
    this.getUser(userId);
    this.getProducts();


    this.name$ = this._oktaAuthStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? ''));

      this.cartQuantityForm = this.formBuilder.group({
        quantity: ['', [Validators.required]]
      })




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
  enableAddCart(event: any) {
    if(event.option.value > 0){
      this.turnOnAddToCart = true;
    }
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

      let itemCount = this.cartQuantityForm.get('quantity')?.value; 
      if (itemCount != null) {

        this.selectedProduct = { 'quantity': +itemCount, 'user': { 'id': this.user.id }, 'product': { 'id': productID } };
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
  constructor( public dialogRef: MatDialogRef<CartDialog>,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
