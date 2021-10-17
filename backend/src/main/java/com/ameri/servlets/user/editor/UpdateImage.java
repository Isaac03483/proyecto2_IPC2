package com.ameri.servlets.user.editor;

import com.ameri.dao.user.editor.DAOProfileImpl;
import com.ameri.objects.classes.user.editor.Profile;
import org.apache.tomcat.util.http.fileupload.IOUtils;

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
import java.sql.SQLException;
import java.util.stream.Collectors;

import static org.apache.tomcat.util.http.fileupload.IOUtils.*;

@WebServlet("/update-image")
@MultipartConfig(location = "/tmp")
public class UpdateImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = filePart.getHeader("Content-type");
        String editorName = req.getParameter("editorName");
        byte[] bytesData = {};
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            bytesData = buffer.lines().collect(Collectors.joining("\n")).getBytes();
        }

        System.out.println("EL NOMBRE DE USUARIO ES: "+editorName);

        Profile updateImage = new Profile(editorName, bytesData);

        try {
            new DAOProfileImpl().updateImage(updateImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
