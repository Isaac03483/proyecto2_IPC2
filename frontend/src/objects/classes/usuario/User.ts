import { HomeUserComponent } from './../../../app/pages/user/home-user/home-user.component';
import { UserType } from './../../enums/user/UserType';

export class User{

    userName!: String;
    userPassword!: String;
    userType!: UserType;

    constructor(userName: String, userPassword: String, userType: UserType){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    get getUserName(){return this.userName;}

    get getPassword(){return this.userPassword;}

    get getUserType(){return this.userType;}

    set setUserName(userName: String){this.userName = userName;}

    set setPassword(userPassword: String){this.userPassword = userPassword;}

    set setUserType(usetType: UserType){this.userType = usetType;}
}