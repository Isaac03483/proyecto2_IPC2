import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EditorReportsService} from "../../../../services/reports/editorReports/editor-reports.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-editor-reports',
  templateUrl: './editor-reports.component.html',
  styleUrls: ['./editor-reports.component.css']
})
export class EditorReportsComponent implements OnInit {

  reportForm!: FormGroup;
  reportViewer: any = null;
  constructor(private formBuilder: FormBuilder, private editorReportsService: EditorReportsService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.reportForm = this.formBuilder.group({
      startDate: [null],
      endDate: [null],
      reportType: [null, Validators.required]
    })
  }

  getReport(number: number) {
    console.log(this.reportForm.value.startDate)

    this.editorReportsService.getMagazineCommentsReport(this.reportForm.value.reportType, number, this.reportForm.value.startDate, this.reportForm.value.endDate)
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
