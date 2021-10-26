package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.Beans.AdminBeans;
import com.ameri.objects.classes.user.editor.Comment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DAOComment {


    void insert(Comment comment) throws SQLException;

    void update(Comment comment) throws  SQLException;

    void delete(Comment comment) throws SQLException;

    List<Comment> listAllComments() throws SQLException;

    List<Comment> listMagazineComments(int magazineRecord) throws SQLException;

    List<Comment> listMagazineCommentsBetween(int magazineRecord, LocalDate startDate, LocalDate endDate) throws SQLException;

    List<AdminBeans> listTopComments() throws SQLException;

    List<AdminBeans> listTopCommentsBetween(LocalDate start, LocalDate end) throws SQLException;

}
