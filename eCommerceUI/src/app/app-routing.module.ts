import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';

import { CheckoutComponent } from './cart/checkout/checkout.component';
import { StripeComponent } from './cart/checkout/stripe/stripe.component';

import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ProductMangementComponent } from './admin/product-mangement/product-mangement.component';
import { OrdersManagementComponent } from './admin/orders-management/orders-management.component';
import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { AddProductComponent } from './admin/product-mangement/add-product/add-product.component';
import { EditProductComponent } from './admin/product-mangement/edit-product/edit-product.component';
import { EditUserComponent } from './admin/edit-user/edit-user.component';



const routes: Routes = [

  { path: 'home', component: HomeComponent },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'checkout-payment', component: StripeComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/editUser/:userId', component: EditUserComponent },
  { path: 'admin/productManagement', component: ProductMangementComponent  },
  { path: 'admin/productManagement/addProduct', component: AddProductComponent },
  { path: 'admin/productManagement/editProduct/:productId', component: EditProductComponent },
  { path: 'admin/orderManagement', component:  OrdersManagementComponent },
  { path: 'account', component: UserComponent },
  { path: 'order', component: OrderComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'login/callback', component: OktaCallbackComponent },
  { path: 'login', component: LoginComponent },
  { path: "", redirectTo: "/login", pathMatch: "full" }
  

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
