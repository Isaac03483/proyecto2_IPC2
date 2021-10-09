export class AdType{

    typeName!: string;
    dayCost!: number;

    constructor(typeName: string, dayCost: number){
        this.typeName = typeName;
        this.dayCost = dayCost;
    }

}