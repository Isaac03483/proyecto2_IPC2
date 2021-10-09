import { AdStatus } from './../../enums/ad/AdStatus';
import { AdType } from "./AdType";

export class Ad{

    adRecord!: number;
    adType!: AdType;
    adName!: string;
    clientName!: string;
    adText!: string;
    adContent!: string;
    views!: number;
    totalCost!: number;
    adStatus!: AdStatus;
    url!: string;
    startDate!: Date;
    endDate!: Date;

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
}