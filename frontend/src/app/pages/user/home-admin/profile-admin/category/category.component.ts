import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileAdminService} from "../../../../../services/user/profile-admin.service";
import {Category} from "../../../../../../objects/classes/usuario/administrador/Category";
import {ColumnMode} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryForm!: FormGroup;
  upForm!: FormGroup;
  delForm!: FormGroup;
  categoryRecord: number = 0;
  categoryName: string="";
  categoryList: Array<Category> = [];
  optionCategory: number =0;

  constructor(private formBuilder: FormBuilder, private service: ProfileAdminService) { }

  ngOnInit(): void {
    this.setReload();
    this.categoryForm = this.formBuilder.group({
      categoryName: [null, Validators.required]
    });

    this.upForm = this.formBuilder.group({
      categoryRecord: [null],
      categoryName: [null, Validators.required]
    });

    this.delForm = this.formBuilder.group({
      categoryRecord: [null],
      categoryName:[null]

    });
  }

  setOption(option:number){
    this.optionCategory=option;
  }

  addCategory(){
    if(this.categoryForm.valid){
      this.service.addCategory(new Category(this.categoryForm.value.categoryName, 0))
        .subscribe((created: Category) =>{
          if(created != null){
            console.log(created);
            window.alert("CATEGORIA AGREGADA CON ÉXITO.");
            this.setReload();
          } else {
            window.alert("ALGO SALIÓ MAL, PARECE QUE ESTA CATEGORIA YA EXISTE.")
          }
        }, (error: any) =>{
            window.alert("ERROR CAPA OCHO.")
        });
    }
  }

  updateInf(record: number){
    for(var i = 0; i < this.categoryList.length; i++){
      if(this.categoryList[i].categoryRecord == record){
        this.categoryRecord = record;
        this.categoryName= this.categoryList[i].categoryName;
        return;
      }
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
    if(this.upForm.valid){
      this.service.updateCategory(new Category(this.categoryName, this.categoryRecord))
        .subscribe((created: Category) =>{
          //console.log(this.categoryRecord);
          if(created != null){
            console.log(created);
            window.alert("CATEGORIA ACTUALIZADA CON ÉXITO.");
            this.setReload();
          } else {
            window.alert("ALGO SALIÓ MAL, PARECE QUE ESTA CATEGORIA YA EXISTE.")
          }
        }, (error: any) =>{
          window.alert("EL REGISTRO DE LA CATEGORÍA NO FUE ENCONTRADO.")
        });
    }
  }
  deleteCategory(){
    //console.log(this.delForm.value.categoryName);
    if(this.delForm.value.categoryName != null){
      this.service.deleteCategory(new Category(this.categoryName, this.categoryRecord))
        .subscribe((created: Category) =>{
          //console.log(this.delForm.value.categoryRecord);
          if(created != null){
            console.log(created);
            window.alert("CATEGORIA ELIMINADA CON ÉXITO.");
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

  selectInf(option: number, record: number, name:string){
    this.setOption(option);
    this.categoryRecord = record;
    this.categoryName = name;
  }
}
