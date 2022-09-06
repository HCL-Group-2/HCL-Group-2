import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Checkout } from './model/Checkout';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private baseURL = 'http://localhost:8081/ecommerce/';

  constructor(private http: HttpClient) { }
  
  addOneCheckout(checkout :Checkout): Observable<any> {
  
    return this.http.post(this.baseURL + 'order/',checkout);
  }


}
