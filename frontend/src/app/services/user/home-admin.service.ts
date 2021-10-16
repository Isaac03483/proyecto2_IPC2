import { Injectable } from '@angular/core';
import {Manager} from "../../../objects/classes/usuario/administrador/Manager";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ManagerUpdate} from "../../../objects/classes/usuario/administrador/ManagerUpdate";

@Injectable({
  providedIn: 'root'
})
export class HomeAdminService {

  readonly API_URL = "http://localhost:8080/backend/";

  option: number = 1;
  constructor(private httpClient: HttpClient) { }

  public addManager(manager: Manager): Observable<Manager>{
    return this.httpClient.post<Manager>(this.API_URL+"add-manager", manager);
  }

  public updateManager(manager: Manager | ManagerUpdate): Observable<Manager | ManagerUpdate>{
    return this.httpClient.post<Manager | ManagerUpdate>(this.API_URL+"update-manager", manager);
  }

  public deleteManager(manager: Manager): Observable<Manager>{
    return this.httpClient.post<Manager>(this.API_URL+"delete-manager", manager);
  }

  public getManagers(): Observable<Array<Manager>>{
    return this.httpClient.get<Array<Manager>>(this.API_URL+"add-manager");
  }
}
