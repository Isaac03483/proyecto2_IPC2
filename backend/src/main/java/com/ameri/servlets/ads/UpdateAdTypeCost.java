package com.ameri.servlets.ads;

import com.ameri.converter.ads.AdsTypeConverter;
import com.ameri.dao.ad.DAOAdTypeImpl;
import com.ameri.objects.classes.ads.AdType;
import com.ameri.operation.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update-ad-type-cost")
public class UpdateAdTypeCost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<AdType> list = new DAOAdTypeImpl().list();
            resp.getWriter().write(new AdsTypeConverter(AdType.class).toJson(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        AdsTypeConverter converter = new AdsTypeConverter(AdType.class);

        AdType adType = converter.fromJson(body);

        try {
            new DAOAdTypeImpl().update(adType);
            resp.getWriter().write(converter.toJson(adType));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
