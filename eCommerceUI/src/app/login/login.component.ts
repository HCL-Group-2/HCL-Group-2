import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';
import { OktaService } from '../authenticate/okta.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,private oktaService: OktaService) { }
  
  username !: string;
  password !: string;
  name: string = "";

  ngOnInit(): void {
    const claims = this.oktaService.getClaims();
    console.log("claims from login component " + claims);
    console.log("claims from login component email" + claims['email']);

    if (claims) {
      console.log(claims);
      console.log(claims['email']);


    }
  }

  login() : void {
    if(this.username == 'admin' && this.password == 'admin'){
     this.router.navigate(["user"]);
    }else {
      alert("Invalid credentials");
    }
  }

}
