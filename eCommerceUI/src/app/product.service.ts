import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './model/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = 'http://localhost:9091/ecommerce/';

  constructor(private http: HttpClient) { }

  getProducts():Observable<any>{
    return this.http.get(this.baseURL + 'products');

  }

  getProductsBySearch(searchText: string):Observable<any>{
    return this.http.get(this.baseURL + 'productsbyname?name='+ searchText);
  }

  getProductByCategory(category: string):Observable<any>{
    return this.http.get(this.baseURL + 'productsbycategory?category='+category);
  }
  
  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.baseURL + 'product', product);
  }

  saveProduct(product: Product): Observable<Product>{
    return this.http.post<Product>(this.baseURL + 'product', product)
  }

  deleteProduct(product: Product): Observable<Product>{
    return this.http.delete<Product>(this.baseURL)
  }


}
