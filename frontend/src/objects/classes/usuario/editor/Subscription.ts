import { SubscriptionLike } from '../../../enums/user/editor/SubscriptionLike';
import { SubscriptionStatus } from '../../../enums/user/editor/SubscriptionStatus';
import { PaymentEnum } from '../../../enums/user/editor/PaymentEnum';
export class Subscription{

    subscriptionRecord:number;
    subscriberName:string;
    magazineRecord:number;
    magazineName: string
    totalPay: number;
    paymentInterval: PaymentEnum;
    recordDate: string;
    endDate: string;
    subscriptionStatus: SubscriptionStatus;
    subscriptionLike: SubscriptionLike;

    constructor(subscriptionRecord: number, subscriberName: string, magazineRecord: number, magazineName: string, totalPay: number, paymentInterval:
        PaymentEnum,recordDate: string, endDate: string, subscriptionStatus: SubscriptionStatus, subscriptionLike: SubscriptionLike){

        this.subscriptionRecord = subscriptionRecord;
        this.subscriberName = subscriberName;
        this.magazineRecord = magazineRecord;
        this.magazineName = magazineName;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = recordDate;
        this.endDate = endDate;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionLike = subscriptionLike;
    }

}
