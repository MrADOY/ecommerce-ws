import { Component, OnInit } from '@angular/core';
import {UserRegisterService} from '../user/service/user-register.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userRegister: UserRegisterService) { }

  ngOnInit() {
  }

}
