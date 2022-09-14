import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Order } from "./model/Order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseURL = 'http://localhost:8081/ecommerce/';

  constructor(private http: HttpClient) { }

  getOrder( orderId : number): Observable<Order> {
    return this.http.get<Order>(this.baseURL + 'user/' + orderId);
  }



  getUserOrders(userId: number): Observable<any> {
    return this.http.get(this.baseURL + 'user/' + userId + '/orders');

  }
}
