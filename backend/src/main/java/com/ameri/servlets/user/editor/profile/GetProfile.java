package com.ameri.servlets.user.editor.profile;

import com.ameri.converter.user.UserConverter;
import com.ameri.converter.user.editor.ProfileConverter;
import com.ameri.dao.user.editor.DAOProfileImpl;
import com.ameri.objects.classes.operation.Reader;
import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.editor.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/get-profile-editor")
public class GetProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        System.out.println(body);

        UserConverter converter = new UserConverter(User.class);

        User userProfile = converter.fromJson(body);
        try {
            Profile profile = new DAOProfileImpl().listWhereEditorName(userProfile);
            System.out.println(profile.getEditorName());
            System.out.println(new ProfileConverter(Profile.class).toJson(profile));
            resp.getWriter().write(new ProfileConverter(Profile.class).toJson(profile));
        } catch (SQLException e) {
            System.err.println("error acá");
        }
    }
}
