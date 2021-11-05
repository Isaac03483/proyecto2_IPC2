import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {EditorBeans} from "../../../../objects/classes/beans/editorBeans/EditorBeans";

@Injectable({
  providedIn: 'root'
})
export class EditorReportsService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getEditorReport(reportType: any, number: number, startDate: string, endDate: string): Observable<EditorBeans>{
    let params = new HttpParams().append("editorName", JSON.parse(<string>localStorage.getItem("editor")))
      .append("start",startDate).append("end",endDate).append("report",number)
      .append("reportType",reportType);
    return this.httpClient.get<EditorBeans>(this.API_URL+"reports/editor-reports",{params:params});
  }

  getEditorExportReport(editorReport: EditorBeans, reportType: string, number: number): any {
    let params = new HttpParams().append("report", number)
      .append("reportType", reportType);
    return this.httpClient.post(this.API_URL + "reports/editor-reports", editorReport,{params: params, responseType: 'blob'});
  }
}
