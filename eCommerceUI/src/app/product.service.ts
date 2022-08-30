import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './model/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = 'http://localhost:8081/ecommerce/';

  constructor(private http: HttpClient) { }

  getProducts():Observable<any>{
    return this.http.get(this.baseURL + 'products');

  }
  
 

}
