package com.ameri.servlets.user.editor.subscription;

import com.ameri.converter.user.editor.CommentConverter;
import com.ameri.dao.user.editor.DAOCommentImpl;
import com.ameri.objects.classes.operation.Reader;
import com.ameri.objects.classes.user.editor.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-subscriber-comment")
public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("magazineRecord"));
        int magazineRecord = Integer.parseInt(req.getParameter("magazineRecord"));
        try {
            List<Comment> comments =new DAOCommentImpl().listMagazineComments(magazineRecord);
            resp.getWriter().write(new CommentConverter(Comment.class).toJson(comments));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        System.out.println(body);
        CommentConverter converter = new CommentConverter(Comment.class);

        Comment comment = converter.fromJson(body);

        try {
            new DAOCommentImpl().insert(comment);

            resp.getWriter().write(converter.toJson(comment));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
