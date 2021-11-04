package com.ameri.servlets.user.manager.tag;

import com.ameri.converter.user.manager.TagConverter;
import com.ameri.dao.user.manager.DAOTagImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.manager.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-tag")
public class UpdateTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();
        System.out.println(body);
        TagConverter converter = new TagConverter(Tag.class);
        Tag updateTag = converter.fromJson(body);
        try {
            new DAOTagImpl().update(updateTag);
            resp.getWriter().write(converter.toJson(updateTag));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
