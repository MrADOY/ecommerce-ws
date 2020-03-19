import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import { Validators } from '@angular/forms';
import {UserRegisterService} from '../service/user-register.service';
import {RegisterUserOdt} from '../RegisterUserOdt';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  profileForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]],
    confirmPassword: ['', [Validators.required]],
    bankCardNumber: ['', [Validators.required]]
  });

  constructor(private fb: FormBuilder, private userRegisterService: UserRegisterService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    const user: RegisterUserOdt = {
      email: this.profileForm.value.email,
      password: this.profileForm.value.password,
      bankCardNumber: this.profileForm.value.bankCardNumber
    };
    this.userRegisterService.singup(user).subscribe((userResponse) => {
      this.userRegisterService.user = userResponse;
      this.userRegisterService.isAuthentified = true;
      this.router.navigateByUrl('');
    });
  }
}
