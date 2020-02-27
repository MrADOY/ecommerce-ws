import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductsService} from '../service/products.service';
import {Product} from '../product';
import {Location} from '@angular/common';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(
  private route: ActivatedRoute,
  private productsService: ProductsService,
  private location: Location
  )  { }

  product: Product;

  ngOnInit() {
    this.getProduct();
  }

  private getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.productsService.getProduct(id)
      .subscribe(product => this.product = product);
  }

  private goBack(): void {
    this.location.back();
  }
}
