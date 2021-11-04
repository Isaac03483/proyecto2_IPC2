package com.ameri.servlets.ads;

import com.ameri.converter.ads.AdConverter;
import com.ameri.dao.ad.DAOAdImpl;
import com.ameri.objects.classes.ads.Ad;
import com.ameri.operation.provisional.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-new-ad")
public class AddNewAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String body = new Reader(reader).getInformation();

        AdConverter converter = new AdConverter(Ad.class);

        Ad newAd = converter.fromJson(body);

        try {
            new DAOAdImpl().insert(newAd);
            resp.getWriter().write(converter.toJson(newAd));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
