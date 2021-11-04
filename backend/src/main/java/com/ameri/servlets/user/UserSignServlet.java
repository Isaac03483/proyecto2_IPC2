package com.ameri.servlets.user;

import com.ameri.converter.user.UserConverter;
import com.ameri.dao.user.DAOUserImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/get-user")
public class UserSignServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        String body = new Reader(reader).getInformation();

        UserConverter converter = new UserConverter(User.class);

        User user = converter.fromJson(body);
        try {
            User investigator = new DAOUserImpl().getUser(user);
            System.out.println(investigator.getUserName()+" "+investigator.getUserType());
            if(investigator.getUserPassword().equals(user.getUserPassword())){
                response.getWriter().write(converter.toJson(investigator));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
