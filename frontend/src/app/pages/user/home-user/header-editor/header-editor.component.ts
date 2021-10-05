import { HomeEditorService } from './../../../../services/user/home-editor.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-editor',
  templateUrl: './header-editor.component.html',
  styleUrls: ['./header-editor.component.css']
})
export class HeaderEditorComponent implements OnInit {

  constructor(private service: HomeEditorService) { }

  ngOnInit(): void {
  }

  setOption(option: number){
    this.service.opcion= option;
  }
}
