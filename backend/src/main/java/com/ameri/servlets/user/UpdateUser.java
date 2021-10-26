package com.ameri.servlets.user;

import com.ameri.converter.user.UserConverter;
import com.ameri.dao.user.DAOUserImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-user")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        UserConverter converter = new UserConverter(User.class);
        System.out.println(body);
        User userUpdate = converter.fromJson(body);
        System.out.println(userUpdate.getUserName()+" "+userUpdate.getUserPassword()+" "+userUpdate.getOldUserName());

        try{
            if(userUpdate.getUserPassword() == null){
                new DAOUserImpl().updateUserName(userUpdate);
                System.out.println("SI ENTRA A MODIFICAR EL NOMBRE");
            } else {
                new DAOUserImpl().updateUserPass(userUpdate);
                System.out.println("SI ENTRA A MODIFICAR LA CONTRASEÑA");
            }
            resp.getWriter().write(converter.toJson(userUpdate));
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
