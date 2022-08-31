import { Component, OnInit } from '@angular/core';

//import { OktaService } from './authenticate/okta.service';
import { OAuthService } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import {Cloudinary, CloudinaryImage} from '@cloudinary/url-gen';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'eCommerceUI';
  img: CloudinaryImage;//Variable to hold the image object



  //In constructor I have created the cloud connection to the 'demo' cloud, but we can choose which one is being connected
  constructor(private router: Router) {
      const cld = new Cloudinary({
        cloud:{
          cloudName: 'demo'
        }
      });
    //This is a demo image on a public cloud
    this.img = cld.image('docs/models');
   }

}
