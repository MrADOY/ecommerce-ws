import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/products/');
  }

  public getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/products/${id}`);
  }
}
