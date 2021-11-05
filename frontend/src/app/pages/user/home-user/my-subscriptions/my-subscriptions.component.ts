import {Component, OnInit} from '@angular/core';
import {SubscriptionService} from "../../../../services/subscription/subscription.service";
import {Subscription} from "../../../../../objects/classes/usuario/editor/Subscription";
import {SubscriptionStatus} from "../../../../../objects/enums/user/editor/SubscriptionStatus";
import {Magazine} from "../../../../../objects/classes/magazine/Magazine";
import {DeleteSubscription} from "../../../../../objects/classes/usuario/editor/DeleteSubscription";
import {Impuesto} from "../../../../../objects/classes/usuario/administrador/Impuesto";
import {formatDate} from "@angular/common";
import {PaymentEnum} from "../../../../../objects/enums/user/editor/PaymentEnum";
import {UpdateSubscriptionInf} from "../../../../../objects/classes/usuario/editor/UpdateSubscriptionInf";
import {EditorAccount} from "../../../../../objects/classes/usuario/editor/EditorAccount";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MagazineService} from "../../../../services/magazine/magazine.service";
import {ProfileAdminService} from "../../../../services/user/profile-admin.service";
import {SubscriptionLike} from "../../../../../objects/enums/user/editor/SubscriptionLike";
import {Comment} from "../../../../../objects/classes/usuario/editor/Comment";
import {SubscriptionLikesCount} from "../../../../../objects/classes/usuario/editor/SubscriptionLikesCount";
import {UpdateSubscriptionLike} from "../../../../../objects/classes/usuario/editor/UpdateSubscriptionLike";
import {MagazineComment} from "../../../../../objects/enums/magazine/MagazineComment";
import {MagazineLike} from "../../../../../objects/enums/magazine/MagazineLike";
import {ProfileEditorService} from "../../../../services/user/profile-editor.service";
import {Profile} from "../../../../../objects/classes/usuario/editor/Profile";
import {User} from "../../../../../objects/classes/usuario/User";
import {UserType} from "../../../../../objects/enums/user/UserType";

@Component({
  selector: 'app-my-subscriptions',
  templateUrl: './my-subscriptions.component.html',
  styleUrls: ['./my-subscriptions.component.css']
})
export class MySubscriptionsComponent implements OnInit {

  updateSubscriptionForm!: FormGroup;
  endDate: any = null;
  impPercentage: number = 0;
  payInterval= PaymentEnum;
  magazine!: Magazine;
  editorProfile!: Profile;
  viewProfile: boolean = false;
  fileView: any = null;
  subscriptionLikeEnum = SubscriptionLike;
  magazineCommentEnum = MagazineComment;
  magazineLikeEnum = MagazineLike;
  commentList: Array<Comment> = [];
  commentForm!: FormGroup;
  subscriptionLikesCount: number = 0;
  constructor(private subscriptionService: SubscriptionService,private magazineService: MagazineService, private impService: ProfileAdminService, private formBuilder: FormBuilder, private profileService: ProfileEditorService) { }

  editorSubscriptions: Array<Subscription> =[];
  subscriptionStatus = SubscriptionStatus;
  subscriptionSelected!: Subscription;

  ngOnInit(): void {
    this.getEditorSubscriptions();
    this.updateSubscriptionForm = this.formBuilder.group({
      intervalOption: [null, Validators.required],
      time: [null, Validators.required],
      startDate: [null, Validators.required]
    });
    this.commentForm = this.formBuilder.group({
      text: [null, Validators.required],
      commentDate: [null, Validators.required]
    })
  }

  getEditorSubscriptions(){
    this.subscriptionService.getEditorSubscriptions()
      .subscribe(created =>{
        if(created != null){
          this.editorSubscriptions = created;
        }
      })
  }

  getSubscriptionLikesCount(){
    this.subscriptionLikesCount = 0;
    this.subscriptionService.getSubscriptionLikes(this.magazine.magazineRecord)
      .subscribe((data:SubscriptionLikesCount)=>{
        if(data != null){
          this.subscriptionLikesCount=data.likes;
        }
      })
  }

  deleteSubscription(subscription: Subscription) {

    this.subscriptionService.deleteSubscription(subscription.subscriptionRecord)
      .subscribe((data:DeleteSubscription)=>{
        if(data != null){
          window.alert("SUSCRIPCIÓN ELIMINADA CON ÉXITO.");
          this.getEditorSubscriptions();
        }
      })
  }

  setOption(number: number, subscription:Subscription) {

    this.subscriptionSelected = subscription;
    this.subscriptionService.optionSubscription = number;
    this.getImp();
    this.getMagazineInfo();
  }

  getOption(){
    return this.subscriptionService.optionSubscription;
  }

  getImp(){
    this.impService.getImp()
      .subscribe((data:Impuesto)=>{
        if(data != null){
          this.impPercentage =data.percentage;
        }
      })
  }

  getMagazineInfo(){
    this.magazineService.getMagazineWithRecord(this.subscriptionSelected.magazineRecord)
      .subscribe((data: Magazine)=>{
        if(data != null){
          this.viewProfile = false;
          this.magazine = data;
          this.getMagazineComments();
          this.getSubscriptionLikesCount();
          this.getEditorProfile();
          this.fileView = this.magazine.file;
          console.log(this.magazine)
        } else {
          console.log("REVISTA NO ENCONTRADA.");
        }
      })
  }

  getMagazineComments(){
    this.magazineService.getMagazineComment(this.magazine.magazineRecord)
      .subscribe((data:Array<Comment>)=>{
        if(data !=null){
          this.commentList = data;
          console.log(data);
        }
      })
  }

  updateEndDate() {
    if(this.updateSubscriptionForm.value.startDate !== ""){
      let startDate = new Date(formatDate(this.updateSubscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US'));
      console.log(startDate);
      if(this.updateSubscriptionForm.value.intervalOption == PaymentEnum.MENSUAL){
        this.endDate = formatDate(new Date(startDate.getUTCFullYear(), startDate.getUTCMonth()+this.updateSubscriptionForm.value.time, startDate.getUTCDate()),'yyyy-MM-dd', 'en-US');
      } else if(this.updateSubscriptionForm.value.intervalOption == PaymentEnum.ANUAL) {
        this.endDate = formatDate(new Date(startDate.getUTCFullYear() +this.updateSubscriptionForm.value.time, startDate.getUTCMonth(), startDate.getUTCDate()),'yyyy-MM-dd', 'en-US');
      } else {
        console.log("error")
      }
    } else{
      window.alert("LA FECHA NO PUEDE ESTAR VACÍA.")
      this.endDate = null;
    }
  }

  updateSubscription() {

    if (this.updateSubscriptionForm.valid) {
      let totalPay = this.magazine.subscriptionCost * this.updateSubscriptionForm.value.time;
      this.subscriptionService.updateSubscriptionInf(new Subscription(this.subscriptionSelected.subscriptionRecord, JSON.parse(<string>localStorage.getItem("editor")), this.magazine.magazineRecord, this.magazine.magazineName, totalPay, this.updateSubscriptionForm.value.intervalOption, formatDate(this.updateSubscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US'), this.endDate,
        SubscriptionStatus.VIGENTE, this.subscriptionSelected.subscriptionLike))
        .subscribe((created: UpdateSubscriptionInf) => {
          if (created != null) {

            let perdida = totalPay * (this.impPercentage/100);
            let ganancia = totalPay - perdida;
            this.subscriptionService.addEditorAccount(new EditorAccount(0, this.magazine.editorName, JSON.parse(<string>localStorage.getItem("editor")), this.magazine.magazineRecord, totalPay, perdida, ganancia, formatDate(this.updateSubscriptionForm.value.startDate, 'yyyy-MM-dd', 'en-US')))
              .subscribe((created: EditorAccount) => {
                window.alert("SUSCRIPCIÓN ACTUALIZADA CON ÉXITO. REVISE EN EL APARTADO DE 'MIS SUSCRIPCIONES' PARA VISUALIZAR ESTA REVISTA.");
                this.endDate = null;
                this.subscriptionService.optionSubscription=0;
                this.getEditorSubscriptions();
              })
          } else {
            window.alert("YA SE ENCUENTRA SUSCRITO A ESTA REVISTA.");
            this.endDate = null;
          }
        })
    }
  }

  viewSubscription(number: number, subscription: Subscription) {
    this.subscriptionSelected = subscription;
    this.getMagazineInfo();
    this.subscriptionService.optionSubscription=number;
  }

  changeLike(option: SubscriptionLike) {
    console.log(option);
    this.subscriptionService.updateSubscriptionLike(this.subscriptionSelected.subscriptionRecord, option)
      .subscribe((data:UpdateSubscriptionLike)=>{
        if(data != null){
          this.subscriptionSelected.subscriptionLike= option;
          this.getSubscriptionLikesCount();
          console.log(this.subscriptionLikesCount)
        }
      })
  }

  addComment() {
    if(this.commentForm.valid){
      console.log("ENTRA AL FORM.");
      this.magazineService.addMagazineComment(new Comment(0,this.magazine.magazineRecord,this.subscriptionSelected.subscriberName,this.commentForm.value.text,formatDate(this.commentForm.value.commentDate, 'yyyy-MM-dd','en-US')))
        .subscribe((data: Comment)=>{
          if(data != null){
            console.log("se creo el comentario.");
            this.getMagazineComments();
            console.log(this.commentList)
            this.commentForm.reset({
              "text": null,
              "commentDate": null
            })
          }
        })
    }
  }

  private getEditorProfile() {
    this.profileService.getProfile(new User(this.magazine.editorName, "", UserType.EDITOR))
        .subscribe((data: Profile)=>{
          if(data != null){
            this.editorProfile = data;
          }
        })
  }

  setProfileVisibility(b: boolean) {
    this.viewProfile = b;
  }
}
