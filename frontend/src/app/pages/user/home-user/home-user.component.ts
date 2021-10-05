import { HomeEditorService } from './../../../services/user/home-editor.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-user',
  templateUrl: './home-user.component.html',
  styleUrls: ['./home-user.component.css']
})
export class HomeUserComponent implements OnInit {

  constructor(private service: HomeEditorService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap: any) => {
      console.log(paramMap);
    });
  }

  getOption(){
    return this.service.opcion;
  }

}
