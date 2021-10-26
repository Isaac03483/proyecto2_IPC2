import {Component, OnInit} from '@angular/core';
import {AdType} from "../../../../../../objects/classes/ads/AdType";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdService} from "../../../../../services/ads/ad.service";
import {formatDate} from "@angular/common";
import {Ad} from "../../../../../../objects/classes/ads/Ad";
import {AdStatus} from "../../../../../../objects/enums/ad/AdStatus";

@Component({
  selector: 'app-ad',
  templateUrl: './ad.component.html',
  styleUrls: ['./ad.component.css']
})
export class AdComponent implements OnInit {

  adTypeList: Array<AdType> =[];
  option: number = 1;
  adTypeToUpdate!: any;
  updateAdTypeCostForm!: FormGroup;
  endDate: any = null;
  adTypeSelected!: AdType;
  value: string = "";
  addNewAdForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private adService: AdService) { }

  ngOnInit(): void {
    this.updateAdTypeCostForm = this.formBuilder.group({
      dayCost: [null, Validators.required]
    });
    this.addNewAdForm = this.formBuilder.group({

      adType: [null, Validators.required],
      adName: [null, Validators.required],
      clientName: [null, Validators.required],
      adText: [null, Validators.required],
      adContent: [null, Validators.required],
      time: [null, Validators.required],
      startDate: [null, Validators.required]
    });
    this.getAllAdsType();
  }

  setOption(number: number) {
    this.option = number;
  }

  selectAdTypeToUpdate(adType: AdType) {
    this.adTypeToUpdate = adType;

  }

  getAllAdsType(){
    this.adService.getAdType()
      .subscribe((data:Array<AdType>)=>{
        if(data != null){
          this.adTypeList = data;
        }
      })
  }

  updateAdTypeCost() {
    if(this.updateAdTypeCostForm.valid){
      this.adService.updateAdTypeCost(new AdType(this.adTypeToUpdate.typeName, this.updateAdTypeCostForm.value.dayCost))
        .subscribe((data:AdType)=>{
          if(data != null){
            window.alert("COSTO ACTUALIZADO CON ÉXITO");
            this.adTypeToUpdate = null;
            this.updateAdTypeCostForm.reset({
              "dayCost": null
            })
            this.getAllAdsType();
          }
        })
    }
  }

  updateEndDate() {
    if(this.addNewAdForm.value.startDate !== ""){
      let startDate = new Date(formatDate(this.addNewAdForm.value.startDate, 'yyyy-MM-dd', 'en-US'));
      console.log(startDate);
      this.endDate = formatDate(new Date(startDate.getUTCFullYear(), startDate.getUTCMonth(), startDate.getUTCDate() +this.addNewAdForm.value.time),'yyyy-MM-dd', 'en-US');
    } else{
      window.alert("LA FECHA NO PUEDE ESTAR VACÍA.")
      this.endDate = null;
    }
  }

  updateAdTypeSelected(adType: AdType) {
    this.adTypeSelected = adType;
  }

  addNewAd() {
    if(this.addNewAdForm.valid){
      let totalPay = this.adTypeSelected.dayCost * this.addNewAdForm.value.time;
      this.adService.addNewAd(new Ad(0,this.adTypeSelected,this.addNewAdForm.value.adName,this.addNewAdForm.value.clientName,this.addNewAdForm.value.adText,this.addNewAdForm.value.adContent,
        0,totalPay,AdStatus.INACTIVO, "",formatDate(this.addNewAdForm.value.startDate, 'yyyy-MM-dd', 'en-US'),this.endDate))
        .subscribe((data:Ad)=>{
          if(data!= null){
            window.alert("ANUNCIO AGREGADO CON ÉXITO. PARA ACTIVAR DICHO ANUNCIO VAYA A LA OPCIÓN 'VER ANUNCIOS' Y FILTRE POR 'ANUNCIOS INACTIVOS'.");

            this.value = this.addNewAdForm.value.adText;
            console.log(this.value)
          }
        })

    }
  }
}
