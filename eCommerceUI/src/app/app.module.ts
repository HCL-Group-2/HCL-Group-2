import { CommonModule } from '@angular/common';
import { NgModule, APP_INITIALIZER, Injector } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FlexLayoutModule } from '@angular/flex-layout';

import { OverlayModule } from '@angular/cdk/overlay';
import { CdkTreeModule } from '@angular/cdk/tree';
import { PortalModule } from '@angular/cdk/portal';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatRippleModule } from '@angular/material/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTreeModule } from '@angular/material/tree';
import { MatBadgeModule } from '@angular/material/badge';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgMaterialModule } from './ng-material/ng-material.module';
import { CloudinaryModule } from '@cloudinary/ng';
import { OktaAuthModule, OKTA_CONFIG } from '@okta/okta-angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { OrderComponent } from './order/order.component';
import { NavComponent } from './nav/nav.component';
import { WelcomeComponent } from './welcome/welcome.component';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { CheckoutComponent } from './cart/checkout/checkout.component';

import { SearchComponent } from './search/search.component';
import { Router } from '@angular/router';
import myAppConfig from './config/my-app-config';
import { OktaAuth } from '@okta/okta-auth-js';
import { ProductMangementComponent } from './admin/product-mangement/product-mangement.component';
import { OrdersManagementComponent } from './admin/orders-management/orders-management.component';
import { AddProductComponent } from './admin/product-mangement/add-product/add-product.component';
import { EditProductComponent } from './admin/product-mangement/edit-product/edit-product.component';
import { EditUserComponent } from './admin/edit-user/edit-user.component';
import { AuthInterceptor } from './auth.interceptor';
import { ProductDetailsComponent } from './product-details/product-details.component';


 const oktaAuth = new OktaAuth({
   issuer: "https://dev-34530608.okta.com/oauth2/default",
   clientId: '0oa6batm961d386e25d7',
   redirectUri: window.location.origin + '/login/callback'
 });

//0oa6k6s96mW1KnQGt5d7


//const oktaAuth = new OktaAuth({
 // issuer: "https://dev-34530608.okta.com/oauth2/default",
 // clientId: '0oa6k6s96mW1KnQGt5d7',
 // redirectUri: window.location.origin + '/login/callback'
//});



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CartComponent,
    HomeComponent,
    RegisterComponent,
    CheckoutComponent,
    AdminComponent,
    UserComponent,
    OrderComponent,
    SearchComponent,
    LoginComponent,
    NavComponent,
    WelcomeComponent,
    ProductMangementComponent,
    OrdersManagementComponent,
    AddProductComponent,
    EditProductComponent,
    EditUserComponent,
    ProductDetailsComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatSelectModule,
    MatFormFieldModule,
    HttpClientModule,
    FlexLayoutModule,
    CommonModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    MatMenuModule,
    MatIconModule,
    MatAutocompleteModule,
    MatProgressSpinnerModule,
    NoopAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgMaterialModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    CloudinaryModule,
    OktaAuthModule,
  ],

  providers: [
    { provide: OKTA_CONFIG, useValue: { oktaAuth } },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
