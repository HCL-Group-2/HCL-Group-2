import { Component, Inject, OnDestroy, OnInit } from '@angular/core';

import myAppConfig from 'src/app/config/my-app-config';

import * as OktaSignIn from '@okta/okta-signin-widget';

import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { Router } from '@angular/router';
import { OktaAuth, AuthState } from '@okta/okta-auth-js';
import { filter, map, Observable } from 'rxjs';
import { User } from '../model/User';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

  public isAuthenticated$!: Observable<boolean>;
  public name$!: Observable<string>;
  storage: Storage =sessionStorage;

  loggedIn = false;

  user = new User();
  response : any;
  msg = '';

  constructor(private userService: UserService, private http:HttpClient, private _router: Router, private _oktaStateService: OktaAuthStateService, @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth) { }

  public ngOnInit(): void {
    this.isAuthenticated$ = this._oktaStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );
    this.getUserDetails();
    console.log("Inside of login.")
  }

  getUserDetails(){
    this.name$ = this._oktaStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? '')
    );
  }

  public async oktaLogin() : Promise<void> {
    console.log("Okta login function run.");
    await this._oktaAuth.signInWithRedirect({originalUri: "/home"});
  }

  public async oktaLogout(): Promise<void> {
    await this._oktaAuth.signOut();
  }

  public loginUser() {
    let loginRequest : any = {
      "email": this.user.email,
      "password": this.user.password
    }

    this.http.post<any>(this.baseURL + 'login', loginRequest).subscribe((response: any) => {
      this.loggedIn = true;
      this.userService.setLoggedIn("true");
      this.storage.setItem('userEmail', (this.user.email));
      this.userEntity();
      this._router.navigate(['/home']).then(() => {
        window.location.reload();
      });
     
    },
    (error) => {
      console.log(error);
      this.msg = "Bad credentials! Please re-enter email and password.";
    })
   // window.location.reload(); trying to get the new header to show depending on the user role
  }

  public userEntity(){
    this.userService.getUserByEmail(this.user.email).subscribe(data =>{
      this.storage.setItem('userId', data.id as unknown as string);
      this.storage.setItem('firstName', data.firstName);
      this.storage.setItem('lastName', data.lastName);
      console.log('loggin in userEntity() in login component' + this.loggedIn);
      // hardcoded role : backend may change
      //this.storage.setItem('userRole', 'Admin');


    }
    );
  }

  public logout() {
    this.loggedIn = false;
    this.userService.clear();
    console.log('before session storage clear ' + this.storage);
    this.storage.clear();
    console.log('after session storage clear ' + this.storage);

    window.location.reload();
  }

  public isLoggedIn() { 
    return this.userService.getLoggedIn() === "true";
  }

}