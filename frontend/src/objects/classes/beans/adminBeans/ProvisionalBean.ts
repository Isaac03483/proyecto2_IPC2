import {AdminBeans} from "./AdminBeans";
import {GananciaBeans} from "./GananciaBeans";

export class ProvisionalBean{

  adminBeansList: Array<AdminBeans>;
  gananciaBeans: Array<GananciaBeans>;


  constructor(adminBeansList: Array<AdminBeans>, gananciaBeans: Array<GananciaBeans>) {
    this.adminBeansList = adminBeansList;
    this.gananciaBeans = gananciaBeans;
  }
}
