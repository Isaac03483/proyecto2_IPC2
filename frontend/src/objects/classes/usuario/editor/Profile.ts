import { Byte } from "@angular/compiler/src/util";

export class Profile{

    editorName: string;
    image: string;
    hobby: string;
    description: string;
    likes: string;

    constructor(editorName: string, image: string, hobby: string, description: string, likes: string){

        this.editorName=editorName;
        this.image=image;
        this.hobby=hobby;
        this.description=description;
        this.likes=likes;
    }

}
