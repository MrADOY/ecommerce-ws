import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductsService} from '../service/products.service';
import {Product} from '../product';
import {Location} from '@angular/common';
import {OrderService} from '../service/order.service';
import {mergeMap, tap} from 'rxjs/operators';
import {UserRegisterService} from '../../user/service/user-register.service';
import {User} from '../../user/User';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  private showSpinner: boolean;
  private showSuccess: boolean;
  private showButton: boolean = true;

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

  private order(idBuyer: string, idOwner: string, amout: number, productId: string): void {
    this.showSpinner = true;
    this.showButton = false;
    forkJoin(
      this.orderService.refundCustommer(idOwner, amout),
      this.orderService.debitCustommer(idBuyer, amout),
      this.productsService.getProductNoAvailable(productId)
    ).subscribe(([refundResult, debitResult]) => {
      this.resultRefund = refundResult;
      this.resultDebit = debitResult;
    },
      (error => {}),
      () => {
      this.showSpinner = false;
      this.showSuccess = true;
    });
  }
}
