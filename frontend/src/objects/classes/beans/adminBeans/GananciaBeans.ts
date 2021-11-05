import {OtherMagazineBeans} from "./OtherMagazineBeans";

export class GananciaBeans{
  total: number;
  otherMagazineBeans: Array<OtherMagazineBeans>

  constructor(total: number, otherMagazineBeans: Array<OtherMagazineBeans>) {
    this.total = total;
    this.otherMagazineBeans = otherMagazineBeans;
  }
}
