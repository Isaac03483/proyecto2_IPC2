import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileAdminService} from "../../../../../services/user/profile-admin.service";
import {Impuesto} from "../../../../../../objects/classes/usuario/administrador/Impuesto";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-impuesto',
  templateUrl: './impuesto.component.html',
  styleUrls: ['./impuesto.component.css']
})
export class ImpuestoComponent implements OnInit {

  impForm!: FormGroup;
  impValue: Impuesto = new Impuesto(1,0,"");
  constructor(private formBuilder: FormBuilder, private service: ProfileAdminService) {
    this.upload();
  }

  ngOnInit(): void {
    this.impForm = this.formBuilder.group({
      impData: [null, Validators.required],
      updateDate: [null, Validators.required]
    });



  }

  updateImp() {
    console.log(this.impForm.value.impData+" "+this.impForm.value.updateDate);
    let date = formatDate(new Date(this.impForm.value.updateDate), 'yyyy-MM-dd', 'en-US');
    console.log(date);
    this.service.updateImp(new Impuesto(1, this.impForm.value.impData, date))
      .subscribe((created: Impuesto) => {
        if(created != null){
          window.alert("SE HA MODIFICADO EL IMPUESTO.");
          this.upload();
        }
      }, (error: any) =>{
        alert(error);
    });
  }

  upload(){
    this.service.getImp()
      .subscribe((created:Impuesto) =>{
        console.log(created);
        this.impValue = created;
        console.log(this.impValue.updateDate);
      })
  }

}
