import { ManagerStatus } from './../../../enums/user/manager/ManagerStatus';
export class Manager{

    managerName: string;
    managerStatus: ManagerStatus;

    constructor(managerName: string, managerStatus: ManagerStatus){
        this.managerName =managerName;
        this.managerStatus = managerStatus
    }
}