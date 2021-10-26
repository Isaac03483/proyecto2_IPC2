package com.ameri.servlets.magazine;

import com.ameri.dao.magazine.DAOMagazineTagImpl;
import com.ameri.objects.classes.magazine.Magazine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-magazine-tag")
public class DeleteMagazineTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int magazineRecord = Integer.parseInt(req.getParameter("magazineRecord"));
        System.out.println(magazineRecord);
        Magazine magazine = new Magazine(magazineRecord);
        try {
            new DAOMagazineTagImpl().delete(magazine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
