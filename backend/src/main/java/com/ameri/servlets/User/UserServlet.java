package com.ameri.servlets.User;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ameri.converter.user.UserConverter;
import com.ameri.dao.user.DAOUserImpl;
import com.ameri.dao.user.editor.DAOProfileImpl;
import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.enums.user.UserType;
import com.google.gson.annotations.SerializedName;


@WebServlet("/user")
public class UserServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        BufferedReader reader = request.getReader();
        System.out.println("Si entra por acá");
        String line = reader.readLine();
        String body="";
        
        while(line !=null){
            body+=line;
            line= reader.readLine();
        }
        System.out.println(body);

        UserConverter converter = new UserConverter(User.class);
        User user = converter.fromJson(body);
        System.out.println(user.getUserName()+" "+user.getUserType());
        try {
            new DAOUserImpl().insert(user);
            new DAOProfileImpl().insert(new Profile(user.getUserName()));
            response.getWriter().append(converter.toJson(user));
            System.out.println("Usuario creado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
