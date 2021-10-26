import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ManagerReportsService {

  readonly API_URL = "http://localhost:8080/backend/";
  constructor(private httpClient: HttpClient) { }

  getMagazineTopCommentsReport(reportType: any, number: number, startDate: string, endDate: string): any{
    let params = new HttpParams().append("start",startDate).append("end",endDate).append("report",number)
      .append("reportType",reportType);
    return this.httpClient.get(this.API_URL+"reports/manager-reports",{params:params, responseType: 'blob'});
  }
}
