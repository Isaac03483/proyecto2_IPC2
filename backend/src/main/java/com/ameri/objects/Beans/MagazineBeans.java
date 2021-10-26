package com.ameri.objects.Beans;

import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Subscription;
import java.util.List;

public class MagazineBeans{

    private int magazineRecord;
    private int magazineLikes;
    private String magazineName;
    private List<Comment> commentList;
    private List<Subscription> subscriptionList;
    private List<EditorAccount> editorAccountList;

    {
        magazineLikes = 0;
    }

    public MagazineBeans(int magazineRecord, String magazineName) {
        this.magazineRecord = magazineRecord;
        this.magazineName = magazineName;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public int getMagazineRecord() {
        return magazineRecord;
    }

    public void setMagazineRecord(int magazineRecord) {
        this.magazineRecord = magazineRecord;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public List<EditorAccount> getEditorAccountList() {
        return editorAccountList;
    }

    public void setEditorAccountList(List<EditorAccount> editorAccountList) {
        this.editorAccountList = editorAccountList;
    }

    public int getMagazineLikes() {
        return magazineLikes;
    }

    public void setMagazineLikes(int magazineLikes) {
        this.magazineLikes = magazineLikes;
    }
}
