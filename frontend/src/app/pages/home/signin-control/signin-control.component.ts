import { Router } from '@angular/router';
import { UserType } from '../../../../objects/enums/user/UserType';
import { UsrService } from 'src/app/services/user/usr.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/objects/classes/usuario/User';
import { Manager } from 'src/objects/classes/usuario/administrador/Manager';
import { ManagerStatus } from 'src/objects/enums/user/manager/ManagerStatus';

@Component({
  selector: 'app-signin-control',
  templateUrl: './signin-control.component.html',
  styleUrls: ['./signin-control.component.css']
})

export class SigninControlComponent implements OnInit {

  formSign!: FormGroup;
  constructor(private formBuilder: FormBuilder, private service: UsrService, private router: Router) { }

  ngOnInit(): void {
    this.formSign = this.formBuilder.group({
      userName: [null, Validators.required],
      passUser: [null, Validators.required],
    });
  }

  getUser(){
    if(this.formSign.valid){
      this.service.getUser(new User(this.formSign.value.userName, this.formSign.value.passUser, UserType.ADMIN))
      .subscribe((created: User) => {
        if(created != null){
          if(created.userType == UserType.EDITOR){
            localStorage.setItem("editor", JSON.stringify(created.userName));

            this.router.navigate(['/editor-home', created.userName]);
          } else if(created.userType == UserType.ADMIN){

            this.service.getManager(new Manager(created.userName, ManagerStatus.VIGENTE))
            .subscribe((manager: Manager) =>{
              if(manager != null){

                  localStorage.setItem("manager", JSON.stringify(manager.managerName));
                  this.router.navigate(['/admin-home', manager.managerName]);

              } else {
                alert("ESTE ADMINISTRADOR HA SIDO CANCELADO.");
              }
            }, (error: any) => {

              alert("LA CONTRASEÑA ES INCORRECTA.");
            });
          }
        } else {
          alert("LA CONTRASEÑA ES INCORRECTA.");
        }
      }, (error: any) => {

        alert("USUARIO NO ENCONTRADO.");
      });
    }
  }
}
