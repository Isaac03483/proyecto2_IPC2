import {MagazineBeans} from "./MagazineBeans";
import {Subscription} from "../../usuario/editor/Subscription";
import {Comment} from "../../usuario/editor/Comment";
import {EditorAccount} from "../../usuario/editor/EditorAccount";

export class EditorBeans{

  total: number;
  magazineBeansList: Array<MagazineBeans> ;
  subscriptionList: Array<Subscription>;
  commentList: Array<Comment>;
  editorAccountList: Array<EditorAccount>;


  constructor(total: number, magazineBeansList: Array<MagazineBeans>, subscriptionList: Array<Subscription>, commentList: Array<Comment>, editorAccountList: Array<EditorAccount>) {
    this.total = total;
    this.magazineBeansList = magazineBeansList;
    this.subscriptionList = subscriptionList;
    this.commentList = commentList;
    this.editorAccountList = editorAccountList;
  }
}
