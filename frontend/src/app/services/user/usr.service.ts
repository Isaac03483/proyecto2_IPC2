import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/objects/classes/usuario/User';

@Injectable({
  providedIn: 'root'
})
export class UsrService {

  readonly API_URL = "http://localhost:8080/backend/";

  constructor(private httpClient: HttpClient) { }

  public createUser(user: User): Observable<User> {
    console.log(user);
    return this.httpClient.post<User>(this.API_URL + "user", user);
  }

  public getUser(user: User): Observable<User>{
    return this.httpClient.post<User>(this.API_URL +"get", user);
  }
}