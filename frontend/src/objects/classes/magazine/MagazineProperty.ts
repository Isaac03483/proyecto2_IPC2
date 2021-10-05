import { MagazineSubscription } from './../../enums/magazine/MagazineSubscription';
import { MagazineComment } from './../../enums/magazine/MagazineComment';
import { MagazineLike } from './../../enums/magazine/MagazineLike';
import { MagazineStatus } from './../../enums/magazine/MagazineStatus';
import { ThrowStmt } from '@angular/compiler';
export class MagazineProperty{

    private magazineRecord!: number;
    private acceptDate!: Date;
    private status!: MagazineStatus;
    private dayCost!: number;
    private updateDate!: Date;
    private like!: MagazineLike;
    private comment!: MagazineComment;
    private subscription!: MagazineSubscription;

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

    get getRecord(){return this.magazineRecord;}

    get getAcceptDate(){return this.acceptDate;}

    get getStatus(){return this.status;}

    get getDayCost(){return this.dayCost;}

    get getUpdateDate(){return this.updateDate;}

    get getLike(){return this.like;}

    get getComment(){return this.comment;}

    get getSubscription(){return this.subscription;}
}