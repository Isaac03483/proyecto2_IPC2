package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineTagConverter;
import com.ameri.dao.magazine.DAOMagazineTagImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineTag;
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

@WebServlet("/add-magazine-tag")
public class AddMagazineTag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int magazineRecord = Integer.parseInt(req.getParameter("magazineRecord"));

        Magazine magazine = new Magazine(magazineRecord);

        try {
            List<MagazineTag> tags= new DAOMagazineTagImpl().listMagazineTags(magazine);

            resp.getWriter().write(new MagazineTagConverter(MagazineTag.class).toJson(tags));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String body = new Reader(reader).getInformation();
        System.out.println(body);
        MagazineTagConverter converter = new MagazineTagConverter(MagazineTag.class);
        MagazineTag tag = converter.fromJson(body);

        try {
            new DAOMagazineTagImpl().insert(tag);
            resp.getWriter().write(converter.toJson(tag));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
