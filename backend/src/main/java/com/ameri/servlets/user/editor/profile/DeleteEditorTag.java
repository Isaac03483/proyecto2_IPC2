package com.ameri.servlets.user.editor.profile;

import com.ameri.converter.user.editor.EditorTagConverter;
import com.ameri.dao.user.editor.DAOEditorTagImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.editor.EditorTag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-editor-tag")
public class DeleteEditorTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String body = new Reader(reader).getInformation();
        EditorTagConverter converter = new EditorTagConverter(EditorTag.class);

        EditorTag tag = converter.fromJson(body);

        try {
            new DAOEditorTagImpl().delete(tag);
            resp.getWriter().write(converter.toJson(tag));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
