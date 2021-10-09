import {BigInteger} from "@angular/compiler/src/i18n/big_integer";

export class Impuesto{

    registroImpuesto: number;
    percentage: number;
    updateDate: string;

    constructor(registroImpuesto: number, percentage: number,updateDate: string){

        this.registroImpuesto=registroImpuesto;
        this.percentage=percentage;
        this.updateDate=updateDate;
    }
}
