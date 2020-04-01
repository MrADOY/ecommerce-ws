import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {CreateProductOdt} from './CreateProductOdt';
import {UserRegisterService} from '../../user/service/user-register.service';
import {ProductsService} from '../service/products.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  productForm = this.fb.group({
    name: ['', [Validators.required]],
    description: ['', [Validators.required]],
    urlPictures: ['', [Validators.required]],
    price: ['', [Validators.required]]
  });

  constructor(private fb: FormBuilder,
              private userService: UserRegisterService,
              private productService: ProductsService,
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    const productToCreate: CreateProductOdt = {
      name: this.productForm.value.name,
      description: this.productForm.value.description,
      urlPictures: this.productForm.value.urlPictures,
      price: this.productForm.value.price,
      ownerId: this.userService.user.id
    };
    this.productService.createProduct(productToCreate).subscribe(() => {
      this.router.navigateByUrl('');
    });
  }
}
