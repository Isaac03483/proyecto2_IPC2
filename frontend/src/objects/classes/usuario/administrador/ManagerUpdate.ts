import {ManagerStatus} from "../../../enums/user/manager/ManagerStatus";

export class ManagerUpdate{

  userName: string;
  oldUserName: string;

  constructor(userName: string, oldUserName: string){
    this.userName =userName;
    this.oldUserName = oldUserName
  }
}
