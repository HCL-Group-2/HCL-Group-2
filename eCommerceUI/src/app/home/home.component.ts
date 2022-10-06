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
  public email$!: Observable<string>;

  email: string = "";
  products !: Array<Product>;
  searchProducts !: Array<Product>;
  user !: User;
  selectedQuantity: number = 0;
  selectedProduct !: CartItems;
  cartQuantityForm: FormGroup = new FormGroup([]);

  turnOnAddToCart: boolean = false;
  search: boolean = true;
  searchText: string = '';

  isLoggedinFromOkta = false;

  //Use this.storage.getKey('userId;) to retrive the userId of the logged in user
  storage: Storage = sessionStorage;
  localStorage: Storage = localStorage;


  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private cartService: CartService,
    public cartDialog: MatDialog,
    private _oktaAuthStateService: OktaAuthStateService
  ) {
    console.log('at customer home page constructor');

  }



  ngOnInit(): void {

    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)
    let userId = +this.storage.getItem('userId')!;
    console.log('userId from session storage ' + userId);



    this.getSearchBool();
    console.log('this.search ' + this.search);
    this.getUser();

    if (this.storage.getItem('search') == 'true') {
      if (this.storage.getItem('category') == 'toys') {
        this.getProductsByCategory('toys');
      } else if (this.storage.getItem('category') == 'clothing') {
        this.getProductsByCategory('clothing');
      } else if (this.storage.getItem('category') == 'electronics') {
        this.getProductsByCategory('electronics');
      }
      else {

        this.getProducts();
      }

    }
    else {
      console.log('getSearchProducts()');
      this.getSearchProducts();
    }

    this.name$ = this._oktaAuthStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? ''));

  
    this._oktaAuthStateService.authState$.subscribe(data => {

      this.email = data.idToken?.claims.email!;
    });
    //console.log('this.email outside ' +   this.email );
    this.cartQuantityForm = this.formBuilder.group({
      quantity: ['', [Validators.required]]
    });


  }

  getUser() {
    this.userService.getUser(Number(this.storage.getItem('userId'))).subscribe(data => {
      this.user = data;
    });
  }

  getSearchBool() {
    this.search = (this.storage.getItem('search') === 'true');
  }

  updateUser(user: User) {
    this.userService.updateUser(user).subscribe();
  }

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }

  goProductDetails(product: Product) {
    this.storage.setItem('productId', product.id?.toString()!);
    console.log(' goProductDetails(productId: number) product: ' + JSON.stringify(product));
    this.router.navigateByUrl('productDetails', { state: product });

  }

  enableAddCart(event: any) {
    if (event.option.value > 0) {
      this.turnOnAddToCart = true;
    }
  }

  openCartDialog(event: any, productID: number) {
    if (productID != undefined) {
      console.log('product id selected ' + productID);

      console.log('selected item quantity ' + this.selectedQuantity);

      let itemCount = this.cartQuantityForm.get('quantity')?.value;
      if (itemCount != null && this.user.id !== undefined) {
        console.log('user id from cookies ' + this.user.id);
        this.selectedProduct = { 'quantity': +itemCount, 'user': { 'id': this.user.id }, 'product': { 'id': productID } };
        this.cartService.addOneCartItem(this.selectedProduct).subscribe();

        const dialogRef = this.cartDialog.open(CartDialog, {
          data: {
            name: ' in the cart placeholder',
          }, disableClose: true
        });

        dialogRef.afterClosed().subscribe(() => {
          console.log('edit product dialog box is closed.');
          window.location.reload();
        });
      }
    }
    return undefined;
  }

  getSearchProducts() {

    this.productService.getProductsBySearch(this.searchText).subscribe(data => {
      this.searchProducts = data;
    }
    );

  }

  getProductsByCategory(category: string) {
    this.productService.getProductByCategory(category).subscribe(data => {
      this.products = data;
    }
    );
  }

}
@Component({
  selector: 'cartDialog-dialog',
  templateUrl: 'cartDialog-dialog.html',
})
export class CartDialog {
  constructor(public dialogRef: MatDialogRef<CartDialog>,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) {
    dialogRef.disableClose = true;

  }

  onNoClick(): void {
    this.dialogRef.close();
    // window.location.reload();

  }

}
