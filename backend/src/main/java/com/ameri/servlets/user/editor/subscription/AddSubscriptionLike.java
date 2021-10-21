package com.ameri.servlets.user.editor.subscription;

import com.ameri.converter.user.editor.SubscriptionConverter;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.objects.classes.user.editor.Subscription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-subscription-like")
public class AddSubscriptionLike extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("magazineRecord"));
        int magazineRecord = Integer.parseInt(req.getParameter("magazineRecord"));
        try {
            Subscription subscription = new DAOSubscriptionImpl().getSubscriptionLikes(magazineRecord);
            resp.getWriter().write(new SubscriptionConverter(Subscription.class).toJson(subscription));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
