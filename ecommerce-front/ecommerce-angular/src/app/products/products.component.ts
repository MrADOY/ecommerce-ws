import { Component, OnInit } from '@angular/core';
import {Product} from './product';
import {ProductsService} from './service/products.service';
import {UserRegisterService} from '../user/service/user-register.service';
import {map} from "rxjs/operators";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private productsService: ProductsService,
              private userService: UserRegisterService) { }

  private products: Product[];

  ngOnInit() {
   this.productsService.getAllProducts().pipe(
     map(products => {
       return products.filter(p => p.ownerId !== this.userService.user.id && p.available);
     }))
    .subscribe(products =>  this.products = products);
  }

}
