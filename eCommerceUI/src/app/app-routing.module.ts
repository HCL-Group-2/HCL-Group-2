import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'signin', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent }

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