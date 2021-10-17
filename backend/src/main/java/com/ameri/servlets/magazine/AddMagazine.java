package com.ameri.servlets.magazine;

import com.ameri.converter.magazine.MagazineConverter;
import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.enums.magazine.MagazineComment;
import com.ameri.objects.enums.magazine.MagazineLike;
import com.ameri.objects.enums.magazine.MagazineStatus;
import com.ameri.objects.enums.magazine.MagazineSubscription;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/add-magazine")
@MultipartConfig(location = "/tmp")
public class AddMagazine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editorName = req.getParameter("editorName");

        Profile profile = new Profile(editorName);

        try {
            List<Magazine> list = new DAOMagazineImpl().listEditorMagazines(profile);
            resp.getWriter().write(new MagazineConverter(Magazine.class).toJson(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part file = req.getPart("file");
        String editorName = req.getParameter("editorName");
        String magazineName = req.getParameter("magazineName");
        BigDecimal subscriptionCost  = new BigDecimal(req.getParameter("subscriptionCost"));
        String publicationDate = req.getParameter("publicationDate");
        String description = req.getParameter("description");
        Category category = new Category(req.getParameter("categoryName"));
        MagazineStatus status = MagazineStatus.value(req.getParameter("magazineStatus"));
        MagazineLike like = MagazineLike.value(req.getParameter("magazineLike"));
        MagazineComment comment = MagazineComment.value(req.getParameter("magazineComment"));
        MagazineSubscription subscription = MagazineSubscription.value(req.getParameter("magazineSubscription"));

        System.out.println(category.getCategoryName());

        byte[] bytesData = {};
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            bytesData = buffer.lines().collect(Collectors.joining("\n")).getBytes();
        }

        Magazine magazine = new Magazine(editorName,magazineName, bytesData,publicationDate, description,category,subscriptionCost, status, like, comment, subscription);

        try {
            new DAOMagazineImpl().insert(magazine);
            resp.getWriter().write(new MagazineConverter(Magazine.class).toJson(magazine));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
