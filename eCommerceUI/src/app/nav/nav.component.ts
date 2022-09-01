import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  isAuthenticated: boolean = false;

  constructor(private oktaAuthService: OktaAuthService) {
    this.oktaAuthService.$authenticationState.subscribe(
      isAuth => this.isAuthenticated = isAuth
    );
   }

  async ngOnInit() {
    this.isAuthenticated = await this.oktaAuthService.isAuthenticated();
  }

  logout(){
    this.oktaAuthService.signOut();
  }

}