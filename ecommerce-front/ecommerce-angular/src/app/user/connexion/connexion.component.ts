import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserRegisterService} from '../service/user-register.service';
import {UserSignin} from '../UserSignin';
import {User} from '../User';

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

  user: User;
  constructor(private fb: FormBuilder, private userRegisterService: UserRegisterService) { }

  ngOnInit() {
  }

  onSubmit() {
    const user: UserSignin = {
      email: this.profileForm.value.email,
      password: this.profileForm.value.password
    };
    this.userRegisterService.signin(user).subscribe((userResponse) => {
      this.user = userResponse;
    });
  }
}
