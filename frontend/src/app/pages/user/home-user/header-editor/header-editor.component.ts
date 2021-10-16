import { HomeEditorService } from '../../../../services/user/home-editor.service';
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-editor',
  templateUrl: './header-editor.component.html',
  styleUrls: ['./header-editor.component.css']
})
export class HeaderEditorComponent implements OnInit {

  constructor(private service: HomeEditorService, private router: Router) { }

  ngOnInit(): void {
  }

  setOption(option: number){
    this.service.opcion= option;
  }

  close(){
    localStorage.removeItem("editor");
    this.router.navigate(["/"]);
  }
}
