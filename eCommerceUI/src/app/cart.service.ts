import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItems } from './model/CartItems';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseURL = 'http://localhost:8080/';
  private cart = this.baseURL + 'cart';
  constructor(private http: HttpClient) { }
  
  addOneCartItem(cartItem :CartItems): Observable<any> {
  
    return this.http.post(this.cart + '/add',cartItem);
  }

  getOneCartItem(userId:number, productId: number):  Observable<CartItems> {
    
    return this.http.get<CartItems>(this.cart+'/item/?userId='+userId+'&'+'productId='+productId);

  }
}
