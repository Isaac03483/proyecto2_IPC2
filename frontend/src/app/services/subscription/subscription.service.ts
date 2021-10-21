import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Subscription} from "../../../objects/classes/usuario/editor/Subscription";
import {Observable} from "rxjs";
import {SubscriptionLike} from "../../../objects/enums/user/editor/SubscriptionLike";
import {UpdateSubscriptionInf} from "../../../objects/classes/usuario/editor/UpdateSubscriptionInf";
import {UpdateSubscriptionLike} from "../../../objects/classes/usuario/editor/UpdateSubscriptionLike";
import {DeleteSubscription} from "../../../objects/classes/usuario/editor/DeleteSubscription";
import {EditorAccount} from "../../../objects/classes/usuario/editor/EditorAccount";
import {SubscriptionLikesCount} from "../../../objects/classes/usuario/editor/SubscriptionLikesCount";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  readonly API_URL = "http://localhost:8080/backend/";

  optionSubscription: number = 0;
  constructor(private httpClient: HttpClient) { }

  addSubscription(subscription: Subscription): Observable<Subscription>{
    return this.httpClient.post<Subscription>(this.API_URL+"add-subscription",subscription);
  }

  addEditorAccount(account: EditorAccount): Observable<EditorAccount>{
    return this.httpClient.post<EditorAccount>(this.API_URL+"add-editor-account", account);
  }

  getEditorSubscriptions(): Observable<Array<Subscription>>{
    let params = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")));
    return this.httpClient.get<Array<Subscription>>(this.API_URL+"add-subscription", {params:params});
  }

  updateSubscriptionLike(subscriptionRecord: number, like: SubscriptionLike): Observable<UpdateSubscriptionLike>{
    let params = new HttpParams().append("like", like)
      .append("subscriptionRecord", subscriptionRecord);
    return this.httpClient.get<UpdateSubscriptionLike>(this.API_URL+"update-subscription-information", {params: params});
  }

  updateSubscriptionInf(subcription: UpdateSubscriptionInf): Observable<UpdateSubscriptionInf>{
    return this.httpClient.post<UpdateSubscriptionInf>(this.API_URL+"update-subscription-information", subcription);
  }

  deleteSubscription(subscriptionRecord: number): Observable<DeleteSubscription>{
    let params = new HttpParams().append("subscriptionRecord", subscriptionRecord);
    return this.httpClient.get<DeleteSubscription>(this.API_URL+"delete-subscription", {params: params});
  }

  getSubscriptionLikes(magazineRecord: number): Observable<SubscriptionLikesCount>{
    let param = new HttpParams().append("magazineRecord", magazineRecord);
    return this.httpClient.get<SubscriptionLikesCount>(this.API_URL+"add-subscription-like", {params:param});
  }

}
