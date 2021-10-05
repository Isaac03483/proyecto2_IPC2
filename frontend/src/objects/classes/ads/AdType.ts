export class AdType{

    private typeName!: string;
    private dayCost!: number;

    constructor(typeName: string, dayCost: number){
        this.typeName = typeName;
        this.dayCost = dayCost;
    }

    get getTypeName(){return this.typeName;}

    get getDayCost(){return this.dayCost;}

}