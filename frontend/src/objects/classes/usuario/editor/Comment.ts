export class Comment{

    commentRecord: number;
    magazineRecord: number;
    subscriberName: string;
    text: string;
    commentDate: string;

    constructor(commentRecord:number, magazineRecord: number, subscriberName: string, text: string, commentDate: string){
        this.commentRecord=commentRecord;
        this.magazineRecord = magazineRecord;
        this.subscriberName=subscriberName;
        this.text=text;
        this.commentDate=commentDate;
    }
}
