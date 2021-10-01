package com.ameri.object.user.editor;

public class EditorTag {

    private String editorName;
    private String tagName;

    public EditorTag(String editorName, String tagName) {
        this.editorName = editorName;
        this.tagName = tagName;
    }

    public String getEditorName() {return editorName;}

    public String getTagName() {return tagName;}

    public void setEditorName(String editorName) {this.editorName = editorName;}

    public void setTagName(String tagName) {this.tagName = tagName;}

}
