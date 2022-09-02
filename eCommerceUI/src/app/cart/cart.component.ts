import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  user !: UserCart;
  product !: ProductCart;
  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService) { }


  ngOnInit(): void {
    this.addOneCartItem({ quantity: 2, user: { id: 1 }, product: { id: 4 } });
  }

  addOneCartItem(cartItem: CartItems) {

    this.cartService.addOneCartItem(cartItem).subscribe();

  }


}

  


