import { UserType } from './../../enums/user/UserType';

export class User{

    userName: string;
    userPassword: string;
    userType: UserType;


  constructor(userName: string, userPassword: string, userType: UserType) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.userType = userType;
  }
}
