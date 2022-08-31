import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Address } from './model/Address';
import { User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseURL = 'http://localhost:8081/ecommerce/';
  
  constructor(private http: HttpClient) { }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(this.baseURL + 'user', user);
  }


  getUser(userId : number):Observable<User>{
    return this.http.get<User>(this.baseURL + 'user/'+ userId);

  

  saveUser(user: User): Observable<User>{
    return this.http.post<User>(this.baseURL + 'user', user)
  }

  saveAddress(address: Address): Observable<Address>{
    return this.http.post<Address>(this.baseURL+'address', address)

  }
}
