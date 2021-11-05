import {Comment} from "../../usuario/editor/Comment";
import {Subscription} from "../../usuario/editor/Subscription";
import {EditorAccount} from "../../usuario/editor/EditorAccount";

export class MagazineBeans{

  magazineRecord: number;
  magazineLikes: number;
  totalCount: number;
  magazineName: string;
  commentList: Array<Comment>;
  subscriptionList: Array<Subscription>;
  editorAccountList: Array<EditorAccount>;


  constructor(magazineRecord: number, magazineLikes: number, totalCount: number, magazineName: string, commentList: Array<Comment>, subscriptionList: Array<Subscription>, editorAccountList: Array<EditorAccount>) {
    this.magazineRecord = magazineRecord;
    this.magazineLikes = magazineLikes;
    this.totalCount = totalCount;
    this.magazineName = magazineName;
    this.commentList = commentList;
    this.subscriptionList = subscriptionList;
    this.editorAccountList = editorAccountList;
  }
}
