import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Category} from "../../../objects/classes/usuario/administrador/Category";
import {Observable} from "rxjs";
import {Magazine} from "../../../objects/classes/magazine/Magazine";

@Injectable({
  providedIn: 'root'
})
export class SearchMagazineService {

  readonly API_URL = "http://localhost:8080/backend/";
  public option =1;
  public showSubscriptionForm = 0;
  constructor(private httpClient:HttpClient) { }

  searchMagazine(magazineName: string, categoryName: string): Observable<Array<Magazine>>{
    let params = new HttpParams().append("magazineName", magazineName)
      .append("categoryName", categoryName)
      .append("editorName", JSON.parse(<string>localStorage.getItem("editor")));
    return this.httpClient.get<Array<Magazine>>(this.API_URL+"search-magazine", {params: params});
  }
}
