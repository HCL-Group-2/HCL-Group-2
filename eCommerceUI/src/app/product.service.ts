import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './model/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

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
  
  getProductById(productId: number):Observable<any>{
    return this.http.get(this.baseURL + 'product/'+productId);
  }
  addProduct(newProduct: Product):Observable<any>{
    return this.http.post(this.baseURL + 'product', newProduct);
  }
  updateProduct(newProduct: Product): Observable<any> {
    return this.http.put<Product>(this.baseURL + 'product', newProduct);
  }

  deleteProduct(productId: number): Observable<any>{
    //localhost:8081/ecommerce/product/1
    return this.http.delete(this.baseURL + 'product/' + productId);
  }

  
 

}
