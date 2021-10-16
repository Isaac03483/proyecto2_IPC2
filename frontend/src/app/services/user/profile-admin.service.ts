import { Observable } from 'rxjs';
import { Category } from '../../../objects/classes/usuario/administrador/Category';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Impuesto} from "../../../objects/classes/usuario/administrador/Impuesto";
import {Tag} from "../../../objects/classes/usuario/administrador/Tag";
import {TagUpdate} from "../../../objects/classes/usuario/administrador/TagUpdate";
import {CategoryUpdate} from "../../../objects/classes/usuario/administrador/CategoryUpdate";

@Injectable({
  providedIn: 'root'
})
export class ProfileAdminService {

  readonly API_URL = "http://localhost:8080/backend/";

  option: number = 0;
  constructor(private httpClient: HttpClient) { }

  public addCategory(category: Category): Observable<Category>{
    return this.httpClient.post<Category>(this.API_URL+"add-category", category);
  }

  public updateCategory(category: CategoryUpdate): Observable<Category | CategoryUpdate>{
    return this.httpClient.post<Category| CategoryUpdate>(this.API_URL+"update-category", category);
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

  public addTag(tag: Tag): Observable<Tag>{
    return this.httpClient.post<Tag>(this.API_URL+"add-tag", tag);
  }

  public updateTag(tag:Tag): Observable<TagUpdate | Tag>{
    return this.httpClient.post<TagUpdate | Tag>(this.API_URL+"update-tag", tag);
  }

  public deleteTag(tag: Tag):Observable<Tag>{
    return this.httpClient.post<Tag>(this.API_URL+"delete-tag", tag);
  }

  public getTags(): Observable<Array<Tag>>{
    return this.httpClient.get<Array<Tag>>(this.API_URL+"add-tag");
  }

}
