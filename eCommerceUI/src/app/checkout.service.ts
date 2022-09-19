import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Checkout } from './model/Checkout';
import { CheckoutOrder } from './model/CheckoutOrder';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

  constructor(private http: HttpClient) { }
  
  addOneCheckout(checkoutOrder :CheckoutOrder): Observable<any> {
  
    return this.http.post(this.baseURL + 'order/',checkoutOrder);
  }


}
