import {Component, OnInit} from '@angular/core';
import {MagazineService} from "../../../../services/magazine/magazine.service";
import {Magazine} from "../../../../../objects/classes/magazine/Magazine";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {formatDate} from "@angular/common";
import {MagazineStatus} from "../../../../../objects/enums/magazine/MagazineStatus";

@Component({
  selector: 'app-magazines-admin',
  templateUrl: './magazines-admin.component.html',
  styleUrls: ['./magazines-admin.component.css']
})
export class MagazinesAdminComponent implements OnInit {

  optionNavigate: number = 1;
  optionForm: number = 0;
  magazineSelected!: Magazine;
  magazinesWaiting: Array<Magazine> =[];
  allMagazines: Array<Magazine> =[];
  acceptForm!: FormGroup;
  updateForm!: FormGroup;

  constructor(private magazineService: MagazineService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.acceptForm = this.formBuilder.group({
      dayCost: [null, Validators.required],
      acceptDate: [null, Validators.required]
    });
    this.updateForm = this.formBuilder.group({
      updateDayCost: [null, Validators.required],
      updateAcceptDate: [null, Validators.required]
    });

    this.getMagazinesWaiting();
  }

  getMagazinesWaiting(){
    this.magazineService.getMagazinesWaiting()
      .subscribe((created: Array<Magazine>)=>{
        if(created != null){
          this.magazinesWaiting=created;
          this.allMagazines=[];
        }
      })
  }

  getAllMagazines(){

    this.magazineService.getAllMagazines()
      .subscribe((created: Array<Magazine>)=>{
        if(created != null){
          this.allMagazines = created;
          this.magazinesWaiting=[];
        }
      })
  }

  setOption(number: number) {
    this.optionNavigate = number;
    this.optionForm =0;
    if(number == 1){
      this.getMagazinesWaiting();
    } else {
      this.getAllMagazines();
    }

  }

  selectInf(number: number, magazine: Magazine) {
    this.optionForm = number;
    this.magazineSelected=magazine;
  }

  acceptMagazine(){
    if(this.acceptForm.valid){
      let date = formatDate(this.acceptForm.value.acceptDate, 'yyyy-MM-dd', 'en-US');
      this.magazineSelected.acceptDate = date;
      this.magazineSelected.updateDate = date;
      this.magazineSelected.status = MagazineStatus.ACEPTADA;
      this.magazineSelected.dayCost = this.acceptForm.value.dayCost;
      this.magazineService.updateMagazineInf(this.magazineSelected)
        .subscribe((created: Magazine)=>{
          if(created != null){
            window.alert("REVISTA ACEPTADA CON ÉXITO.");
            this.optionForm = 0;
            this.acceptForm.reset({
              "dayCost": null,
              "acceptDate": null
            })
            this.getMagazinesWaiting();
          }
        });
    }
  }

  updateMagazine(){
    if(this.updateForm.valid){
      this.magazineSelected.updateDate = formatDate(this.updateForm.value.updateAcceptDate, 'yyyy-MM-dd', 'en-US');
      this.magazineSelected.dayCost = this.updateForm.value.updateDayCost;
      this.magazineService.updateMagazineInf(this.magazineSelected)
        .subscribe((created: Magazine)=>{
          if(created != null){
            window.alert("INFORMACIÓN MODIFICADA CON ÉXITO.");
            console.log(created.dayCost)
            this.optionForm = 0;
            this.updateForm.reset({
              "updateDayCost": null,
              "updateAcceptDate": null
            })
            this.getAllMagazines();
          }
        });
    }
  }
}
