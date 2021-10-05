import { AdStatus } from './../../enums/ad/AdStatus';
import { AdType } from "./AdType";

export class Ad{

    private adRecord!: number;
    private adType!: AdType;
    private adName!: string;
    private clientName!: string;
    private adText!: string;
    private adContent!: string;
    private views!: number;
    private totalCost!: number;
    private adStatus!: AdStatus;
    private url!: string;
    private startDate!: Date;
    private endDate!: Date;

    constructor(adRecord: number, adType: AdType, adName: string, clientName: string, adText: string, adContent: string, 
        views: number, totalCost: number, adStatus: AdStatus, url: string, startDate: Date, endDate: Date){

            this.adRecord = adRecord;
            this.adType = adType;
            this.adName = adName;
            this.clientName = clientName;
            this.adText = adText;
            this.adContent = adContent;
            this.views = views;
            this.totalCost = totalCost;
            this.adStatus = adStatus;
            this.url = url;
            this.startDate = startDate;
            this.endDate = endDate;
    }

    get getRecord(){return this.adRecord;}

    get getType(){return this.adType;}

    get getName(){return this.adName;}

    get getClient(){return this.clientName;}

    get getText(){return this.adText;}

    get getContent(){return this.adContent;}

    get getViews(){return this.views;}

    get getCost(){return this.totalCost;}

    get getStatus(){return this.adStatus;}

    get getUrl(){return this.url;}

    get getStartDate(){return this.startDate;}

    get getEndDate(){return this.endDate;}
}