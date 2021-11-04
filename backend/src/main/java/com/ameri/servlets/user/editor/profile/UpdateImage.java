package com.ameri.servlets.user.editor.profile;

import com.ameri.converter.user.editor.ProfileConverter;
import com.ameri.dao.user.editor.DAOProfileImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.editor.Profile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-image")
public class UpdateImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        ProfileConverter converter = new ProfileConverter(Profile.class);

        Profile profile = converter.fromJson(body);

        try {
            new DAOProfileImpl().updateImage(profile);
            resp.getWriter().write(converter.toJson(profile));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
