package com.ameri.object.user.editor;

import java.time.LocalDate;

public class Comment {

    private final Integer commentRecord;
    private final Integer magazineRecord;
    private final String subscriberName;
    private final String text;
    private final LocalDate commentDate;

    public Comment(Integer commentRecord, Integer magazineRecord, String subscriberName, String text, LocalDate commentDate) {
        this.commentRecord = commentRecord;
        this.magazineRecord = magazineRecord;
        this.subscriberName = subscriberName;
        this.text = text;
        this.commentDate = commentDate;
    }

    public Integer getCommentRecord() {return commentRecord;}

    public Integer getMagazineRecord() {return magazineRecord;}

    public String getSubscriberName() {return subscriberName;}

    public String getText() {return text;}

    public LocalDate getCommentDate() {return commentDate;}
}
