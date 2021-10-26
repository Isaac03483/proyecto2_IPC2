package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineConverter;
import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.editor.Profile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-magazine")
public class AddMagazine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editorName = req.getParameter("editorName");

        Profile profile = new Profile(editorName);

        try {
            List<Magazine> list = new DAOMagazineImpl().listEditorMagazines(profile);
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

        Magazine magazine = converter.fromJson(body);
        System.out.println(magazine);
        try {
            DAOMagazineImpl daoMagazine = new DAOMagazineImpl();

            if(daoMagazine.getMagazine(magazine) == null){
                System.out.println("entra");
                daoMagazine.insert(magazine);
                System.out.println("pasa el insert");
                Magazine sameMagazine = daoMagazine.getMagazine(magazine);
                System.out.println("este "+sameMagazine);
                resp.getWriter().write(new MagazineConverter(Magazine.class).toJson(sameMagazine));
            } else{
                System.out.println("no entra");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
