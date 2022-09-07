import { Component, OnInit } from '@angular/core';
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
  itemsInCartCount !: Array<CartItems2>;
  a_cart_count$ !: Observable<number>;

  loggedIn = true;

  products !: Array<Product>;


  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService,
    private userService: UserService,
    private productService: ProductService,) { }

  ngOnInit(): void {
    console.log('hello from header');
    this.cartService.getCartItems(1).subscribe(data => {
      this.itemsInCartCount = data;
      // this.a_cart_count$ = of(this.itemsInCartCount.length);
    })

  }
  goToHomePage(){
    this.router.navigate(['/home']);

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

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    }
    );
  }



}
