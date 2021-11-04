package com.ameri.servlets.ads;

import com.ameri.converter.ads.AdConverter;
import com.ameri.dao.ad.DAOAdImpl;
import com.ameri.objects.classes.ads.Ad;
import com.ameri.objects.enums.ad.AdStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/get-ads-info")
public class GetAdsInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new DAOAdImpl().desactivateAd();
            List<Ad> list = new DAOAdImpl().listAllAds();
            resp.getWriter().write(new AdConverter(Ad.class).toJson(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdStatus adStatus = AdStatus.value(req.getParameter("adStatus"));

        try {
            if(adStatus != null){
                List<Ad> list = new DAOAdImpl().listAdWhereStatus(adStatus);
                resp.getWriter().write(new AdConverter(Ad.class).toJson(list));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
