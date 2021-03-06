package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineConverter;
import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.enums.magazine.MagazineStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update-magazine")
@MultipartConfig(location = "/tmp")
public class UpdateMagazine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Magazine> list = new DAOMagazineImpl().listMagazinesWhereStatus(MagazineStatus.ACEPTADA.getStatus());
            resp.getWriter().write(new MagazineConverter(Magazine.class).toJson(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();
        MagazineConverter converter = new MagazineConverter(Magazine.class);

        System.out.println(body);
        Magazine magazine = converter.fromJson(body);
        System.out.println(magazine);
        try {
            DAOMagazineImpl daoMagazine = new DAOMagazineImpl();
            daoMagazine.update(magazine);
            Magazine sameMagazine = daoMagazine.getMagazine(magazine);
            resp.getWriter().write(converter.toJson(sameMagazine));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
