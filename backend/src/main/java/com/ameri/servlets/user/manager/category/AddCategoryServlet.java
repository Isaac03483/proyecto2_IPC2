package com.ameri.servlets.user.manager.category;

import com.ameri.converter.user.manager.CategoryConverter;
import com.ameri.dao.user.manager.DAOCategoryImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.manager.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-category")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String data = new CategoryConverter(Category.class).toJson(new DAOCategoryImpl().list());
            System.out.println(data);
            resp.getWriter().write(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        CategoryConverter converter = new CategoryConverter(Category.class);

        try {
            Category category = converter.fromJson(body);
            if(new DAOCategoryImpl().getCategory(category) == null){
                new DAOCategoryImpl().insert(category);
                resp.getWriter().write(converter.toJson(new DAOCategoryImpl().getCategory(category)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
