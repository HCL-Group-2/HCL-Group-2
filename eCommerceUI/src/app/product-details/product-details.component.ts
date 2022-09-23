import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/Product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { FormGroup } from '@angular/forms';
import { User } from '../model/User';
import { CartItems } from '../model/CartItems';
import { CartDialog } from '../home/home.component';
import { MatDialog } from '@angular/material/dialog';
import { CartService } from '../cart.service';



@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product !: Product;
  storage: Storage = sessionStorage;
  cartQuantityForm: FormGroup = new FormGroup([]);
  user !: User;
  selectedQuantity: number = 0;
  selectedProduct !: CartItems;
  
   constructor(  private router: Router,
     private route: ActivatedRoute,
      private productService: ProductService,
      private cartService: CartService,
      public cartDialog: MatDialog) { 

     // this.product = this.route.snapshot.params['product'];
     console.log('passing product object test ' +  JSON.stringify(this.router.getCurrentNavigation()?.extras.state));

     // console.log('product ' + JSON.stringify(this.product));
   }

  ngOnInit(): void {
    this.getProduct();
    console.log(this.product);
    
  
  }

  getProduct(){
    this.productService.getProductById(Number(this.storage.getItem('productId'))).subscribe(data => {
      this.product= data;
      console.log("Occupying product with "+this.storage.getItem('productId'));
      console.log(this.product);
      
    }); 
  }


}