package com.ameri.objects.beans.adminBeans;

import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.Subscription;

import java.util.List;

public class AdminBeans {

    private int count;
    private int magazineRecord;
    private List<Comment> commentList;
    private List<Subscription> subscriptionList;

    public AdminBeans(int count, int magazineRecord) {
        this.count = count;
        this.magazineRecord = magazineRecord;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMagazineRecord() {
        return magazineRecord;
    }

    public void setMagazineRecord(int magazineRecord) {
        this.magazineRecord = magazineRecord;
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
}
