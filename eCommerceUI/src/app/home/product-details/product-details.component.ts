import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/Product';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product !: Product;
  constructor(  private router: Router, private route: ActivatedRoute) { 
    
    // this.product = this.route.snapshot.params['product'];
    console.log('passing product object test ' +  JSON.stringify(this.router.getCurrentNavigation()?.extras.state));

    // console.log('product ' + JSON.stringify(this.product));
  }

  ngOnInit(): void {
  }

}
