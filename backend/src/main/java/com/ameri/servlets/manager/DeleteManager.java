package com.ameri.servlets.manager;

import com.ameri.converter.user.manager.ManagerConverter;
import com.ameri.dao.user.manager.DAOManagerImpl;
import com.ameri.objects.classes.operation.Reader;
import com.ameri.objects.classes.user.manager.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-manager")
public class DeleteManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        ManagerConverter converter = new ManagerConverter(Manager.class);

        Manager newManager = converter.fromJson(body);

        try {
            new DAOManagerImpl().delete(newManager);
            resp.getWriter().write(converter.toJson(newManager));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
