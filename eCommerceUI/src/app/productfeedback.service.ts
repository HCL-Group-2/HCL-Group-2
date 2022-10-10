import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductFeedback } from './model/ProductFeedback';

@Injectable({
  providedIn: 'root'
})
export class ProductFeedbackService {

  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

  constructor(private http: HttpClient) { }
  
  addProductFeedback(newProductFeedback: ProductFeedback):Observable<any>{
    return this.http.post(this.baseURL + 'productfeedback', newProductFeedback);
  }
  
}

