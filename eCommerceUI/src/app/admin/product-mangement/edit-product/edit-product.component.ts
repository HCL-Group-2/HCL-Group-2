import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/Product';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  productId !: number;
  productEditForm !: FormGroup


  editProduct !: Product;


  constructor(private router: Router, private route: ActivatedRoute,
    private productService: ProductService,
    public editProductDialog: MatDialog) {

    this.productId = this.route.snapshot.params['productId'];
    console.log('productId ' + this.productId);

    this.productEditForm = new FormGroup({
      id: new FormControl({ value: this.productId, disabled: true }),
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      image: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      inventory: new FormControl('', Validators.required)
    });

  }

  ngOnInit(): void {


    this.productService.getProductById(this.productId).subscribe(data => {
      this.productEditForm.setValue({
        id: data.id,
        name: data.name,
        description: data.description,
        price: data.price,
        image: data.image,
        category: data.category,
        inventory: data.inventory,
      });

    }
    );

  }

  editProductSubmit(event: any) {
    console.log('id in form ' + this.productEditForm.get('id')?.value);
    console.log('form to object' + JSON.stringify(this.productEditForm.value));

    let productName = this.productEditForm.get('name')?.value;
    console.log('name' + productName);
    let productDesc = this.productEditForm.get('description')?.value;
    console.log('desc' + productDesc);
    let productPrice = this.productEditForm.get('price')?.value;
    console.log('price' + productPrice);
    let productImage = this.productEditForm.get('image')?.value;
    console.log('image' + productImage);
    let productCategory = this.productEditForm.get('category')?.value;
    console.log('category' + productCategory);
    let productInventory = this.productEditForm.get('inventory')?.value;
    console.log('inventory' + productInventory);

    this.editProduct = {
      "id": this.productId,
      "name": productName,
      "description": productDesc,
      "price": productPrice,
      "image": productImage,
      "category": productCategory,
      "inventory": productInventory
    };

    console.log('edit product: ' + JSON.stringify(this.editProduct));
    this.productService.updateProduct(this.editProduct).subscribe();

    const dialogRef = this.editProductDialog.open(ProductEditDialog, {
      data: {
        name: ' in the ProductEditDialog placeholder',
      }, disableClose: true
    },);

    dialogRef.afterClosed().subscribe(() => {
      console.log('edit product dialog box is closed.');
      this.router.navigate(['admin/productManagement']);
      // window.location.reload();
    });




  }

}
@Component({
  selector: 'product-edit-dialog',
  templateUrl: 'product-edit-dialog.html',
})
export class ProductEditDialog {
  constructor(
    public dialogRef: MatDialogRef<ProductEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {
    dialogRef.disableClose = true;
  }

  onNoClick(): void {

    this.dialogRef.close();
  }
}

