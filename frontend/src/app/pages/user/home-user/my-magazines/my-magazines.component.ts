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
  editorMagazines: Array<Magazine> = [];
  magazineForm!: FormGroup;
  magazineLikeEnum = MagazineLike;
  magazineCommentEnum = MagazineComment;
  magazineSubscriptionEnum = MagazineSubscription;
  editorName: string = JSON.parse(<string>localStorage.getItem("editor"));

  constructor(private categoryTagService: ProfileAdminService, private formBuilder: FormBuilder, private magazineService: MagazineService) { }

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

  changeFile($event: Event) {
    const files = ($event.target as HTMLInputElement).files;

    if(files != null){
      this.selectedFile = files.item(0);
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedFile);

      reader.onload = function load(this: any){
        this.fileView = reader.result;
      }.bind(this);
      this.file = files;

      console.log(this.selectedFile);
      console.log(this.file);
    }
  }

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

        console.log(this.magazineForm.value.mCategory);
        this.magazineService.addMagazine(new Magazine(0, this.editorName, this.magazineForm.value.magazineName, this.file,formatDate(new Date(this.magazineForm.value.mDate), "yyyy-MM-dd","en-US"),
          this.magazineForm.value.mDescription,new Category(this.magazineForm.value.mCategory),this.magazineForm.value.mCost,"",MagazineStatus.ENESPERA, 0,"",this.magazineForm.value.mLike,this.magazineForm.value.mComment, this.magazineForm.value.mSubs), this.selectedFile)
          .subscribe((created:Object) =>{
            if(created != null){
              window.alert("REVISTA GUARDADA CON ÉXITO.");
              this.getMagazines();
              console.log(created);
            }
          })
      }
    }
  }

  getMagazines(){

    this.magazineService.getMagazines()
      .subscribe(data =>{
        if(data != null){
          this.editorMagazines = data;
          console.log(this.editorMagazines[0].magazineName);
        }
      })
  }

  selectInf(number: number, magazine: Magazine) {
    this.option = number;
    console.log(magazine);
  }
}
