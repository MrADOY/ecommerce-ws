import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserRegisterService} from '../service/user-register.service';
import {UserSignin} from '../UserSignin';
import {User} from '../User';
import {Router} from "@angular/router";

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  profileForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  constructor(private fb: FormBuilder,
              private userRegisterService: UserRegisterService,
              private router: Router,
              private userRegister: UserRegisterService
              ) { }
  ngOnInit() {
  }

  onSubmit() {
    const user: UserSignin = {
      email: this.profileForm.value.email,
      password: this.profileForm.value.password
    };
    this.userRegisterService.signin(user).subscribe((userResponse) => {
      this.userRegister.user = userResponse;
      this.userRegister.isAuthentified = true;
      this.router.navigateByUrl('');
    });
  }
}
