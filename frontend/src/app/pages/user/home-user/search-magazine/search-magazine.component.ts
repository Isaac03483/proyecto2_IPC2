import { Component, OnInit } from '@angular/core';
import {ProfileAdminService} from "../../../../services/user/profile-admin.service";
import {Category} from "../../../../../objects/classes/usuario/administrador/Category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SearchMagazineService} from "../../../../services/magazine/search-magazine.service";
import {Magazine} from "../../../../../objects/classes/magazine/Magazine";

@Component({
  selector: 'app-search-magazine',
  templateUrl: './search-magazine.component.html',
  styleUrls: ['./search-magazine.component.css']
})
export class SearchMagazineComponent implements OnInit {

  categoryFilter: boolean = false;
  categoryList: Array<Category> = [];
  magazinesFound: Array<Magazine> =[];
  categoryNameSelected: string = "";
  searchMagazineForm!: FormGroup;
  magazineSelected!: Magazine;

  constructor(private categoryService: ProfileAdminService, private formBuilder: FormBuilder, private searchService: SearchMagazineService) { }

  ngOnInit(): void {
    this.searchService.option = 1;
    this.getAllCategories();
    this.searchMagazineForm = this.formBuilder.group({
      magazineName: [null, Validators.required]
    })
  }

  getAllCategories(){
    this.categoryService.listCategories()
      .subscribe((created:Array<Category>) =>{
        if(created != null){
          this.categoryList = created;
        }
      })
  }

  changeOption() {
    this.categoryFilter = !this.categoryFilter;
  }

  changeCategory(categoryName: string) {
    this.categoryNameSelected = categoryName;
  }

  searchMagazine(){

    if(this.searchMagazineForm.valid){
      if(this.categoryFilter){
        if(this.categoryNameSelected !== ""){
          this.searchService.searchMagazine(this.searchMagazineForm.value.magazineName, this.categoryNameSelected)
            .subscribe((created:Array<Magazine>)=>{

              if(created != null){
                this.magazinesFound = created;
              }
            })
        } else{
          alert("AL SELECCIONAR LA OPCIÓN FILTRAR DEBE SELECCIONAR UNA CATEGORÍA.");
        }
      } else {
        this.searchService.searchMagazine(this.searchMagazineForm.value.magazineName, "")
          .subscribe((created:Array<Magazine>)=>{

            if(created != null){
              this.magazinesFound = created;
              console.log(this.magazinesFound);
            } else {
              this.magazinesFound = [];
            }
          })
      }
    }
  }

  selectMagazine(magazine: Magazine) {
    this.magazineSelected = magazine;
    this.searchService.option = 2;
  }

  getOptionService(){
    return this.searchService.option;
  }

  setOption(number: number) {
    this.searchService.option = number;
  }
}
