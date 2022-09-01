import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';

const routes: Routes = [

  { path: 'home', component: HomeComponent, canActivate: [OktaAuthGuard] },
  { path: 'user', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'account', component: UserComponent },
  { path: 'order', component: OrderComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'login/callback', component: OktaCallbackComponent},
  { path: 'login', component: LoginComponent },
  { path: "", redirectTo: "/login", pathMatch: "full"}


];

// const routes: Routes = [
//   { path: '', pathMatch: 'full', redirectTo: 'signin' },
//   { path: 'signin', component: LoginComponent},
//   { path: 'register', component: RegisterComponent }
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
