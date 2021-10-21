import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../../../objects/classes/usuario/User";
import {Observable} from "rxjs";
import {Profile} from "../../../objects/classes/usuario/editor/Profile";
import {EditorTag} from "../../../objects/classes/usuario/editor/EditorTag";
import {UpdateImage} from "../../../objects/classes/usuario/editor/UpdateImage";
import {UserType} from "../../../objects/enums/user/UserType";

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

  updateImage(updateImage: UpdateImage): Observable<UpdateImage>{
    return this.httpClient.post<UpdateImage>(this.API_URL+"update-image",updateImage);
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

  showInformation(){
    this.getProfile(new User(JSON.parse(<string>localStorage.getItem("editor")), "", UserType.EDITOR))
      .subscribe((created: Profile) =>{
        if(created != null){
          window.alert("\t\t\t\t\t.:INFORMACIÓN DEL EDITOR:.\n\n" +
            "NOMBRE: "+created.editorName+".\n\n" +
            "DESCRIPCIÓN: "+created.description+".\n\n" +
            "PASATIEMPOS: "+created.hobby+".\n\n" +
            "GUSTOS: "+created.likes);
        }
      })
  }
}
