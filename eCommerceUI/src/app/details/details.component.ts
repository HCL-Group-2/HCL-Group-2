import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { OktaAuthStateService } from '@okta/okta-angular';
import { Observable, filter, map } from 'rxjs';
import { CartService } from '../cart.service';
import { CartItems } from '../model/CartItems';
import { Product } from '../model/Product';
import { User } from '../model/User';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css']
  })

  export class DetailsComponent implements OnInit {

    public name$!: Observable<string>;

    name: string = "";
    products !: Array<Product>;
    searchProducts ! :Array<Product>;
    user !: User;
    selectedQuantity: number = 0;
    selectedProduct !: CartItems;
    cartQuantityForm: FormGroup = new FormGroup([]);
  
    turnOnAddToCart : boolean = false;
    search: boolean = true;
    searchText: string = '';
  
    //Use this.storage.getKey('userId;) to retrive the userId of the logged in user
    storage: Storage = sessionStorage;
  
    constructor(private route: ActivatedRoute,
      private router: Router, private userService: UserService,
      private formBuilder: FormBuilder,
      private productService: ProductService,
      private cartService: CartService,
      public cartDialog: MatDialog,
      private _oktaAuthStateService: OktaAuthStateService
    ) { }
  
    ngOnInit(): void {
        this.router.navigate(['/admin']);
      }
  }
  
  