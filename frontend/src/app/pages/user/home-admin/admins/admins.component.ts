import {Component, OnInit} from '@angular/core';
import {HomeAdminService} from "../../../../services/user/home-admin.service";
import {Manager} from "../../../../../objects/classes/usuario/administrador/Manager";
import {ManagerStatus} from "../../../../../objects/enums/user/manager/ManagerStatus";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsrService} from "../../../../services/user/usr.service";
import {User} from "../../../../../objects/classes/usuario/User";
import {UserType} from "../../../../../objects/enums/user/UserType";
import {UserUpdate} from "../../../../../objects/classes/usuario/UserUpdate";

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  optionAdmin: number = 1;
  optionUpdate: number = 0;
  managerName: string ="";
  allManagers: Array<Manager> = [];
  deleteManagerForm!: FormGroup;
  addManagerForm!: FormGroup;
  updatePasswordForm!: FormGroup;
  updateNameForm!: FormGroup;

  constructor(private adminService: HomeAdminService, private formBuilder: FormBuilder, private userService: UsrService) { }

  ngOnInit(): void {
    this.upload();
    this.addManagerForm = this.formBuilder.group({
      managerName: [null, Validators.required],
      managerPassword: [null, Validators.required],
      verificationPass: [null, Validators.required]
    });

    this.updateNameForm = this.formBuilder.group({
      oldPassword: [null, Validators.required],
      updateName: [null, Validators.required]
    });

    this.updatePasswordForm = this.formBuilder.group({
      oldPassword: [null, Validators.required],
      newPassword: [null, Validators.required],
      verificationNew: [null, Validators.required]
    });

    this.deleteManagerForm = this.formBuilder.group({
      managerPassword: [null, Validators.required]
    });
  }

  upload(){
    this.adminService.getManagers()
      .subscribe(created =>{
        if(created != null){
          this.allManagers = created;
        }
      });
  }

  setOption(option: number){
    this.optionAdmin = option;
  }

  selectInf(number: number, managerName: string, managerStatus: ManagerStatus) {
    this.setOption(number);
    this.managerName = managerName;
  }

  changeOption(number: number) {
    this.optionUpdate = number;
  }

  updateManager(){
    if(this.optionUpdate == 1){
      if(this.updatePasswordForm.value.newPassword === this.updatePasswordForm.value.verificationNew){
        this.userService.getUser(new User(this.managerName, this.updatePasswordForm.value.oldPassword, UserType.ADMIN))
          .subscribe((created: User) =>{
            if(created != null){
              this.userService.updateUser(new User(this.managerName, this.updatePasswordForm.value.newPassword, UserType.ADMIN))
                .subscribe((created: User | UserUpdate) =>{
                  if(created != null){
                    window.alert("CONTRASEÑA MODIFICADA CON ÉXITO.")
                    this.managerName="";
                    this.updatePasswordForm.reset({
                      oldPassword: [null],
                      newPassword: [null],
                      verificationNew: [null]
                    })
                  }
                });
            } else {
              window.alert("LA CONTRASEÑA ANTIGÜA NO COINCIDE CON LA CONTRASEÑA DEL USUARIO.");
            }
          });
      }
    } else if (this.optionUpdate == 2){

      this.userService.getUser(new User(this.managerName, this.updateNameForm.value.oldPassword, UserType.ADMIN))
        .subscribe((created: User) =>{
          if(created != null){
            console.log(this.updateNameForm.value.updateName);
            this.userService.updateUser(new UserUpdate(this.updateNameForm.value.updateName, this.managerName))
              .subscribe((user: User | UserUpdate) =>{
                if(user != null){
                  window.alert("NOMBRE DE USUARIO ACTUALIZADO CON ÉXITO.");
                  this.managerName="";
                  this.updateNameForm.reset({
                    oldPassword: [null],
                    updateName: [null]

                  })
                  this.upload();
                }
              }, (error: any) =>{
                alert("ERROR CAPA 8");
              });
          } else {
            window.alert("LA CONTRASEÑA ANTIGÜA NO COINCIDE CON LA CONTRASEÑA DEL USUARIO.");
          }
        });

    }
  }
  addManager(){
    if(this.addManagerForm.valid){
      console.log(this.addManagerForm.value.managerPassword);
      console.log(this.addManagerForm.value.verificationPass);
      if(this.addManagerForm.value.managerPassword === this.addManagerForm.value.verificationPass){
        this.userService.createUser(new User(this.addManagerForm.value.managerName, this.addManagerForm.value.managerPassword, UserType.ADMIN))
          .subscribe((created: User) => {
            if(created != null){
              this.adminService.addManager(new Manager(this.addManagerForm.value.managerName, ManagerStatus.VIGENTE))
                .subscribe((manager: Manager) =>{
                  if(manager != null){
                    window.alert("ADMINISTRADOR CREADO CON ÉXITO.");
                    this.upload();
                  }
                })
            } else{
              window.alert("NO SE HA PODIDO CREAR EL ADMINISTRADOR.");
            }
          }, (error: any) =>{
            window.alert("ESTE USUARIO YA EXISTE. PRUEBE CON OTRO NOMBRE.")
        });
      } else {
        window.alert("LA CONTRASEÑA Y SU VERIFICACIÓN DEBEN SER IGUALES");
      }
    }
  }

  deleteManager() {
    if(this.deleteManagerForm.valid){
      this.userService.getUser(new User(this.managerName, this.deleteManagerForm.value.managerPassword, UserType.ADMIN))
        .subscribe((created: User) =>{
          if(created != null){
            this.adminService.deleteManager(new Manager(this.managerName, ManagerStatus.CANCELADO))
              .subscribe((created: Manager) =>{
                if(created != null){
                  window.alert("ADMINISTRADOR CANCELADO CON ÉXITO.");
                  this.upload();
                }
              });
          } else {
            window.alert("LA CONTRASEÑA NO COINCIDE.");
          }
        });
    }

  }
}
