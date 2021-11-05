import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {EditorBeans} from "../../../../objects/classes/beans/editorBeans/EditorBeans";
import {ProvisionalBean} from "../../../../objects/classes/beans/adminBeans/ProvisionalBean";

@Injectable({
  providedIn: 'root'
})
export class ManagerReportsService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getMagazineTopCommentsReport(reportType: any, number: number, startDate: string, endDate: string): Observable<Array<any>>{
    let params = new HttpParams().append("start",startDate).append("end",endDate).append("report",number)
      .append("reportType",reportType);
    return this.httpClient.get<Array<any>>(this.API_URL+"reports/manager-reports",{params:params});
  }

  getAdminExportReport(editorReport: ProvisionalBean, reportType: string): any {
    let params = new HttpParams().append("reportType", reportType);
    return this.httpClient.post(this.API_URL + "reports/manager-reports", editorReport,{params: params, responseType: 'blob'});
  }
}
