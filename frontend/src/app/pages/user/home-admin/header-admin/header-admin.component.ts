import { HomeAdminService } from './../../../../services/user/home-admin.service';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Manager} from "../../../../../objects/classes/usuario/administrador/Manager";
import {ManagerStatus} from "../../../../../objects/enums/user/manager/ManagerStatus";
import {ProfileAdminService} from "../../../../services/user/profile-admin.service";
import {UsrService} from "../../../../services/user/usr.service";

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent implements OnInit {

  manager!: Manager;
  managerLog: Manager = JSON.parse(<string>localStorage.getItem("manager"));
  constructor(private userService: UsrService, private router: Router, private route: ActivatedRoute, private profileAdminService: ProfileAdminService, private service: HomeAdminService) { }

  ngOnInit(): void {
    if(this.managerLog == null){
      this.router.navigate(["/"]);
    } else {
      this.getManager();
    }
  }

  setOption(option: number){
    this.service.option = option;
  }

  getManager(){
    let managerName = this.route.snapshot.paramMap.get("user");
    console.log(managerName);
    this.userService.getManager(new Manager(String(managerName) , ManagerStatus.VIGENTE))
      .subscribe((managerInfo: Manager) =>{

        this.manager = managerInfo;
        console.log(this.manager);
      }, (error: any) => {
        console.log("USUARIO ADMINISTRADOR NO ENCONTRADO");
        this.router.navigate(["/"]);
      });
  }

  close(){
    localStorage.removeItem("manager");
    this.router.navigate(["/"]);
  }


}
