import { Observable } from 'rxjs';
import { Category } from '../../../objects/classes/usuario/administrador/Category';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Impuesto} from "../../../objects/classes/usuario/administrador/Impuesto";

@Injectable({
  providedIn: 'root'
})
export class ProfileAdminService {

  readonly API_URL = "http://localhost:8080/backend/";

  option: number = 0;
  reload: number = 2;
  constructor(private httpClient: HttpClient) { }

  public addCategory(category: Category): Observable<Category>{
    return this.httpClient.post<Category>(this.API_URL+"add-category", category);
  }

  public updateCategory(category: Category): Observable<Category>{
    return this.httpClient.post<Category>(this.API_URL+"update-category", category);
  }

  public deleteCategory(category: Category): Observable<Category>{
    return this.httpClient.post<Category>(this.API_URL+"delete-category", category);
  }

  public listCategories(): Observable<Array<Category>>{
    return this.httpClient.get<Array<Category>>(this.API_URL+"add-category");
  }

  public updateImp(imp: Impuesto): Observable<Impuesto>{
    return this.httpClient.post<Impuesto>(this.API_URL+"update-imp", imp);
  }

  public getImp(): Observable<Impuesto>{
    return this.httpClient.get<Impuesto>(this.API_URL+"update-imp");
  }

}
