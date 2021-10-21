package com.ameri.objects.classes.user.editor;

public class Profile {

    private final String editorName;
    private final String image;
    private final String hobby;
    private final String description;
    private final String likes;

    public Profile(String editorName, String image, String hobby, String description, String likes) {
        this.editorName = editorName;
        this.image = image;
        this.hobby = hobby;
        this.description = description;
        this.likes = likes;
    }

    public Profile(String editorName, String image){
        this.editorName = editorName;
        this.image = image;
        this.hobby = "";
        this.description = "";
        this.likes = "";
    }

    public Profile(String editorName){
        this.editorName = editorName;
        this.image = null;
        this.hobby = "";
        this.description = "";
        this.likes = "";
    }

    public String getEditorName() {return editorName;}

    public String getImage() {return image;}

    public String getHobby() {return hobby;}

    public String getDescription() {return description;}

    public String getLikes() {return likes;}

}
