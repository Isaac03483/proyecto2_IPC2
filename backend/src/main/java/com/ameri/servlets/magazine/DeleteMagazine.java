package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineConverter;
import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.operation.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-magazine")
public class DeleteMagazine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        MagazineConverter converter = new MagazineConverter(Magazine.class);

        Magazine deleteMagazine = converter.fromJson(body);

        try {
            new DAOMagazineImpl().delete(deleteMagazine);
            resp.getWriter().write(converter.toJson(deleteMagazine));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
