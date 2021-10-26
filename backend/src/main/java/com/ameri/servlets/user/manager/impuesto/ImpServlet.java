package com.ameri.servlets.user.manager.impuesto;

import com.ameri.converter.user.manager.ImpuestoConverter;
import com.ameri.dao.user.manager.DAOImpuestoImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.manager.Impuesto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-imp")
public class ImpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ImpuestoConverter converter = new ImpuestoConverter(Impuesto.class);
        try {
            Impuesto imp = new DAOImpuestoImpl().selectImpuesto();
            resp.getWriter().write(converter.toJson(imp));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        System.out.println("ESTA ENTRANDO EN LA MODIFICACIÓN DEL IMPUESTO.");
        String body = new Reader(reader).getInformation();
        ImpuestoConverter converter = new ImpuestoConverter(Impuesto.class);
        System.out.println(body);
        Impuesto newImp = converter.fromJson(body);
        DAOImpuestoImpl daoImpuesto = new DAOImpuestoImpl();

        System.out.println("LEE EL JSON.");

        try {
            if(daoImpuesto.selectImpuesto() != null){
                daoImpuesto.update(newImp);
            } else {
                daoImpuesto.insert(newImp);
            }

            resp.getWriter().write(converter.toJson(newImp));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
