import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/Product';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-product-mangement',
  templateUrl: './product-mangement.component.html',
  styleUrls: ['./product-mangement.component.css']
})
export class ProductMangementComponent implements OnInit {
  products !: Array<Product>;


  constructor(private productService: ProductService,
    private router: Router,
   public productAddDialog: MatDialog) { }

  ngOnInit(): void {
 
    this.getProducts();


  }

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    }

    );
  }
  goToAddProductForm() {
    console.log('goToAddProductForm()');
    this.router.navigate(['admin/productManagement/addProduct']);
  }

  goToEditProductForm(productId: number) {
    console.log('goToEditProductForm() productId: ' + productId);
    this.router.navigate(['admin/productManagement/editProduct',productId]);
  }
  

 

}



