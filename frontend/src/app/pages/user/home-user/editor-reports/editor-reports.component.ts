import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EditorReportsService} from "../../../../services/reports/editorReports/editor-reports.service";
import {DomSanitizer} from "@angular/platform-browser";
import {Subscription} from "../../../../../objects/classes/usuario/editor/Subscription";
import {Comment} from "../../../../../objects/classes/usuario/editor/Comment";
import {MagazineBeans} from "../../../../../objects/classes/beans/editorBeans/MagazineBeans";
import {EditorAccount} from "../../../../../objects/classes/usuario/editor/EditorAccount";
import {formatDate} from "@angular/common";
import {EditorBeans} from "../../../../../objects/classes/beans/editorBeans/EditorBeans";

@Component({
  selector: 'app-editor-reports',
  templateUrl: './editor-reports.component.html',
  styleUrls: ['./editor-reports.component.css']
})
export class EditorReportsComponent implements OnInit {

  reportForm!: FormGroup;
  reportViewer: any = null;
  number: number = 0;
  reportType: string = "";
  reportWithFilter!: EditorBeans;
  getExportReport: boolean = false;
  constructor(private formBuilder: FormBuilder, private editorReportsService: EditorReportsService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
      reportType: [null, Validators.required]
    })
  }

  getReport(number: number){
    this.number = number;
    this.reportType = this.reportForm.value.reportType;
    console.log(this.reportType);
    this.getExportReport = false;

    this.editorReportsService.getEditorReport(this.reportForm.value.reportType, this.number, this.reportForm.value.startDate, this.reportForm.value.endDate)
      .subscribe((data: EditorBeans) =>{
        if(data != null){
          this.reportWithFilter =data;
          console.log(data);
          this.reportForm.reset({
            "reportType": null
          })
        }
      })
  }

  exportReport() {
    this.editorReportsService.getEditorExportReport( this.reportWithFilter,this.reportType, this.number)
      .subscribe((data:any)=>{
        if(data !=null){
          this.getBase64(data).then((image: any) =>{
            this.reportViewer = image.base;
            this.getExportReport = true;
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

  dateFormat(recordDate: any) {
    return formatDate(recordDate, 'dd-MM-yyyy', 'en-US');
  }
}
