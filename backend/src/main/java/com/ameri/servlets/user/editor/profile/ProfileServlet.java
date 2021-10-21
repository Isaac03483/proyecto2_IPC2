package com.ameri.servlets.user.editor.profile;

import com.ameri.converter.user.editor.ProfileConverter;
import com.ameri.dao.user.editor.DAOProfileImpl;
import com.ameri.objects.classes.operation.Reader;
import com.ameri.objects.classes.user.editor.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();
        System.out.println(body);
        ProfileConverter converter = new ProfileConverter(Profile.class);
        Profile updateProfile = converter.fromJson(body);

        try {
            new DAOProfileImpl().updateInf(updateProfile);
            System.out.println("ENTRA A ACTUALIZAR.");
            resp.getWriter().write(converter.toJson(updateProfile));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
