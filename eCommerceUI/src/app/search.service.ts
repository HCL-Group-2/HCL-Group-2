import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


//const baseUrl = "http://localhost:7777/ecommerce/product/"
const baseUrl = "http://localhost:7777/product/"


@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }


}
