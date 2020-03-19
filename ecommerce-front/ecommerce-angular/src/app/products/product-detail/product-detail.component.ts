import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductsService} from '../service/products.service';
import {Product} from '../product';
import {Location} from '@angular/common';
import {OrderService} from "../service/order.service";
import {mergeMap, tap} from "rxjs/operators";
import {UserRegisterService} from "../../user/service/user-register.service";
import {User} from "../../user/User";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(
  private route: ActivatedRoute,
  private productsService: ProductsService,
  private location: Location,
  private orderService: OrderService,
  private userService: UserRegisterService)  { }

  resultRefund: boolean;
  resultDebit: boolean;

  product: Product;
  user: User;
  ngOnInit() {
    this.getProduct();
  }

  private getProduct(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.productsService.getProduct(id)
      .pipe(
        tap(product => this.product = product),
        mergeMap(product => this.userService.getUserDetail(product.ownerId))
      ).subscribe(
        user => this.user = user
    );
  }

  private order(idBuyer: string, idOwner: string, amout: number): void {
    this.orderService.refundCustommer(idOwner, amout).subscribe(value => {
      this.resultRefund = value;
    });
    this.orderService.debitCustommer(idBuyer, amout).subscribe(value => {
      this.resultDebit = value;
    });
  }

  private goBack(): void {
    this.location.back();
  }
}
