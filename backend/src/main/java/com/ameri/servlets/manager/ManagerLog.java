package com.ameri.servlets.manager;

import com.ameri.converter.user.manager.ManagerConverter;
import com.ameri.dao.user.manager.DAOManagerImpl;
import com.ameri.objects.classes.operation.Reader;
import com.ameri.objects.classes.user.manager.Manager;
import com.ameri.objects.enums.user.manager.ManagerStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/get-manager")
public class ManagerLog extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        String body = new Reader(reader).getInformation();

        ManagerConverter converter = new ManagerConverter(Manager.class);

        Manager manager = converter.fromJson(body);

        try {
            Manager investigator = new DAOManagerImpl().selectManager(manager);
            System.out.println("ENTRA A VERIFICAR");
            if(investigator.getManagerStatus() == ManagerStatus.VIGENTE){
                response.getWriter().write(converter.toJson(investigator));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
