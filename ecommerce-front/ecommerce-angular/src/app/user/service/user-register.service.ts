import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../User';
import {UserSignin} from '../UserSignin';
import {UserSignup} from '../UserSignup';

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {

  constructor(private http: HttpClient) { }

  public isAuthentified = false;
  public user: User;

  public signin(user: UserSignin): Observable<User> {
    return this.http.post<User>('http://localhost:8080/user/connexion/', user);
  }

  public singup(user: UserSignup): Observable<User> {
    return this.http.post<User>('http://localhost:8080/user/inscription/', user);
  }
}
