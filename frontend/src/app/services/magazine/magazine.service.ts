import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Magazine} from "../../../objects/classes/magazine/Magazine";
import {Observable} from "rxjs";
import {MagazineTag} from "../../../objects/classes/magazine/MagazineTag";
import {Comment} from "../../../objects/classes/usuario/editor/Comment";

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  readonly API_URL = "http://localhost:8080/backend/";

  constructor(private httpClient: HttpClient) { }

  addMagazine(magazine: Magazine): Observable<Magazine>{

    return this.httpClient.post<Magazine>(this.API_URL+"add-magazine", magazine);
  }

  updateMagazine(magazine:Magazine): Observable<Magazine>{
    return this.httpClient.post<Magazine>(this.API_URL+"update-magazine", magazine);
  }

  updateMagazineInf(magazine:Magazine): Observable<Magazine>{
    return this.httpClient.post<Magazine>(this.API_URL+"accept-magazine", magazine);

  }

  addMagazineTags(magazineTag: MagazineTag): Observable<MagazineTag>{
    return this.httpClient.post<MagazineTag>(this.API_URL+"add-magazine-tag", magazineTag);
  }

  deleteMagazine(magazine: Magazine): Observable<Magazine>{
    return this.httpClient.post<Magazine>(this.API_URL+"delete-magazine", magazine);
  }

  getMagazines(): Observable<Array<Magazine>>{
    let param = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")));
    return this.httpClient.get<Array<Magazine>>(this.API_URL+"add-magazine", {params: param});
  }

  getMagazineTags(magazine: Magazine): Observable<Array<MagazineTag>>{
    let magazineParam = new HttpParams().append("magazineRecord", magazine.magazineRecord);
    return this.httpClient.get<Array<MagazineTag>>(this.API_URL+"add-magazine-tag", {params: magazineParam});
  }

  deleteAllMagazineTags(magazine: Magazine): Observable<Magazine>{
    console.log("prueba unitaria"+magazine.magazineRecord);
    let magazineParam = new HttpParams().append("magazineRecord", magazine.magazineRecord);
    return this.httpClient.get<Magazine>(this.API_URL+"delete-magazine-tag",{params: magazineParam});
  }

  getMagazinesWaiting(): Observable<Array<Magazine>>{
    return this.httpClient.get<Array<Magazine>>(this.API_URL+"accept-magazine");
  }

  getAllMagazines(): Observable<Array<Magazine>>{
    return this.httpClient.get<Array<Magazine>>(this.API_URL+"update-magazine");
  }

  getMagazineWithRecord(magazineRecord: number): Observable<Magazine>{
    let params = new HttpParams().append("magazineRecord", magazineRecord);
    return this.httpClient.get<Magazine>(this.API_URL+"get-magazine-with-record", {params: params});
  }

  getMagazineComment(magazineRecord: number): Observable<Array<Comment>>{
    let param = new HttpParams().append("magazineRecord", magazineRecord);
    return this.httpClient.get<Array<Comment>>(this.API_URL+"add-subscriber-comment", {params: param});
  }

  addMagazineComment(comment: Comment): Observable<Comment>{
    return this.httpClient.post<Comment>(this.API_URL+"add-subscriber-comment", comment);
  }
}
