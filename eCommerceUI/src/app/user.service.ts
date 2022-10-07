import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Address } from './model/Address';
import { OktaUser, User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';
  //private baseURL = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  updateUser(user: OktaUser): Observable<OktaUser> {
    return this.http.put<User>(this.baseURL + 'user', user);
  }

  getUser(userId: number): Observable<User> {
    return this.http.get<User>(this.baseURL + 'user/' + userId);
  }
  getAllUsers(): Observable<any> {
    return this.http.get<any>(this.baseURL + 'users');
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(this.baseURL + 'byEmail?email=' + email);
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseURL + 'user', user);
  }

  saveOktaUser(user: OktaUser): Observable<User> {
    return this.http.post<User>(this.baseURL + 'user', user);
  }

  saveAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(this.baseURL + 'address', address);
  }

  public setLoggedIn(loggedIn: string) {
    localStorage.setItem('loggedIn', loggedIn);
  }

  public getLoggedIn(): string {
    return localStorage.getItem('loggedIn') as string;
  }

  public clear() {
    //localStorage.clear();
    window.localStorage.clear();
  }
}

