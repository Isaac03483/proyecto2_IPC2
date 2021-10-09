import { HomeAdminService } from './../../../services/user/home-admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})
export class HomeAdminComponent implements OnInit {

  constructor(private service: HomeAdminService) { }

  ngOnInit(): void {
  }

  getOption(){
    return this.service.option;
  }
}
