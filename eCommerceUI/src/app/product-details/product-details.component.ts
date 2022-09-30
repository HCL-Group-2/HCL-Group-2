import { Component, OnInit, Inject } from '@angular/core';
import { Product } from 'src/app/model/Product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../model/User';
import { CartItems } from '../model/CartItems';
import { CartService } from '../cart.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';



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
  userId: number = -1;
  
  turnOnAddToCart: boolean = false;

   constructor(  private router: Router,
      private route: ActivatedRoute,
      private productService: ProductService,
      private cartService: CartService,
      private formBuilder: FormBuilder,
      public atcDialog: MatDialog) { 

     // this.product = this.route.snapshot.params['product'];
     console.log('passing product object test ' +  JSON.stringify(this.router.getCurrentNavigation()?.extras.state));

     // console.log('product ' + JSON.stringify(this.product));
   }
   ngOnInit(): void {
    this.getProduct();
    this.userId = Number(this.storage.getItem('userId'));
    console.log(this.product);
    
    this.cartQuantityForm = this.formBuilder.group({
      quantity: ['', [Validators.required]]
    });
  }

   enableAddCart(event: any) {
    if (event.option.value > 0) {
      this.turnOnAddToCart = true;
    }
  }

  openAtcDialog(event: any) {
    if (this.product.id != undefined) {
      console.log('product id selected ' + this.product.id);
    
      console.log('selected item quantity ' + this.selectedQuantity);

      let itemCount = this.cartQuantityForm.get('quantity')?.value;
      if (itemCount != null && this.userId !== -1) {
        console.log('user id from cookies ' + this.userId);
        this.selectedProduct = { 'quantity': +itemCount, 'user': { 'id': this.userId }, 'product': { 'id': this.product.id } };
        this.cartService.addOneCartItem(this.selectedProduct).subscribe();

        const dialogRef = this.atcDialog.open(AtcDialog, {
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



  getProduct(){
    this.productService.getProductById(Number(this.storage.getItem('productId'))).subscribe(data => {
      this.product= data;
      console.log("Occupying product with "+this.storage.getItem('productId'));
      console.log(this.product);
      
    }); 
  }
  
}

@Component({
  selector: 'atcDialog-dialog',
  templateUrl: 'atcDialog-dialog.html',
})
export class AtcDialog {
  constructor(public dialogRef: MatDialogRef<AtcDialog>,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) {
    dialogRef.disableClose = true;

  }

  onNoClick(): void {
    this.dialogRef.close();
   // window.location.reload();

  }

}