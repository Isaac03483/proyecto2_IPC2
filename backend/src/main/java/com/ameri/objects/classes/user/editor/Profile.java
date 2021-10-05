package com.ameri.objects.classes.user.editor;

import java.util.List;

public class Profile {

    private final String editorName;
    private final Byte image;
    private final String hobby;
    private final String description;
    private final String likes;
    private final List<EditorTag> editorTagList;

    public Profile(String editorName, Byte image, String hobby, String description, String likes, List<EditorTag> editorTagList) {
        this.editorName = editorName;
        this.image = image;
        this.hobby = hobby;
        this.description = description;
        this.likes = likes;
        this.editorTagList = editorTagList;
    }

    public Profile(String editorName){
        this.editorName = editorName;
        this.image = null;
        this.hobby = "";
        this.description = "";
        this.likes = "";
        this.editorTagList = null;
    }

    public String getEditorName() {return editorName;}

    public Byte getImage() {return image;}

    public String getHobby() {return hobby;}

    public String getDescription() {return description;}

    public String getLikes() {return likes;}

    public List<EditorTag> getEditorTagList(){return this.editorTagList;}
}
