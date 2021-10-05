import { UserType } from 'src/objects/enums/user/UserType';
import { UsrService } from 'src/app/services/user/usr.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/objects/classes/usuario/User';

@Component({
  selector: 'app-signin-control',
  templateUrl: './signin-control.component.html',
  styleUrls: ['./signin-control.component.css']
})

export class SigninControlComponent implements OnInit {

  formSign!: FormGroup;
  constructor(private formBuilder: FormBuilder, private service: UsrService) { }

  ngOnInit(): void {
    this.formSign = this.formBuilder.group({
      userName: [null, Validators.required],
      passUser: [null, Validators.required],
    });
  }

  getUser(){
    if(this.formSign.valid){
      this.service.getUser(new User(this.formSign.value.userName, "", UserType.ADMIN))
      .subscribe((created: User) => {
        console.log(created);
        if(created.getUserType == UserType.ADMIN){

        } else {
          alert("El usuario ingresado no es un administrador.");
        }
      }, (error: any) => {

        alert("Parece que hubo un error al guardar la información. Pruebe con otro usuario.");
      });
    }
  }
}
