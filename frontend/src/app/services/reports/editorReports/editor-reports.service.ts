import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Response} from "../../../../objects/other/Response";

@Injectable({
  providedIn: 'root'
})
export class EditorReportsService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getMagazineCommentsReport(reportType: any, number: number, startDate: string, endDate: string): any{
    let params = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")))
      .append("start",startDate).append("end",endDate).append("report",number)
      .append("reportType",reportType);
    return this.httpClient.get(this.API_URL+"reports/editor-reports",{params:params, responseType: 'blob'});
  }
}
