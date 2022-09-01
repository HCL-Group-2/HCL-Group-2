import { Component, Inject, OnDestroy, OnInit } from '@angular/core';

import myAppConfig from 'src/app/config/my-app-config';

import * as OktaSignIn from '@okta/okta-signin-widget';

import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { Router } from '@angular/router';
import { OktaAuth, AuthState } from '@okta/okta-auth-js';
import { filter, map, Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public isAuthenticated$!: Observable<boolean>;
  public name$!: Observable<string>;

  constructor(private _router: Router, private _oktaStateService: OktaAuthStateService, @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth) { }

  public ngOnInit(): void {
    this.isAuthenticated$ = this._oktaStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );
    this.getUserDetails();
  }

  getUserDetails(){
    this.name$ = this._oktaStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? '')
    );
  }

  public async oktaLogin() : Promise<void> {
    await this._oktaAuth.signInWithRedirect({originalUri: "/loginSuccess"}).then(
      _ => this._router.navigate(['/home'])
    );
  }

  public async oktaLogout(): Promise<void> {
    await this._oktaAuth.signOut();
  }

}