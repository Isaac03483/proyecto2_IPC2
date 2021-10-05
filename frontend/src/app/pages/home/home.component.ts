import { HomeService } from './../../services/form-control/home.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private service: HomeService) { }

  ngOnInit(): void {

  }

  getOption(){
    console.log(this.service.opcion);
    return this.service.opcion;
  }
}
