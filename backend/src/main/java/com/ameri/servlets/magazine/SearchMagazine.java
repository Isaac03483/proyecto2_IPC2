package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineConverter;
import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.manager.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
 import java.util.List;

@WebServlet("/search-magazine")
public class SearchMagazine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String magazineName = "%"+req.getParameter("magazineName")+"%";
        String editorName = req.getParameter("editorName");
        String categoryName = req.getParameter("categoryName");
        List<Magazine> list;
        try {
            if(!categoryName.equals("")){
                Category category = new Category(categoryName);
                Magazine searchMagazine = new Magazine(magazineName, category, editorName);

                list = new DAOMagazineImpl().listMagazinesWhereCategory(searchMagazine);
            } else{
                Magazine searchMagazine = new Magazine(magazineName, editorName);
                list = new DAOMagazineImpl().listMagazinesWhereName(searchMagazine);
            }
            resp.getWriter().write(new MagazineConverter(Magazine.class).toJson(list));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
