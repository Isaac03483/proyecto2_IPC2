export class UserUpdate{

  userName: string;
  oldUserName: string;


  constructor(userName: string, oldUserName: string) {
    this.userName = userName;
    this.oldUserName = oldUserName;
  }
}
