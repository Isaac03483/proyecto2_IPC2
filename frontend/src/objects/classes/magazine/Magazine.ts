import { Category } from './../usuario/administrador/Category';
import { Byte } from '@angular/compiler/src/util';
export class Magazine{

    magazineRecord: number;
    editorName:string;
    magazineName:string;
    file: Blob;
    publicationDate: Date;
    description: string;
    category: Category;
    subscriptionCost: number;

    constructor(magazineRecord: number, editorName: string, magazineName: string, file: Blob, publicationDate: Date, description: string, category: Category, subscriptionCost: number){

        this.magazineRecord = magazineRecord;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = file;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.subscriptionCost = subscriptionCost;
    }

}
