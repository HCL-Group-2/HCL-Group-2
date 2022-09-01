import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { search } from '../models/search.model';

const baseUrl = "http://localhost:8081/ecommerce/product/"

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<search[]> {
    return this.http.get<search[]>(baseUrl);
  }

  get(id: any): Observable<search> {
    return this.http.get<search>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<search[]> {
    return this.http.get<search[]>(`${baseUrl}?title=${title}`);
  }
}
