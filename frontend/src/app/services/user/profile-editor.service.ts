import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../../../objects/classes/usuario/User";
import {Observable} from "rxjs";
import {Profile} from "../../../objects/classes/usuario/editor/Profile";
import {EditorTag} from "../../../objects/classes/usuario/editor/EditorTag";

@Injectable({
  providedIn: 'root'
})
export class ProfileEditorService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getProfile(user: User): Observable<Profile>{
    return this.httpClient.post<Profile>(this.API_URL+"get-profile-editor", user);
  }

  updateProfile(profile: Profile): Observable<Profile>{

    return this.httpClient.post<Profile>(this.API_URL+"update-profile", profile );
  }

  updateImage(fileToUpload: File): Observable<Object>{
    const formData: FormData = new FormData();
    let paramsInfo = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")));
    formData.append('file', fileToUpload, "image-name");
    return this.httpClient.post<Object>(this.API_URL+"update-image", formData,{params: paramsInfo});
  }

  addTag(tag: EditorTag): Observable<EditorTag>{
    return this.httpClient.post<EditorTag>(this.API_URL+"add-editor-tag", tag);
  }

  deleteTag(tag: EditorTag): Observable<EditorTag>{
    return this.httpClient.post<EditorTag>(this.API_URL+"delete-editor-tag", tag);
  }

  getEditorTags(editorName: string): Observable<Array<EditorTag>>{
    let params = new HttpParams().append("editorName", editorName);
    return this.httpClient.get<Array<EditorTag>>(this.API_URL+"add-editor-tag", {params:params});
  }
}
