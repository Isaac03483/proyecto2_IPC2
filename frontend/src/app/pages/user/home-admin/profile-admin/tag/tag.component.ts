import { Component, OnInit } from '@angular/core';
import {Tag} from "../../../../../../objects/classes/usuario/administrador/Tag";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileAdminService} from "../../../../../services/user/profile-admin.service";
import {TagUpdate} from "../../../../../../objects/classes/usuario/administrador/TagUpdate";

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnInit {

  optionTag = 1;
  tagsList: Array<Tag> = [];
  tagForm!: FormGroup;
  tagName: string = "";
  constructor(private formBuilder: FormBuilder, private service: ProfileAdminService) { }

  ngOnInit(): void {
    this.tagForm = this.formBuilder.group({
      tagName: [null, Validators.required]
    });
    this.upload();
  }

  setOption(number: number) {
    this.optionTag = number;
  }

  upload(){
    this.service.getTags()
      .subscribe(created =>{
        if(created != null){
          this.tagsList = created;
        }
      });
  }

  addTag(){

    if(this.tagForm.valid){
      this.service.addTag(new Tag(this.tagForm.value.tagName))
        .subscribe((created: Tag) =>{
          if(created != null){
            window.alert("ETIQUETA AGREGADA CON ÉXITO.");
            this.tagForm.reset({
              "tagName": null
            });
            this.upload();
          } else {
            window.alert("ESTA ETIQUETA YA EXISTE.");
          }
        },(error: any) =>{

        });
    }
  }

  selectInf(number: number, tagName: string) {
    this.setOption(number);
    this.tagName = tagName;
  }

  updateTagName() {
    if(this.tagForm.valid){
      this.service.updateTag(new TagUpdate(this.tagForm.value.tagName,this.tagName))
        .subscribe((created:Tag | TagUpdate) => {
          if(created != null){
            console.log(created);
            window.alert("ETIQUETA MODIFICADA CON ÉXITO");
            this.tagForm.reset({
              "tagName": null
            })
            this.tagName="";
            this.upload();
          } else {
            window.alert("ERROR AL INTENTAR ACTUALIZAR, PRUEBE CON OTRO NOMBRE.")
          }
        }, (error: any) =>{
          window.alert("ERROR CAPA 8.");
        });
    }
  }

  deleteTag() {
    if(this.tagName !== ""){
      this.service.deleteTag(new Tag(this.tagName))
        .subscribe((created: Tag) =>{
          if(created != null){
            window.alert("ETIQUETA ELIMINADA CON ÉXITO.");
            this.upload();
            this.tagName = "";
          } else {
            window.alert("ERROR AL ELIMINAR LA ETIQUETA.");
          }
        });
    }else{
      window.alert("NO SE HA SELECCIONADO NINGUNA ETIQUETA.");
    }
  }
}
