import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsrService } from 'src/app/services/user/usr.service';
import { User } from 'src/objects/classes/usuario/User';
import { UserType } from 'src/objects/enums/user/UserType';

@Component({
  selector: 'app-login-control',
  templateUrl: './login-control.component.html',
  styleUrls: ['./login-control.component.css']
})
export class LoginControlComponent implements OnInit {

  formLog!: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UsrService, private router: Router) { }

  ngOnInit(): void {

    this.formLog = this.formBuilder.group({
      userName: [null, Validators.required],
      passUser: [null, Validators.required],
      verificacionPass: [null, Validators.required]
    });
  }

  saveUser() {
    if (this.formLog.valid) {
      //console.log(this.formSign.value);

      if (this.formLog.value.passUser === this.formLog.value.verificacionPass) {
        this.userService.createUser(new User(this.formLog.value.userName, this.formLog.value.passUser, UserType.EDITOR))
          .subscribe((created: User) => {
            console.log(created);
            if(created != null){
              this.router.navigate(['/editor-home', this.formLog.value.userName]);
            } else{
              alert("Error. este usuario ya existe en la base de datos.");
            }
          }, (error: any) => {

            alert("Parece que hubo un error al guardar la información. Pruebe con otro usuario.");
          });
      } else {
        alert("La contraseña y la verificación deben ser iguales.");
        this.formLog.reset({
          userName: this.formLog.value.userName,
          passUser: "",
          verificacionPass: ""
        });
      }
    }
  }
}
