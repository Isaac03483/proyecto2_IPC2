import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileEditorService} from "../../../../services/user/profile-editor.service";
import {User} from "../../../../../objects/classes/usuario/User";
import {UserType} from "../../../../../objects/enums/user/UserType";
import {Profile} from "../../../../../objects/classes/usuario/editor/Profile";
import {DomSanitizer} from "@angular/platform-browser";

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
  constructor(private formBuilder: FormBuilder, private service: ProfileEditorService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({

      userDescription: [null, Validators.required],
      userHobbies: [null, Validators.required],
      userLikes: [null, Validators.required]
    });

    this.reLoad();
  }

  fileUploadInAngular(event: Event) {
    const files = (event.target as  HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files.item(0);
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedFile);

      reader.onload = function load(this: any){
        this.image = reader.result;
        console.log(this.image)
      }.bind(this);
      this.file = files;
      console.log(this.file);
    }
  }

  reLoad(){
    this.service.getProfile(new User(this.editorName, "", UserType.EDITOR))
      .subscribe((created:Profile) => {
        if(created != null){
          let objectURL = 'data:image/png;base64,' + created.image;
          this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          this.description = created.description;
          this.hobbies = created.hobby;
          this.likes = created.likes;
        }
      },(error: any)=>{
        window.alert(error);
      });
  }

  updateImage(){

    if(this.file != null){
      this.service.updateImage(this.selectedFile)
        .subscribe((data) =>{
          console.log("es")
        }, (error) =>{
          console.log(error);
        })
    }
  }

  updateProfile() {

    if(this.formGroup.valid){

      if(this.description === this.formGroup.value.userDescription && this.hobbies === this.formGroup.value.userHobbies
      && this.likes === this.formGroup.value.userLikes){
        window.alert("NO SE HA MODIFICADO LA INFORMACIÓN.");
      } else {
        this.service.updateProfile(new Profile(this.editorName, null, this.formGroup.value.userHobbies, this.formGroup.value.userDescription, this.formGroup.value.userLikes))
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
}
