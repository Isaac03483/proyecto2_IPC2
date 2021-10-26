package com.ameri.servlets.user.editor.profile;

import com.ameri.converter.user.editor.EditorTagConverter;
import com.ameri.dao.user.editor.DAOEditorTagImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.editor.EditorTag;
import com.ameri.objects.classes.user.editor.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-editor-tag")
public class AddEditorTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editorName = req.getParameter("editorName");
        Profile profile = new Profile(editorName);

        try {
            List<EditorTag> list = new DAOEditorTagImpl().list(profile);
            resp.getWriter().write(new EditorTagConverter(EditorTag.class).toJson(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String body = new Reader(reader).getInformation();
        EditorTagConverter converter = new EditorTagConverter(EditorTag.class);

        EditorTag tag = converter.fromJson(body);

        try {
            new DAOEditorTagImpl().insert(tag);
            resp.getWriter().write(converter.toJson(tag));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
