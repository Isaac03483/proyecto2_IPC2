package com.ameri.objects.beans.editorBeans;

import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Subscription;

import java.util.List;

public class EditorBeans {

    private double total;
    private List<MagazineBeans> magazineBeansList;
    private List<Subscription> subscriptionList;
    private List<Comment> commentList;
    private List<EditorAccount> editorAccountList;

    public EditorBeans(){}

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }

    public List<MagazineBeans> getMagazineBeansList() {
        return magazineBeansList;
    }

    public void setMagazineBeansList(List<MagazineBeans> magazineBeansList) {
        this.magazineBeansList = magazineBeansList;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<EditorAccount> getEditorAccountList() {
        return editorAccountList;
    }

    public void setEditorAccountList(List<EditorAccount> editorAccountList) {
        this.editorAccountList = editorAccountList;
    }
}
