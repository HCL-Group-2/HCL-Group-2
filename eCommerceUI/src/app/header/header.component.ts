import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, OktaAuth } from '@okta/okta-auth-js';
import { Observable, filter, map } from 'rxjs';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  itemsInCartCount: number = 0;
  storage: Storage = sessionStorage;
  searchText: string = '';
  searchForm: FormGroup = new FormGroup([]);
  public name$!: Observable<string>;
  public email$!: Observable<string>;
  public isAuthenticated$!: Observable<boolean>;

  email: string = "";
  loggedIn = false;
  isAdmin = false;

  constructor(private route: ActivatedRoute,
    private router: Router, private cartService: CartService,
    private fb: FormBuilder,  private _oktaStateService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth,
    private userService: UserService,
    private _oktaAuthStateService: OktaAuthStateService,
    private loginService: LoginComponent) { }

  ngOnInit(): void {

    this.isAuthenticated$ = this._oktaStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );

    this.name$ = this._oktaAuthStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? ''));
      
     this._oktaAuthStateService.authState$.subscribe(data =>{
      console.log('raw email ' + data.idToken?.claims.email);
      console.log('raw authorizeUrl ' + data.idToken?.authorizeUrl);
      this.email = data.idToken?.claims.email!;
      console.log('this.email ' +   this.email );
    });
    console.log('this.email outside ' +   this.email );
    this.userService.getUserByEmail(this.email);
    this.storage.setItem('userEmail', (this.email));
    this.loginService.userEntity();





    let userRole = this.storage.getItem('userRole')!;
    console.log('user role is ' + userRole);
    if (userRole !== undefined && userRole === 'Admin') {
      console.log('admin user');
      this.isAdmin = true;
    }

    if (userRole !== 'Admin') {
      let userId = +this.storage.getItem('userId')!;
      if(userId !== undefined || userId !== null){
        this.cartService.getCartItems(userId).subscribe(data => {
          data.forEach((element: any) => {
            this.itemsInCartCount += element.quantity;
          });
        });
        this.searchForm = this.fb.group({
          searchText: [null, [Validators.required]]
        });
      }
    }
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
