export class MagazineTag{

    magazineRecord!: number;
    tagName!: string;

    constructor(magazineRecord: number, tagName: string){
        this.magazineRecord = magazineRecord;
        this.tagName= tagName;
    }
}