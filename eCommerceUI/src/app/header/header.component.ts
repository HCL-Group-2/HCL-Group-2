import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { lastValueFrom, Observable, of } from 'rxjs';
import { CartService } from '../cart.service';
import { CartItems2 } from '../model/CartItems';
import { Product } from '../model/Product';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  itemsInCartCount : number = 0;
  a_cart_count$ !: Observable<number>;
  storage: Storage = sessionStorage;
  searchText: string = '';
  searchForm: FormGroup = new FormGroup([]);

  loggedIn = true;

  products !: Array<Product>;


  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    let userId = +this.storage.getItem('userId')!;
    this.cartService.getCartItems(userId).subscribe(data => {
      data.forEach((element: any) => {
        this.itemsInCartCount += element.quantity;
    });
    })
    this.searchForm = this.fb.group({
      searchText:[null, [Validators.required]]});
    

  }
  goToHomePage(){
    this.router.navigate(['/home']);
    this.storage.setItem('search', 'true');

  }
  goToCart(){
  // https://dev.to/isamrish/how-to-display-observable-of-an-object-in-angular-22em
    this.router.navigate(['/cart']);
  }

  goToOrderStatus(){
    this.router.navigate(['/order']);
  }

  goToRegister(){
    this.router.navigate(['/user']);
  }

  goToAccount(){
    this.router.navigate(['/account']);
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  public logout() {
    this.loggedIn = false;
    this.userService.clear();
    this.goToLogin();
    //window.location.reload();
  }

  searchSubmit(){
    var search = 'false';
    this.storage.setItem('search', search);
    this.storage.setItem('searchText', this.searchForm.value.searchText);
    window.location.reload();
  }

}
