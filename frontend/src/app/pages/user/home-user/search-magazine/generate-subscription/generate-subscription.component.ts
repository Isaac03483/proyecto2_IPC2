import {Component, Input, OnInit} from '@angular/core';
import {PaymentEnum} from "../../../../../../objects/enums/user/editor/PaymentEnum";
import {formatDate} from "@angular/common";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Magazine} from "../../../../../../objects/classes/magazine/Magazine";
import {SubscriptionService} from "../../../../../services/subscription/subscription.service";
import {Subscription} from "../../../../../../objects/classes/usuario/editor/Subscription";
import {SubscriptionStatus} from "../../../../../../objects/enums/user/editor/SubscriptionStatus";
import {SubscriptionLike} from "../../../../../../objects/enums/user/editor/SubscriptionLike";
import {EditorAccount} from "../../../../../../objects/classes/usuario/editor/EditorAccount";
import {Impuesto} from "../../../../../../objects/classes/usuario/administrador/Impuesto";
import {ProfileAdminService} from "../../../../../services/user/profile-admin.service";
import {SearchMagazineService} from "../../../../../services/magazine/search-magazine.service";

@Component({
  selector: 'app-generate-subscription',
  templateUrl: './generate-subscription.component.html',
  styleUrls: ['./generate-subscription.component.css']
})
export class GenerateSubscriptionComponent implements OnInit {

  @Input() magazine!: Magazine;
  payInterval= PaymentEnum;
  subscriptionForm!: FormGroup;
  endDate: any = null;
  impPercentage: number = 0;
  constructor(private formBuilder: FormBuilder, private subscriptionService: SubscriptionService, private impService: ProfileAdminService,private searchService: SearchMagazineService) { }

  ngOnInit(): void {
    this.subscriptionForm = this.formBuilder.group({
      intervalOption: [null, Validators.required],
      time: [null, Validators.required],
      startDate: [null, Validators.required]
    })
    this.getImp();
  }

  getImp(){
    this.impService.getImp()
      .subscribe((data:Impuesto)=>{
        if(data != null){
          this.impPercentage =data.percentage;
        }
      })
  }

  updateEndDate() {
    if(this.subscriptionForm.value.startDate !== ""){
      let startDate = new Date(formatDate(this.subscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US'));
      console.log(startDate);
      if(this.subscriptionForm.value.intervalOption == PaymentEnum.MENSUAL){
        this.endDate = formatDate(new Date(startDate.getUTCFullYear(), startDate.getUTCMonth()+this.subscriptionForm.value.time, startDate.getUTCDate()),'yyyy-MM-dd', 'en-US');
      } else if(this.subscriptionForm.value.intervalOption == PaymentEnum.ANUAL) {
        this.endDate = formatDate(new Date(startDate.getUTCFullYear() +this.subscriptionForm.value.time, startDate.getUTCMonth(), startDate.getUTCDate()),'yyyy-MM-dd', 'en-US');
      } else {
        console.log("error")
      }
    } else{
      window.alert("LA FECHA NO PUEDE ESTAR VACÍA.")
      this.endDate = null;
    }
  }

  addSubscription() {
    if(this.subscriptionForm.valid){
      let totalPay = this.magazine.subscriptionCost * this.subscriptionForm.value.time;
      this.subscriptionService.addSubscription(new Subscription(0, JSON.parse(<string>localStorage.getItem("editor")),this.magazine.magazineRecord, this.magazine.magazineName,totalPay,this.subscriptionForm.value.intervalOption,formatDate(this.subscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US'),this.endDate,
        SubscriptionStatus.VIGENTE, SubscriptionLike.NO))
        .subscribe((created: Subscription)=>{
          if(created != null){

            let perdida = totalPay * (this.impPercentage/100);
            let ganancia = totalPay - perdida;
            this.subscriptionService.addEditorAccount(new EditorAccount(0,this.magazine.editorName,JSON.parse(<string>localStorage.getItem("editor")),this.magazine.magazineRecord,totalPay,perdida,ganancia,formatDate(this.subscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US')))
              .subscribe((created:EditorAccount)=>{
                window.alert("SUSCRIPCIÓN REALIZADA CON ÉXITO. REVISE EN EL APARTADO DE 'MIS SUSCRIPCIONES' PARA VISUALIZAR ESTA REVISTA.");
                this.endDate = null;
                this.searchService.option = 1;
              })
          } else{
            window.alert("YA SE ENCUENTRA SUSCRITO A ESTA REVISTA.");
            this.endDate = null;
            this.searchService.option = 1;
          }
        })
    }
  }
}
