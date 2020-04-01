import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../product';
import {CreateProductOdt} from "../product-form/CreateProductOdt";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/api/products/');
  }
  public getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}`);
  }
  public getProductNoAvailable(id: string): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}/buy`);
  }
  public createProduct(createProductOdt: CreateProductOdt): Observable<any> {
    return this.http.post('http://localhost:8080/api/products/create', createProductOdt);
  }
}
