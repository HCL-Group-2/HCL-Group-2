import { AuthConfig, OAuthService } from "angular-oauth2-oidc";
import { JwksValidationHandler } from 'angular-oauth2-oidc-jwks';

import { Router } from "@angular/router";
import { Injectable } from "@angular/core";

@Injectable()

export class OktaService {
    constructor(private oauthService: OAuthService, private router: Router) {
    }
    public okta: AuthConfig = {
        issuer: 'https://dev-88347192.okta.com/oauth2/default',
        oidc: true,
        redirectUri: window.location.origin,
        postLogoutRedirectUri: window.location.origin,
        clientId: '0oa6bv5wdt8m2uwGY5d7',
        scope: 'openid profile email',
        showDebugInformation: true,
    };
    public configure() {
        const oauthService = this.oauthService;
        const router = this.router;
        oauthService.configure(this.okta);
        oauthService.tokenValidationHandler = new JwksValidationHandler();
        oauthService.showDebugInformation = true;
        oauthService.loadDiscoveryDocument().then((doc) => {
            oauthService.tryLogin({
                onTokenReceived: (context) => {
                    router.navigateByUrl(context.state !); //tell the compiler your are sure the context is not null by using !
                },
                onLoginError: (context) => {
                    console.log(" on login error is called in okta.service");
                    console.log('error', context);
                }
            });
        });
    }

    public getClaims() {
        return this.oauthService.getIdentityClaims();
    }
}