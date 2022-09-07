import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { lastValueFrom, Observable, of } from 'rxjs';
import { CartService } from '../cart.service';
import { CartItems2 } from '../model/CartItems';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  itemsInCartCount !: Array<CartItems2>;
  a_cart_count$ !: Observable<number>;
  storage: Storage = sessionStorage;


  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    //console.log('hello from header');
    let userId = +this.storage.getItem('userId')!;
    this.cartService.getCartItems(userId).subscribe(data => {
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

 

}
