import {SubscriptionLike} from "../../../enums/user/editor/SubscriptionLike";

export class UpdateSubscriptionLike{

  subscriptionRecord: number;
  subscriptionLike: SubscriptionLike;


  constructor(subscriptionRecord: number, subscriptionLike: SubscriptionLike) {
    this.subscriptionRecord = subscriptionRecord;
    this.subscriptionLike = subscriptionLike;
  }
}
