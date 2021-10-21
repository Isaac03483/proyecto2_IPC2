package com.ameri.objects.classes.user.editor;

import java.sql.Date;
import java.time.LocalDate;

public class Comment {

    private final int commentRecord;
    private final int magazineRecord;
    private final String subscriberName;
    private final String text;
    private final String commentDate;

    public Comment(int commentRecord, int magazineRecord, String subscriberName, String text, String commentDate) {
        this.commentRecord = commentRecord;
        this.magazineRecord = magazineRecord;
        this.subscriberName = subscriberName;
        this.text = text;
        this.commentDate = commentDate;
    }

    public int getCommentRecord() {return commentRecord;}

    public int getMagazineRecord() {return magazineRecord;}

    public String getSubscriberName() {return subscriberName;}

    public String getText() {return text;}

    public Date getCommentDate() {return Date.valueOf(commentDate);}
}
