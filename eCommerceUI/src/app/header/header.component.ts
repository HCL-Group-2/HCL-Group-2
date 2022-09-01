import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Cloudinary, CloudinaryImage} from '@cloudinary/url-gen';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  logo: CloudinaryImage; //ostrichmart logo

  constructor(private route: ActivatedRoute,
    private router: Router) {
      const cld = new Cloudinary({
        cloud:{
          cloudName: 'ecommercehcl'
        }
      });
      this.logo=cld.image('ostrichmart_qu5yud');
    }

  ngOnInit(): void {
  }
  goToHomePage(){
    this.router.navigate(['/home']);

  }
  goToCart(){
    this.router.navigate(['/cart']);
  }
  goToOrderStatus(){
    this.router.navigate(['/order']);
  }

  goToRegister(){
    this.router.navigate(['/user']);
  }

  goToAccount(){
    this.router.navigate(['/account']);
  }

}
