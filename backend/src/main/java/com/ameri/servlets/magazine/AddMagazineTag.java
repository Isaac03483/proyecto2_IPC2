package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineTagConverter;
import com.ameri.objects.classes.magazine.MagazineTag;
import com.ameri.objects.classes.operation.Reader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-magazine-tag")
public class AddMagazineTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String body = new Reader(reader).getInformation();
        MagazineTagConverter converter = new MagazineTagConverter(MagazineTag.class);
        MagazineTag tag = converter.fromJson(body);

    }
}
