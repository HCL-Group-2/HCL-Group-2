import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartItems } from './model/CartItems';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseURL = 'http://localhost:8081/ecommerce/';

  constructor(private http: HttpClient) { }
  
  addOneCartItem(cartItem :CartItems): Observable<any> {
  
    return this.http.post(this.baseURL + 'cartitem/',cartItem);
  }


}
