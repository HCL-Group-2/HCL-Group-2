import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/Product';

import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html'
  // styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  productId !: number;
  productToView !: Product;
  constructor(  private router: Router, private route: ActivatedRoute,
    private productService: ProductService) { 
    
    this.productId = this.route.snapshot.params['productId'];

  }

  ngOnInit(): void {
    console.log('product id from home page ' + this.productId);
    this.productService.getProductById(this.productId).subscribe(data =>{
      this.productToView = data
    });

  }

}
