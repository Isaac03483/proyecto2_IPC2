import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ManagerReportsService} from "../../../../services/reports/managerReports/manager-reports.service";

@Component({
  selector: 'app-report-admin',
  templateUrl: './report-admin.component.html',
  styleUrls: ['./report-admin.component.css']
})
export class ReportAdminComponent implements OnInit {

  reportViewer: any = null;
  reportForm!: FormGroup;

  constructor(private sanitizer: DomSanitizer,private formBuilder: FormBuilder, private managerReportsServices: ManagerReportsService) { }

  ngOnInit(): void {
    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
      reportType: [null, Validators.required]
    })
  }

  getReport(number: number) {

    this.managerReportsServices.getMagazineTopCommentsReport(this.reportForm.value.reportType, number, this.reportForm.value.startDate, this.reportForm.value.endDate)
      .subscribe((data:any)=>{
        if(data !=null){
          this.getBase64(data).then((image: any) =>{
            this.reportViewer = image.base;
            console.log(this.reportViewer)
            this.reportForm.reset({
              "reportType": null
            })
          })
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
}
