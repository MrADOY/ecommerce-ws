import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ConnexionUserResponseOdt} from '../ConnexionUserResponseOdt';
import {UserSignin} from '../UserSignin';
import {RegisterUserOdt} from '../RegisterUserOdt';
import {User} from "../User";

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {

  constructor(private http: HttpClient) { }

  public isAuthentified = false;
  public user: ConnexionUserResponseOdt;

  public signin(user: UserSignin): Observable<ConnexionUserResponseOdt> {
    return this.http.post<ConnexionUserResponseOdt>('http://localhost:8080/api/user/connexion/', user);
  }

  public singup(user: RegisterUserOdt): Observable<ConnexionUserResponseOdt> {
    return this.http.post<ConnexionUserResponseOdt>('http://localhost:8080/api/user/register/', user);
  }

  public getUserDetail(id: string): Observable<User> {
    return this.http.get<User>('http://localhost:8080/api/user/' + id);
  }
}
