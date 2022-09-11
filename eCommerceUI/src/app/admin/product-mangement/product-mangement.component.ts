import { Component, Inject, OnInit } from '@angular/core';
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
  constructor( private productService: ProductService, public productRemoveDialog: MatDialog) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    }

    );
  }

  deleteProductFromCatalog(event: any, productId: number){

    
    if(productId !== undefined){
      //this.productService.deleteProduct(productId).subscribe();
      this.productRemoveDialog.open(ProductManagementRemoveWarningDialog, {
        data: {
          name: 'in the ProductManagementRemoveWarningDialog placeholder',  
          product_ID: productId
        }, disableClose: true 
      }, );

    }

  }

}
@Component({
  selector: 'productManagementRemoveWarning',
  templateUrl: 'productManagementRemoveWarning.html',
})
export class ProductManagementRemoveWarningDialog {
  constructor( public dialogRef: MatDialogRef<ProductManagementRemoveWarningDialog>, private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { name: string, product_ID: number }) {
      dialogRef.disableClose = true;

     }

  onNoClick(): void {
    this.dialogRef.close();
    //this.router.navigate(['/admin/productManagement']);
    //window.location.reload();

  }
  remove(): void {
    console.log(this.data.product_ID);
    //this.dialogRef.close();
    //this.router.navigate(['/admin/productManagement']);
    //window.location.reload();

  }


}

