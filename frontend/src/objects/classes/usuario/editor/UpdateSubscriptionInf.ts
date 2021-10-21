import {PaymentEnum} from "../../../enums/user/editor/PaymentEnum";
import {SubscriptionStatus} from "../../../enums/user/editor/SubscriptionStatus";

export class UpdateSubscriptionInf{

  subscriptionRecord: number;
  totalPay: number;
  paymentInterval: PaymentEnum;
  endDate: string;
  subscriptionStatus: SubscriptionStatus;


  constructor(subscriptionRecord: number, totalPay: number, paymentInterval: PaymentEnum, endDate: string, subscriptionStatus: SubscriptionStatus) {
    this.subscriptionRecord = subscriptionRecord;
    this.totalPay = totalPay;
    this.paymentInterval = paymentInterval;
    this.endDate = endDate;
    this.subscriptionStatus = subscriptionStatus;
  }
}
