package com.ameri.servlets.user.manager.tag;

import com.ameri.converter.user.manager.TagConverter;
import com.ameri.dao.user.manager.DAOTagImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.manager.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-tag")
public class AddTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Tag> tags = new DAOTagImpl().list();
            TagConverter converter = new TagConverter(Tag.class);

            resp.getWriter().write(converter.toJson(tags));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        TagConverter converter = new TagConverter(Tag.class);
        Tag newTag = converter.fromJson(body);
        try {
            new DAOTagImpl().insert(newTag);
            resp.getWriter().write(converter.toJson(newTag));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
