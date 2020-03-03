import { Component, OnInit } from '@angular/core';
import { NgxSoapService, Client, ISoapMethodResponse } from 'ngx-soap';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {OrderService} from './service/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  result: boolean;

  constructor(private orderService: OrderService) {
  }


  ngOnInit(): void {

  }

  refundCustomer(id: string, amout: string): void {
    this.orderService.refundCustommer(id, amout).subscribe(value => {
      this.result = value;
    });
  }
}
