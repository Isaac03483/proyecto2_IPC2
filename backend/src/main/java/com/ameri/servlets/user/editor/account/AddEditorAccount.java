package com.ameri.servlets.user.editor.account;

import com.ameri.converter.user.editor.EditorAccountConverter;
import com.ameri.dao.user.editor.DAOEditorAccountImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.editor.EditorAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-editor-account")
public class AddEditorAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        EditorAccountConverter converter = new EditorAccountConverter(EditorAccount.class);
        EditorAccount account = converter.fromJson(body);

        try {
            new DAOEditorAccountImpl().insert(account);
            resp.getWriter().write(converter.toJson(account));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
