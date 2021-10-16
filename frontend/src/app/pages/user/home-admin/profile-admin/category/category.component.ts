import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileAdminService} from "../../../../../services/user/profile-admin.service";
import {Category} from "../../../../../../objects/classes/usuario/administrador/Category";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {CategoryUpdate} from "../../../../../../objects/classes/usuario/administrador/CategoryUpdate";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryForm!: FormGroup;
  upForm!: FormGroup;
  categoryName: string="";
  categoryList: Array<Category> = [];
  optionCategory: number =1;

  constructor(private formBuilder: FormBuilder, private service: ProfileAdminService) { }

  ngOnInit(): void {
    this.setReload();
    this.categoryForm = this.formBuilder.group({
      categoryName: [null, Validators.required]
    });

    this.upForm = this.formBuilder.group({
      categoryName: [null, Validators.required]
    });
  }

  setOption(option:number){
    this.optionCategory=option;
  }

  addCategory(){
    if(this.categoryForm.valid){
      this.service.addCategory(new Category(this.categoryForm.value.categoryName))
        .subscribe((created: Category) =>{
          if(created != null){
            console.log(created);
            window.alert("CATEGORIA AGREGADA CON ÉXITO.");
            this.categoryForm.reset({
              "categoryName": null
            })
            this.setReload();
          } else {
            window.alert("ALGO SALIÓ MAL, PARECE QUE ESTA CATEGORIA YA EXISTE.")
          }
        }, (error: any) =>{
            window.alert("ERROR CAPA OCHO.")
        });
    }
  }



  setReload(){
    this.service.listCategories()
      .subscribe(categories => {
        console.log(categories);
        this.categoryList = categories;
      });
  }

  updateCategory(){
    //console.log(this.categoryName);
    if(this.categoryName !== ""){
      if(this.upForm.valid){
        this.service.updateCategory(new CategoryUpdate(this.upForm.value.categoryName, this.categoryName))
          .subscribe((created: Category | CategoryUpdate) =>{
            //console.log(this.categoryRecord);
            if(created != null){
              console.log(created);
              window.alert("CATEGORIA ACTUALIZADA CON ÉXITO.");
              this.upForm.reset({
                "categoryName": null
              });
              this.categoryName = "";
              this.setReload();
            } else {
              window.alert("ALGO SALIÓ MAL, PARECE QUE ESTA CATEGORIA YA EXISTE.")
            }
          }, (error: any) =>{
            window.alert("EL REGISTRO DE LA CATEGORÍA NO FUE ENCONTRADO.")
          });
      }
    } else {
      window.alert("NO SE HA SELECCIONADO UNA CATEGORÍA.");
    }
  }
  deleteCategory(){
    //console.log(this.delForm.value.categoryName);
    if(this.categoryName !== ""){
      this.service.deleteCategory(new Category(this.categoryName))
        .subscribe((created: Category) =>{
          //console.log(this.delForm.value.categoryRecord);
          if(created != null){
            console.log(created);
            window.alert("CATEGORIA ELIMINADA CON ÉXITO.");
            this.categoryName = "";
            this.setReload();
          } else {
            window.alert("ALGO SALIÓ MAL, PARECE QUE ESTA CATEGORIA NO EXISTE.")
          }
        }, (error: any) =>{
          window.alert("ERROR CAPA OCHO.")
        });
    } else {
      window.alert("NO SE HA SELECCIONADO UNA CATEGORIA.")
    }

  }

  selectInf(option: number, name:string){
    this.setOption(option);
    this.categoryName = name;
  }
}
