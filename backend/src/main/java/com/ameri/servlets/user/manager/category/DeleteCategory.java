package com.ameri.servlets.user.manager.category;

import com.ameri.converter.user.manager.CategoryConverter;
import com.ameri.dao.user.manager.DAOCategoryImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.manager.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-category")
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        CategoryConverter converter = new CategoryConverter(Category.class);

        Category deleteCategory = converter.fromJson(body);

        try {
            new DAOCategoryImpl().delete(deleteCategory);
            resp.getWriter().write(converter.toJson(deleteCategory));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
