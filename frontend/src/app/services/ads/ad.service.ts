import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {AdType} from "../../../objects/classes/ads/AdType";
import {HttpClient} from "@angular/common/http";
import {Ad} from "../../../objects/classes/ads/Ad";

@Injectable({
  providedIn: 'root'
})
export class AdService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getAdType(): Observable<Array<AdType>>{
    return this.httpClient.get<Array<AdType>>(this.API_URL+"update-ad-type-cost");
  }

  updateAdTypeCost(adType: AdType): Observable<AdType>{

    return this.httpClient.post<AdType>(this.API_URL+"update-ad-type-cost", adType);
  }

  addNewAd(ad: Ad): Observable<Ad>{
    return this.httpClient.post<Ad>(this.API_URL+"add-new-ad",ad);
  }
}
