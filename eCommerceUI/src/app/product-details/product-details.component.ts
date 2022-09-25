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
  
  turnOnAddToCart: boolean = false;

   constructor(  private router: Router,
     private route: ActivatedRoute,
      private productService: ProductService,
      private cartService: CartService,
      public cartDialog: MatDialog) { 

     // this.product = this.route.snapshot.params['product'];
     console.log('passing product object test ' +  JSON.stringify(this.router.getCurrentNavigation()?.extras.state));

     // console.log('product ' + JSON.stringify(this.product));
   }

   enableAddCart(event: any) {
    if (event.option.value > 0) {
      this.turnOnAddToCart = true;
    }
  }

   openCartDialog(event: any, productID: number) {
    if (productID != undefined) {
      console.log('product id selected ' + productID);
    
      console.log('selected item quantity ' + this.selectedQuantity);

      let itemCount = this.cartQuantityForm.get('quantity')?.value;
      if (itemCount != null && this.user.id !== undefined) {
        console.log('user id from cookies ' + this.user.id);
        this.selectedProduct = { 'quantity': +itemCount, 'user': { 'id': this.user.id }, 'product': { 'id': productID } };
        this.cartService.addOneCartItem(this.selectedProduct).subscribe();

        const dialogRef = this.cartDialog.open(CartDialog, {
          data: {
            name: ' in the cart placeholder',
          }, disableClose: true
        });
    
        dialogRef.afterClosed().subscribe(() => {
          console.log('edit product dialog box is closed.');
          window.location.reload();
        });
      }
    }
    return undefined;
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