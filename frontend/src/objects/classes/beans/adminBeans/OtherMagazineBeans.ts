import {EditorAccount} from "../../usuario/editor/EditorAccount";

export class OtherMagazineBeans{

  magazineRecord: number;
  dayCost: number;
  totalDayCost: number;
  gananciaTotal: number;
  subscriptionList: Array<EditorAccount>;
  
  constructor(magazineRecord: number, dayCost: number, totalDayCost: number, gananciaTotal: number, subscriptionList: Array<EditorAccount>) {
    this.magazineRecord = magazineRecord;
    this.dayCost = dayCost;
    this.totalDayCost = totalDayCost;
    this.gananciaTotal = gananciaTotal;
    this.subscriptionList = subscriptionList;
  }
}
