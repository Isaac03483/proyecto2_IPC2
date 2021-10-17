import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Magazine} from "../../../objects/classes/magazine/Magazine";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  readonly API_URL = "http://localhost:8080/backend/";

  constructor(private httpClient: HttpClient) { }

  addMagazine(magazine: Magazine, file: File): Observable<Object>{
    const formData: FormData = new FormData();
    let editorParam = new HttpParams().append("editorName", magazine.editorName)
      .append("magazineName", magazine.magazineName)
      .append("publicationDate", (magazine.publicationDate).toString())
      .append("description", magazine.description)
      .append("categoryName", magazine.category.categoryName)
      .append("subscriptionCost", magazine.subscriptionCost)
      .append("magazineStatus", magazine.status)
      .append("magazineLike", magazine.like)
      .append("magazineComment", magazine.comment)
      .append("magazineSubscription", magazine.subscription);
    formData.append('file',file , "magazine");
    return this.httpClient.post<Object>(this.API_URL+"add-magazine", formData, {params: editorParam});
  }

  getMagazines(): Observable<Array<Magazine>>{
    let param = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")));
    return this.httpClient.get<Array<Magazine>>(this.API_URL+"add-magazine", {params: param});
  }
}
