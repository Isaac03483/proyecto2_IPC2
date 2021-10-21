import { HomeEditorService } from '../../../../services/user/home-editor.service';
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {SubscriptionService} from "../../../../services/subscription/subscription.service";

@Component({
  selector: 'app-header-editor',
  templateUrl: './header-editor.component.html',
  styleUrls: ['./header-editor.component.css']
})
export class HeaderEditorComponent implements OnInit {

  constructor(private service: HomeEditorService, private subscriptionService: SubscriptionService, private router: Router) { }

  ngOnInit(): void {
  }

  setOption(option: number){
    this.service.opcion= option;
    this.subscriptionService.optionSubscription = 0;
  }

  close(){
    localStorage.removeItem("editor");
    this.service.opcion = 1;
    this.router.navigate(["/"]);
  }
}
