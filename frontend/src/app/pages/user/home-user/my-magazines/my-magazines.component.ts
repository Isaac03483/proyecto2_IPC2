import {Component, OnInit} from '@angular/core';
import {Category} from "../../../../../objects/classes/usuario/administrador/Category";
import {ProfileAdminService} from "../../../../services/user/profile-admin.service";
import {Tag} from "../../../../../objects/classes/usuario/administrador/Tag";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MagazineLike} from "../../../../../objects/enums/magazine/MagazineLike";
import {MagazineComment} from "../../../../../objects/enums/magazine/MagazineComment";
import {MagazineSubscription} from "../../../../../objects/enums/magazine/MagazineSubscription";
import {MagazineService} from "../../../../services/magazine/magazine.service";
import {Magazine} from "../../../../../objects/classes/magazine/Magazine";
import {formatDate} from "@angular/common";
import {MagazineStatus} from "../../../../../objects/enums/magazine/MagazineStatus";
import {MagazineTag} from "../../../../../objects/classes/magazine/MagazineTag";
import {applySourceSpanToExpressionIfNeeded} from "@angular/compiler/src/output/output_ast";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-my-magazines',
  templateUrl: './my-magazines.component.html',
  styleUrls: ['./my-magazines.component.css']
})
export class MyMagazinesComponent implements OnInit {

  option: number = 1;
  selectedFile: any = null;
  fileView: any = null;
  file: any = null;
  categories: Array<Category> = [];
  tags: Array<Tag> = [];
  magazineTagsSelected: Array<Tag> = [];
  magazineTagsUpdate: Array<MagazineTag> =[];
  magazineSelected!: Magazine;
  editorMagazines: Array<Magazine> = [];
  magazineForm!: FormGroup;
  magazineLikeEnum = MagazineLike;
  magazineCommentEnum = MagazineComment;
  magazineSubscriptionEnum = MagazineSubscription;
  editorName: string = JSON.parse(<string>localStorage.getItem("editor"));

  constructor(private categoryTagService: ProfileAdminService, private formBuilder: FormBuilder, private magazineService: MagazineService,  private sanitizer: DomSanitizer) { }

  ngOnInit(): void {

    this.magazineForm = this.formBuilder.group({

      magazineName: [null, Validators.required],
      mCategory: [null, Validators.required],
      mDescription: [null, Validators.required],
      mDate: [null, Validators.required],
      mCost: [null, Validators.required],
      mLike: [null, Validators.required],
      mComment: [null, Validators.required],
      mSubs: [null, Validators.required]
    });

    this.getCategories();
    this.getTags();
    this.getMagazines()
  }

  setOption(number: number) {

    this.option = number;
  }

  getCategories(){
    this.categoryTagService.listCategories()
      .subscribe(created =>{
        if(created != null){
          this.categories = created;
          console.log(this.categories);
        }
      });
  }

  getTags(){
    this.categoryTagService.getTags()
      .subscribe(created =>{

        if(created != null){
          this.tags = created;
          console.log(this.tags);
        }
      });
  }

  changeFile(event: any) {
    const file = event.target.files[0];
    this.getBase64(file).then((image: any) =>{
      this.selectedFile = file;
      this.fileView = image.base;
      console.log(this.fileView);
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

  addTag(tagName: string) {
    let found: boolean = false;

    for(var i = 0; i < this.magazineTagsSelected.length; i++){
      if(this.magazineTagsSelected[i].tagName === tagName){
        found = true;
        break;
      }
    }

    if(!found){
      this.magazineTagsSelected.push(new Tag(tagName));
      console.log(this.magazineTagsSelected);
    }
  }

  removeTag(tagName: string) {
    this.magazineTagsSelected = this.magazineTagsSelected.filter(item => item.tagName !== tagName);
  }

  addMagazine() {
    if(this.magazineForm.valid){
      if(this.selectedFile != null){
        this.magazineService.addMagazine(new Magazine(0, this.editorName, this.magazineForm.value.magazineName, this.fileView,formatDate(this.magazineForm.value.mDate, 'yyyy-MM-dd','en-US'),
          this.magazineForm.value.mDescription,new Category(this.magazineForm.value.mCategory),this.magazineForm.value.mCost,"",MagazineStatus.ENESPERA, 0,"",this.magazineForm.value.mLike,this.magazineForm.value.mComment, this.magazineForm.value.mSubs))
          .subscribe((created:Magazine) =>{
            if(created != null){

              for(var i = 0; i < this.magazineTagsSelected.length; i++){
                this.magazineService.addMagazineTags(new MagazineTag(created.magazineRecord,this.magazineTagsSelected[i].tagName))
                  .subscribe((magazineTag: MagazineTag) =>{
                    if(magazineTag == null){
                      window.alert("NO SE PUDO AGREGAR LA ETIQUETA: "+this.magazineTagsSelected[i].tagName);
                    }
                  });
              }

              window.alert("REVISTA CREADA CON ÉXITO");
              this.magazineForm.reset({
                "magazineName": null,
                "mCategory":null,
                "mDescription": null,
                "mDate": null,
                "mCost": null,
                "mLike": null,
                "mComment": null,
                "mSubs": null
              });
              this.magazineTagsSelected =[];
              this.fileView = null;
              this.selectedFile = null;
              this.getMagazines();

            }
          },(error: any)=>{

            console.log("ERROR CAPA 8")
          })
      }
    }
  }

  getMagazines(){

    this.magazineService.getMagazines()
      .subscribe((data:Array<Magazine>) =>{
        if(data != null){
          this.editorMagazines = data;
        }
      })
  }

  selectInf(number: number, magazine: Magazine) {
    this.option = number;
    this.magazineSelected = magazine;
    this.magazineService.getMagazineTags(this.magazineSelected)
      .subscribe((created:Array<MagazineTag>) =>{
        if(created != null){
          this.magazineTagsUpdate = created;
        }
      });

  }

  updateTag(tagName: string) {
    let found: boolean = false;

    for(var i = 0; i < this.magazineTagsUpdate.length; i++){
      if(this.magazineTagsUpdate[i].tagName === tagName){
        found = true;
        break;
      }
    }

    if(!found){
      this.magazineTagsUpdate.push(new MagazineTag(this.magazineSelected.magazineRecord,tagName));
    }
  }

  removeTagToUpdate(tagName: string) {
    this.magazineTagsUpdate = this.magazineTagsUpdate.filter(item => item.tagName !== tagName);
    console.log(this.magazineTagsUpdate+" "+this.magazineTagsUpdate.length);
  }

  updateMagazine() {
    if(this.magazineSelected != null){
      console.log(this.magazineSelected.magazineRecord);
        this.magazineService.updateMagazine(new Magazine(this.magazineSelected.magazineRecord, this.editorName, this.magazineSelected.magazineName, "",formatDate(this.magazineSelected.publicationDate, 'yyyy-MM-dd','en-US'),
          this.magazineSelected.description,this.magazineSelected.category,this.magazineSelected.subscriptionCost,"",this.magazineSelected.status, 0,"",this.magazineSelected.like,this.magazineSelected.comment, this.magazineSelected.subscription))
          .subscribe((created:Magazine) =>{
            if(created != null){
              this.magazineService.deleteAllMagazineTags(this.magazineSelected)
                .subscribe((data:Magazine) =>{

                    for(var i = 0; i < this.magazineTagsUpdate.length; i++){
                      this.magazineService.addMagazineTags(new MagazineTag(created.magazineRecord,this.magazineTagsUpdate[i].tagName))
                        .subscribe((magazineTag: MagazineTag) =>{
                          if(magazineTag == null){
                            window.alert("NO SE PUDO AGREGAR LA ETIQUETA: "+this.magazineTagsUpdate[i].tagName);
                          }
                        });
                    }

                });
              window.alert("REVISTA ACTUALIZADA CON ÉXITO");
              this.magazineTagsUpdate =[];
              this.option = 4;
              this.getMagazines();

            }
          },(error: any)=>{

          })

    }
  }

  updateCategory(category: Category) {
    this.magazineSelected.category = category;
  }

  updateDescription() {
    this.magazineSelected.description = (document.getElementById("descriptionToUpdate") as HTMLInputElement).value;
    console.log(this.magazineSelected.description);
  }

  updateName() {
    this.magazineSelected.magazineName = (document.getElementById("magazineUpdateName") as HTMLInputElement).value;
    console.log(this.magazineSelected.magazineName);
  }

  updateSubscriptionCost() {
    this.magazineSelected.subscriptionCost = Number(((document.getElementById("costToUpdate") as HTMLInputElement).value));
    console.log(this.magazineSelected.subscriptionCost);
  }

  updateLike(value: MagazineLike.SI | MagazineLike.NO) {

    this.magazineSelected.like = value;
    console.log(this.magazineSelected.like);
  }

  updateSubscription(value: MagazineSubscription.SI | MagazineSubscription.NO) {
    this.magazineSelected.subscription = value;
  }

  updateComment(value: MagazineComment.SI | MagazineComment.NO) {
    this.magazineSelected.comment = value;
  }

  deleteMagazine() {
    if(this.magazineSelected != null){

      this.magazineService.deleteMagazine(this.magazineSelected)
        .subscribe((created: Magazine)=>{
          if(created != null){
            window.alert("REVISTA ELIMINADA CON ÉXITO.");
            this.getMagazines();
          }
        });
    }
  }
}
