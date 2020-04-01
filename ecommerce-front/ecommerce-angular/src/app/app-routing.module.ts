import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductsComponent} from './products/products.component';
import {ProductDetailComponent} from './products/product-detail/product-detail.component';
import {RegisterComponent} from './user/register/register.component';
import {ConnexionComponent} from './user/connexion/connexion.component';
import {AuthGuard} from './guards/auth.guard';
import {ProductFormComponent} from './products/product-form/product-form.component';

const routes: Routes = [
  { path : '', component: ProductsComponent, canActivate: [AuthGuard]},
  { path: 'product/:id', component: ProductDetailComponent, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent},
  { path: 'connexion', component: ConnexionComponent },
  { path: 'create-product', component: ProductFormComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
