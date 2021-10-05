package com.ameri.objects.classes.user.editor;

public class EditorTag {

    private final String editorName;
    private final String tagName;

    public EditorTag(String editorName, String tagName) {
        this.editorName = editorName;
        this.tagName = tagName;
    }

    public String getEditorName() {return editorName;}

    public String getTagName() {return tagName;}
}
