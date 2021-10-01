package com.ameri.object.user.editor;

import java.util.List;

public final class Editor {

    private String editorName;
    private String editorPassword;
    private Byte image;
    private String hobbies;
    private String description;
    private String likes;

    public Editor(String editorName, String editorPassword, Byte image, String hobbies, String description, String likes) {
        this.editorName = editorName;
        this.editorPassword = editorPassword;
        this.image = image;
        this.hobbies = hobbies;
        this.description = description;
        this.likes = likes;
    }

    public String getEditorName() {return editorName;}

    public String getEditorPassword() {return editorPassword;}

    public Byte getImage() {return image;}

    public String getHobbies() {return hobbies;}

    public String getDescription() {return description;}

    public String getLikes() {return likes;}

    public void setEditorName(String editorName) {this.editorName = editorName;}

    public void setEditorPassword(String editorPassword) {this.editorPassword = editorPassword;}

    public void setImage(Byte image) {this.image = image;}

    public void setHobbies(String hobbies) {this.hobbies = hobbies;}

    public void setDescription(String description) {this.description = description;}

    public void setLikes(String likes) {this.likes = likes;}
}
