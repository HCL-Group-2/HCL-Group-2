import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Product } from 'src/app/model/Product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  productAddForm !: FormGroup;
  newProduct !: Product;
  // https://github.com/RameshMF/Angular-10-Spring-Boot-CRUD-Full-Stack-App/blob/master/angular-frontend/src/app/create-employee/create-employee.component.html
  constructor( private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    
    this.productAddForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: ['', [Validators.required]],
      image: ['', [Validators.required]],
      category: ['', [Validators.required]],
      inventory: ['', [Validators.required]]

    });
  }

  addProductSubmit(){
    let productName = this.productAddForm.get('name')?.value;
    console.log('name' + productName);
    let productDesc = this.productAddForm.get('description')?.value;
    console.log('desc' + productDesc);
    let productPrice = this.productAddForm.get('price')?.value;
    console.log('price' + productPrice);
    let productImage = this.productAddForm.get('image')?.value;
    console.log('image' + productImage);
    let productCategory = this.productAddForm.get('category')?.value;
    console.log('category' + productCategory);
    let productInventory = this.productAddForm.get('inventory')?.value;
    console.log('inventory' + productInventory);

    this.newProduct = {
      "name": productName,
      "description": productDesc,
      "price": productPrice,
      "image": productImage,
      "category": productCategory,
      "inventory": productInventory
    };

    
    // const addProductdialogRef = this.productAddDialog.open(ProductAddDialog, {
    //   data: { newProduct: this.newProduct },  disableClose: true
    // });


  }

  

}
@Component({
  selector: 'product-add-dialog',
  templateUrl: 'product-add-dialog.html',
})
export class ProductAddDialog {
  constructor(
    public dialogRef: MatDialogRef<ProductAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Product,
  ) {
    dialogRef.disableClose = true;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
