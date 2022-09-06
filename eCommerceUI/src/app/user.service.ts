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
  }

  getUserByEmail(email: string):Observable<User>{
      // localhost:8081/ecommerce/byEmail?email=testuser@gmail.com
    return this.http.get<User>(this.baseURL + 'byEmail?email='+ email);
  }


  saveUser(user: User): Observable<User>{
    return this.http.post<User>(this.baseURL + 'user', user)
  }

<<<<<<< HEAD
  getUserByEmail(email: string):Observable<User>{
    return this.http.get<User>(this.baseURL + 'byEmail/?email=' + email);
  }
=======
>>>>>>> parent of 811c63b (Revert "Merge branch 'main' into chrisbranch")
  saveAddress(address: Address): Observable<Address>{
    return this.http.post<Address>(this.baseURL+'address', address)

  }

  public setLoggedIn(loggedIn : string) {
    localStorage.setItem('loggedIn', loggedIn);
  }

  public getLoggedIn() : string {
    return localStorage.getItem('loggedIn') as string;
  }

  public clear() {
    localStorage.clear();
  }

}
