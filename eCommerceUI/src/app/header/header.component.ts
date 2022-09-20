import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, OktaAuth } from '@okta/okta-auth-js';
import { Observable, filter, map } from 'rxjs';
import { CartService } from '../cart.service';
import { User } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  itemsInCartCount: number = 0;
  storage: Storage = sessionStorage;
  localStorage: Storage = localStorage;
  searchText: string = '';
  searchForm: FormGroup = new FormGroup([]);
  user: User = new User();

  public isAuthenticated$!: Observable<boolean>;

  isLoggedinFromOkta = false;

  email: string = "";
  loggedIn = false;
  isAdmin = false;

  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService,
    private fb: FormBuilder,  private _oktaStateService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth,
    private userService: UserService,
    private _oktaAuthStateService: OktaAuthStateService) { }

  ngOnInit(): void {

    this.isAuthenticated$ = this._oktaStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );

    this._oktaStateService.authState$.subscribe(data =>{
      console.log('data.isAuthenticated ' + data.isAuthenticated);
      this.isLoggedinFromOkta  = data.isAuthenticated !
      console.log('this.isLoggedinFromOkta inside ' + this.isLoggedinFromOkta);
      if (this.isLoggedinFromOkta) {
        console.log('user is login with okta');
        let idToken = JSON.parse( this.localStorage.getItem('okta-token-storage') !).idToken;
        let email = idToken.claims.email;
        this.userEntity(email);
        this.storage.setItem('email', email);
        let oktaUserRole = idToken.claims.groups[1];
        console.log('okta user role ' + oktaUserRole);
        let userId = +this.storage.getItem('userId')!;
        if(oktaUserRole === 'customer'){
          this.cartService.getCartItems(userId).subscribe(data => {
            data.forEach((element: any) => {
              this.itemsInCartCount += element.quantity;
            });
          });
    
          this.searchForm = this.fb.group({
            searchText: [null, [Validators.required]]
          });
        }else if(oktaUserRole === 'admin'){
          console.log('admin loggin in okta');
          this.isAdmin = true;


        }
       
      }

    });
    console.log('this.isLoggedinFromOkta outside ' + this.isLoggedinFromOkta);
  }


  public userEntity(email: string){
    this.storage.setItem('email', email); // remove?
    this.userService.getUserByEmail(email).subscribe(data =>{
      this.storage.setItem('userId', data.id as unknown as string);
      this.storage.setItem('firstName', data.firstName);
      this.storage.setItem('lastName', data.lastName);
      console.log('loggin in userEntity() in login component' + this.loggedIn);
      // hardcoded role : backend may change
      //this.storage.setItem('userRole', 'Admin');
    }
    );
  }

  goToHomePage() {
    this.router.navigate(['/home']);
    this.storage.setItem('search', 'true');
    this.storage.setItem('category', 'home');

  }
  goToCart() {
    // https://dev.to/isamrish/how-to-display-observable-of-an-object-in-angular-22em
    this.router.navigate(['/cart']);
  }

  goToOrderStatus() {
    this.router.navigate(['/order']);
  }

  goToRegister() {
    this.router.navigate(['/user']);
  }

  goToAccount() {
    this.router.navigate(['/account']);
  }

  searchSubmit() {
    var search = 'false';
    this.storage.setItem('search', search);
    this.storage.setItem('searchText', this.searchForm.value.searchText);
    window.location.reload();
  }

  searchToys() {
    var search = 'false';
    this.storage.setItem('category', 'toys');
    window.location.reload();
  }

  searchClothing() {
    var search = 'false';
    this.storage.setItem('category', 'clothing');
    window.location.reload();
  }

  searchElectronics() {
    var search = 'false';
    this.storage.setItem('category', 'electronics');
    window.location.reload();
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToAdmin() {
    // default into users management page
    this.router.navigate(['/admin']);
  }

  goToProductCatalog() {
    this.router.navigate(['/admin/productManagement']);
 
  }
  goToOrders() {
    this.router.navigate(['/admin/orderManagement']);
  }

  public logout() {
    this.loggedIn = false;
    console.log('loggedIn from header logout() ' + this.loggedIn);

    console.log('header logging out, before clear this.storage ' + JSON.stringify(this.storage));
    this.userService.clear();
    this.storage.clear();
    console.log('header logging out, after clear this.storage ' + JSON.stringify(this.storage));
    this.goToLogin();
  }


}
