import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/Product';
import { User } from '../model/User';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  name: string = "";
  products !: Array<Product>;
  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService, 
    private productService: ProductService, 
    public cartDialog: MatDialog
    ) { }

  ngOnInit(): void {

    this.getProducts();
   
    // this.updateUser({
    //   id: 1,
    //   firstName: 'Ostrich',
    //   lastName: 'Doe',
    //   email:'ostrich.doe@hcl.com',
    //   password: 'ostrich'
    // });
   }
  updateUser(user: User) {

    this.userService.updateUser(user).subscribe();

  }
  getProducts(){
    this.productService.getProducts().subscribe(data =>{
      this.products = data;
    }

    );
  }

   openCartDialog() {
    this.cartDialog.open(CartDialog, {
      data: {
        name: 'item in the cart placeholder',
      },
    });
   }


}
 @Component({
   selector: 'cartDialog-dialog',
  templateUrl: 'cartDialog-dialog.html',
 })
 export class CartDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Product) {}
}
