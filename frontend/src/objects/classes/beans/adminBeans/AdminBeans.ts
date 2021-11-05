import {Comment} from "../../usuario/editor/Comment";
import {Subscription} from "../../usuario/editor/Subscription";

export class AdminBeans{

  count: number;
  magazineRecord: number;
  commentList: Array<Comment>;
  subscriptionList: Array<Subscription>;


  constructor(count: number, magazineRecord: number, commentList: Array<Comment>, subscriptionList: Array<Subscription>) {
    this.count = count;
    this.magazineRecord = magazineRecord;
    this.commentList = commentList;
    this.subscriptionList = subscriptionList;
  }
}
