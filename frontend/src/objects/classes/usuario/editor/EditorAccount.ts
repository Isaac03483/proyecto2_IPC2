export class EditorAccount{

    accountRecord:number;
    editorName: string;
    subscriberName: string;
    magazineRecord:number;
    totalPay: number;
    descuento: number;
    ganancia: number;
    payDate: Date;

    constructor(accountRecord:number, editorName:string, subscriberName: string, magazineRecord: number, totalPay:number, descuento:number, ganancia: number, payDate:Date){
        this.accountRecord = accountRecord;
        this.editorName = editorName;
        this.subscriberName = subscriberName;
        this.magazineRecord = magazineRecord;
        this.totalPay = totalPay;
        this.descuento = descuento;
        this.ganancia = ganancia;
        this.payDate = payDate;
    }
}
