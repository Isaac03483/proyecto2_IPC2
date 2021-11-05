import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ManagerReportsService} from "../../../../services/reports/managerReports/manager-reports.service";
import {formatDate} from "@angular/common";
import {ProvisionalBean} from "../../../../../objects/classes/beans/adminBeans/ProvisionalBean";

@Component({
  selector: 'app-report-admin',
  templateUrl: './report-admin.component.html',
  styleUrls: ['./report-admin.component.css']
})
export class ReportAdminComponent implements OnInit {

  reportViewer: any = null;
  reportForm!: FormGroup;
  reportType: string = "";
  reportList: Array<any> = [];
  getExportReport: boolean = false;

  constructor(private sanitizer: DomSanitizer,private formBuilder: FormBuilder, private managerReportsServices: ManagerReportsService) { }

  ngOnInit(): void {
    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
      reportType: [null, Validators.required]
    })
  }

  getReport(number: number) {

    this.reportType = this.reportForm.value.reportType;
    this.getExportReport = false;
    console.log(this.reportType)
    this.managerReportsServices.getMagazineTopCommentsReport(this.reportForm.value.reportType, number, this.reportForm.value.startDate, this.reportForm.value.endDate)
      .subscribe((data:any)=>{
        if(data !=null){
          this.reportList = data;
          console.log(this.reportList);
        }
      })
  }

  getBase64 = async ($event: any) => new Promise((resolve, reject) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      reader.onload = () => {
        resolve({
          base: reader.result
        });
      };
      reader.onerror = _error => {
        resolve({
          base: null
        });
      };
      return;
    } catch (e) {
      return null;
    }
  })

  getFormat(date: any){
    return formatDate(date, 'dd-MM-yyyy','en-US');
  }

  resetReport() {
    this.reportType = "";
    this.getExportReport = false;
  }

  exportReport() {

    let newExport;
    if(this.reportType ==="comments" || this.reportType ==="subscriptions"){
      newExport = new ProvisionalBean(this.reportList, []);
    } else {
      newExport = new ProvisionalBean([], this.reportList);
    }
    this.managerReportsServices.getAdminExportReport( newExport,this.reportType)
      .subscribe((data:any)=>{
        if(data !=null){
          this.getBase64(data).then((image: any) =>{
            this.reportViewer = image.base;
            console.log(this.reportViewer)
            this.reportForm.reset({
              "reportType": null
            })
          })
          this.getExportReport = true;
        }
      })
  }
}
