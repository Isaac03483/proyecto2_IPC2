export class MagazineTag{

    private magazineRecord!: number;
    private tagName!: string;

    constructor(magazineRecord: number, tagName: string){
        this.magazineRecord = magazineRecord;
        this.tagName= tagName;
    }

    get getRecord(){return this.magazineRecord;}

    get getName(){return this.tagName;}
}