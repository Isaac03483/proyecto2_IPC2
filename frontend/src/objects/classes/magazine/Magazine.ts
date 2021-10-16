import { Category } from './../usuario/administrador/Category';
import { Byte } from '@angular/compiler/src/util';
import {File} from "@angular/compiler-cli/src/ngtsc/file_system/testing/src/mock_file_system";
import {MagazineStatus} from "../../enums/magazine/MagazineStatus";
import {MagazineLike} from "../../enums/magazine/MagazineLike";
import {MagazineComment} from "../../enums/magazine/MagazineComment";
import {MagazineSubscription} from "../../enums/magazine/MagazineSubscription";
export class Magazine{

    magazineRecord: number;
    editorName:string;
    magazineName:string;
    file: File | null;
    publicationDate: string;
    description: string;
    category: Category;
    subscriptionCost: number;
    acceptDate!: string;
    status!: MagazineStatus;
    dayCost!: number;
    updateDate!: string;
    like!: MagazineLike;
    comment!: MagazineComment;
    subscription!: MagazineSubscription;


  constructor(magazineRecord: number, editorName: string, magazineName: string, file: File | null, publicationDate: string, description: string, category: Category, subscriptionCost: number, acceptDate: string, status: MagazineStatus, dayCost: number, updateDate: string, like: MagazineLike, comment: MagazineComment, subscription: MagazineSubscription) {
    this.magazineRecord = magazineRecord;
    this.editorName = editorName;
    this.magazineName = magazineName;
    this.file = file;
    this.publicationDate = publicationDate;
    this.description = description;
    this.category = category;
    this.subscriptionCost = subscriptionCost;
    this.acceptDate = acceptDate;
    this.status = status;
    this.dayCost = dayCost;
    this.updateDate = updateDate;
    this.like = like;
    this.comment = comment;
    this.subscription = subscription;
  }
}
