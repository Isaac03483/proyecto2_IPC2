import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileEditorService} from "../../../../services/user/profile-editor.service";
import {User} from "../../../../../objects/classes/usuario/User";
import {UserType} from "../../../../../objects/enums/user/UserType";
import {Profile} from "../../../../../objects/classes/usuario/editor/Profile";
import {DomSanitizer} from "@angular/platform-browser";
import {Tag} from "../../../../../objects/classes/usuario/administrador/Tag";
import {ProfileAdminService} from "../../../../services/user/profile-admin.service";
import {EditorTag} from "../../../../../objects/classes/usuario/editor/EditorTag";
import {UpdateImage} from "../../../../../objects/classes/usuario/editor/UpdateImage";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  selectedFile: any = null;
  image: any = null;
  file: any = null;
  formGroup!: FormGroup;
  editorName: string = JSON.parse(<string>localStorage.getItem("editor"));
  description: string ="";
  hobbies: string= "";
  likes: string = "";
  optionNav: number = 1;
  tags: Array<Tag> = [];
  editorTags: Array<Tag> =[];
  tagForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private service: ProfileEditorService, private sanitizer: DomSanitizer, private tagService: ProfileAdminService) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({

      userDescription: [null, Validators.required],
      userHobbies: [null, Validators.required],
      userLikes: [null, Validators.required]
    });

    this.tagForm = this.formBuilder.group({
      tag: [null, Validators.required]
    })
    this.reLoad();
    this.getTags();
    this.getEditorTags()
  }

  fileUploadInAngular(event: any) {
    const file = event.target.files[0];
    this.getBase64(file).then((image: any) =>{
      this.selectedFile = file;
      this.image = image.base;
      console.log(this.image);
    })

  }

  getTags(){

    this.tagService.getTags()
      .subscribe((data: Array<Tag>) =>{
        if(data != null){
          this.tags = data;
        }
      })
  }

  reLoad(){
    this.service.getProfile(new User(this.editorName, "", UserType.EDITOR))
      .subscribe((created:Profile) => {
        if(created != null){
          this.image = created.image;
          console.log(this.image);
          this.description = created.description;
          this.hobbies = created.hobby;
          this.likes = created.likes;
        }
      },(error: any)=>{
        window.alert(error);
      });
  }

  updateImage(){

    if(this.selectedFile   != null){
      this.service.updateImage(new UpdateImage(this.editorName, this.image))
        .subscribe((data:UpdateImage) =>{
          if(data != null){
            window.alert("SE HA CAMBIADO LA IMAGEN.");
          }
        }, (error) =>{
          window.alert("ERROR AL INTENTAR CAMBIAR DE IMAGEN, PRUEBE CON OTRA.");
        })
    }
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

  updateProfile() {

    if(this.formGroup.valid){

      if(this.description === this.formGroup.value.userDescription && this.hobbies === this.formGroup.value.userHobbies
      && this.likes === this.formGroup.value.userLikes){
        window.alert("NO SE HA MODIFICADO LA INFORMACIÓN.");
      } else {
        this.service.updateProfile(new Profile(this.editorName, "", this.formGroup.value.userHobbies, this.formGroup.value.userDescription, this.formGroup.value.userLikes))
          .subscribe((created:Profile) =>{
            if(created!= null){
              window.alert("INFORMACIÓN ACTUALIZADA CON ÉXITO.");
              this.description = this.formGroup.value.userDescription;
              this.hobbies = this.formGroup.value.userHobbies;
              this.likes = this.formGroup.value.userLikes;
              this.formGroup.reset({
                "userDescription": null,
                "userHobbies": null,
                "userLikes": null,
              })
            } else {
              window.alert("ERROR AL INTENTAR GUARDAR.");
            }
          },(error: any) =>{
            window.alert("ERROR CAPA 8.");
          })
      }
    }
  }

  setOption(number: number) {
    this.optionNav= number;
  }

  getEditorTags(){
    this.service.getEditorTags(this.editorName)
      .subscribe((data:Array<EditorTag>) =>{
        if(data != null){
          this.editorTags =data;
        }
      })
  }

  deleteTag(tagName: string) {

    this.service.deleteTag(new EditorTag(this.editorName, tagName))
      .subscribe((data: EditorTag)=>{
        if(data != null){
          window.alert("ETIQUETA ELIMINADA CON ÉXITO.");
          this.getEditorTags();
        }
      })
  }

  addTag() {

    if(this.tagForm.valid){
      this.service.addTag(new EditorTag(this.editorName, this.tagForm.value.tag))
        .subscribe((data: EditorTag)=>{
          if(data != null){
            window.alert("ETIQUETA AGREGADA CON ÉXITO.");
            this.getEditorTags();
            this.tagForm.reset({
              "tag": null
            });
          } else{
            window.alert("ERROR AL INTENTAR AGREGAR UNA ETIQUETA QUE YA POSEE.")
          }
        }, (error: any)=>{

        });
    }
  }
}
