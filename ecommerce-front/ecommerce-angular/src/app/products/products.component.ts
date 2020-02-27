import { Component, OnInit } from '@angular/core';
import {Product} from './product';
import {ProductsService} from './service/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor(private productsService: ProductsService) { }

  private products: Product[];

  ngOnInit() {
   this.productsService.getAllProducts().subscribe(products => this.products = products);
  }

}
