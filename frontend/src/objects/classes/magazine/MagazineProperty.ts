import { MagazineSubscription } from './../../enums/magazine/MagazineSubscription';
import { MagazineComment } from './../../enums/magazine/MagazineComment';
import { MagazineLike } from './../../enums/magazine/MagazineLike';
import { MagazineStatus } from './../../enums/magazine/MagazineStatus';
export class MagazineProperty{

    magazineRecord!: number;
    acceptDate!: Date;
    status!: MagazineStatus;
    dayCost!: number;
    updateDate!: Date;
    like!: MagazineLike;
    comment!: MagazineComment;
    subscription!: MagazineSubscription;

    constructor(magazineRecord:number, acceptDate:Date, status: MagazineStatus,dayCost: number, 
        updateDate: Date, like: MagazineLike, comment: MagazineComment, subscription: MagazineSubscription){

            this.magazineRecord =magazineRecord;
            this.acceptDate = acceptDate;
            this.status=status;
            this.dayCost=dayCost;
            this.updateDate=updateDate;
            this.like=like;
            this.comment=comment;
            this.subscription=subscription;
    }

}