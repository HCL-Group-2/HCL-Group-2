import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  isAuthenticated: boolean = false;

  name: string = "";

  constructor(private authService: OktaAuthService) { }

  async ngOnInit() {
    this.isAuthenticated = await this.authService.isAuthenticated();

    if(this.isAuthenticated){
      const userClaims = await this.authService.getUser();
      this.name = userClaims.name || "";
    }
  }

}