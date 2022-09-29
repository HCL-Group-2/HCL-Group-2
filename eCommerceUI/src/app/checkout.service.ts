import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Checkout } from './model/Checkout';
import { CheckoutOrder } from './model/CheckoutOrder';
import { PaymentIntent, PaymentIntent2 } from './model/PaymentIntent';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';
  //private baseURL = 'http://localhost:8080/';


  constructor(private http: HttpClient) { }
  
  addOneCheckout(checkoutOrder :CheckoutOrder): Observable<any> {
  
    return this.http.post(this.baseURL + 'order/',checkoutOrder);
  }

  paymentIntent(paymentIntent: PaymentIntent): Observable<any> {
    
    return this.http.post(this.baseURL + 'create-intent/', paymentIntent);
  }

  
  createPaymentIntent(paymentIntent2:  PaymentIntent2): Observable<any> {
    return this.http.post<PaymentIntent2>(this.baseURL + 'payment-intent/', paymentIntent2);
  }


}
