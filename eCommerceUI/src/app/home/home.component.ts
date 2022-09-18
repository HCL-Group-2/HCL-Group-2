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
  searchProducts ! :Array<Product>;
  user !: User;
  selectedQuantity: number = 0;
  selectedProduct !: CartItems;
  cartQuantityForm: FormGroup = new FormGroup([]);

  turnOnAddToCart : boolean = false;
  search: boolean = true;
  searchText: string = '';

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

//chriswilcox580@gmail.com
//Okta4444

  ngOnInit(): void {

    // getting the user id from login user hardcoding (cannot figure out how to get the user id from login user yet)
    let userId = +this.storage.getItem('userId')!;
    this.getSearchBool();
    console.log(this.search);

    console.log('userId ' + userId);
    
    this.getUser(1);
    if(this.storage.getItem('search')=='true'){
      if(this.storage.getItem('category')=='toys'){
        this.getProductsByCategory('toys');
      }else if(this.storage.getItem('category')=='clothing'){
        this.getProductsByCategory('clothing');
      }else if(this.storage.getItem('category')=='electronics'){
        this.getProductsByCategory('electronics');
      }
      else{
        this.getProducts();
      }

    }
    else{
      this.getSearchProducts();
    }


    this.name$ = this._oktaAuthStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? ''));

    
     this._oktaAuthStateService.authState$.subscribe(data =>{
      console.log('raw email ' + data.idToken?.claims.email);
      console.log('raw authorizeUrl ' + data.idToken?.authorizeUrl);
      this.email = data.idToken?.claims.email!;
      console.log('this.email ' +   this.email );
    });
    console.log('this.email outside ' +   this.email );
    

      this.cartQuantityForm = this.formBuilder.group({
        quantity: ['', [Validators.required]]
      });








  }
  getUser(userId: number) {
    this.userService.getUser(userId).subscribe(data => {
      this.user = data;
    });
  }
  getSearchBool(){
    this.search = (this.storage.getItem('search') ==='true');
   
  }
  updateUser(user: User) {

    this.userService.updateUser(user).subscribe();

  }
  getProducts() {
    console.log('trying to get all products ');
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
        }, disableClose: true 
      });
      console.log('selected item quantity ' + this.selectedQuantity);

      let itemCount = this.cartQuantityForm.get('quantity')?.value; 
      if (itemCount != null && this.user.id !== undefined) {
        console.log('user id from cookies ' + this.user.id); 
        this.selectedProduct = { 'quantity': +itemCount, 'user': { 'id': this.user.id }, 'product': { 'id': productID } };
        this.cartService.addOneCartItem(this.selectedProduct).subscribe();

      }

    }
    return undefined;
  }
  getSearchProducts(){
    this.searchText = this.storage.getItem('searchText')!;
    this.productService.getProductsBySearch(this.searchText).subscribe(data => {
      this.searchProducts = data;
    }
    );
  }

  getProductsByCategory(category: string){
    this.productService.getProductByCategory(category).subscribe(data => {
      this.products = data;  }
    );
    }
    


}
@Component({
  selector: 'cartDialog-dialog',
  templateUrl: 'cartDialog-dialog.html',
})
export class CartDialog {
  constructor( public dialogRef: MatDialogRef<CartDialog>,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) { 
      dialogRef.disableClose = true;

    }

  onNoClick(): void {
    this.dialogRef.close();
    window.location.reload();

  }

}
