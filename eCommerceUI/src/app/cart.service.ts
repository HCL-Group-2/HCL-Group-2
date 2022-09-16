import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';


import { CartItems, CartItems2 } from './model/CartItems';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  //private baseURL = 'http://localhost:7777/ecommerce/';
  private baseURL = 'http://localhost:7777/';


  constructor(private http: HttpClient) { }

  addOneCartItem(cartItem: CartItems): Observable<any> {

    return this.http.post(this.baseURL + 'cartitem/', cartItem);
  }

  getCartItems(userId: number): Observable<any> {
    return this.http.get(this.baseURL + 'cartitems/' + userId);

  }

  deleteCartItem(cartId: number): Observable<any>{
    return this.http.delete(this.baseURL + 'cartitem/' + cartId);
  }

  




}
