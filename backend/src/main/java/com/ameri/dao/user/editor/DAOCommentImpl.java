package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.Beans.AdminBeans;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.interfaces.user.editor.DAOComment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOCommentImpl implements DAOComment {

    private final String INSERT_COMMENT="INSERT INTO comentario (registro_revista, nombre_suscriptor, texto, fecha_comentario) VALUES (?,?,?,?)";
    private final String UPDATE_COMMENT="UPDATE comentario SET texto=?,fecha_comentario=? WHERE registro_comentario=?";
    private final String DELETE_COMMENT="DELETE FROM comentario WHERE registro_comentario=?";
    private final String LIST_MAGAZINE_COMMENTS ="SELECT * FROM comentario WHERE registro_revista=? ORDER BY fecha_comentario DESC";
    private final String LIST_MAGAZINE_COMMENTS_BETWEEN_FILTER = "SELECT * FROM comentario WHERE registro_revista=? AND fecha_comentario BETWEEN ? AND ? ORDER BY fecha_comentario DESC";
    private final String GET_MAGAZINE_COMMENT_COUNT = "SELECT registro_revista, count(*) as contador FROM comentario GROUP BY registro_revista ORDER BY registro_revista DESC LIMIT 5";
    private final String GET_MAGAZINE_COMMENT_COUNT_BETWEEN = "SELECT registro_revista, count(*) as contador FROM comentario WHERE fecha_comentario BETWEEN ? AND ? GROUP BY registro_revista ORDER BY registro_revista DESC LIMIT 5";


    public DAOCommentImpl(){
        new Connector();
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_COMMENT);
        query.setInt(1,comment.getMagazineRecord());
        query.setString(2,comment.getSubscriberName());
        query.setString(3,comment.getText());
        query.setDate(4,comment.getCommentDate());
        query.executeUpdate();

    }

    @Override
    public void update(Comment comment) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_COMMENT);
        query.setString(1,comment.getText());
        query.setDate(2,comment.getCommentDate());
        query.setInt(3,comment.getCommentRecord());
        query.executeUpdate();
    }

    @Override
    public void delete(Comment comment) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_COMMENT);
        query.setInt(1,comment.getCommentRecord());
        query.executeUpdate();
    }

    @Override
    public List<Comment> listAllComments() throws SQLException {
        return null;
    }

    @Override
    public List<Comment> listMagazineComments(int magazineRecord) throws SQLException {
        List<Comment> list = new ArrayList<>();

        PreparedStatement query = Connector.getConnection().prepareStatement(LIST_MAGAZINE_COMMENTS);
        query.setInt(1, magazineRecord);
        ResultSet resultSet = query.executeQuery();

        while(resultSet.next()){
            list.add(new Comment(resultSet.getInt("registro_comentario"), resultSet.getInt("registro_revista"), resultSet.getString("nombre_suscriptor"),resultSet.getString("texto"), String.valueOf(resultSet.getDate("fecha_comentario"))));
        }
        return list;
    }

    @Override
    public List<Comment> listMagazineCommentsBetween(int magazineRecord, LocalDate startDate, LocalDate endDate) throws SQLException {
        List<Comment> list = new ArrayList<>();

        PreparedStatement query = Connector.getConnection().prepareStatement(LIST_MAGAZINE_COMMENTS_BETWEEN_FILTER);
        query.setInt(1, magazineRecord);
        query.setString(2, startDate.toString());
        query.setString(3,endDate.toString());
        ResultSet resultSet = query.executeQuery();

        while(resultSet.next()){
            list.add(new Comment(resultSet.getInt("registro_comentario"), resultSet.getInt("registro_revista"), resultSet.getString("nombre_suscriptor"),resultSet.getString("texto"), String.valueOf(resultSet.getDate("fecha_comentario"))));
        }
        return list;
    }

    @Override
    public List<AdminBeans> listTopComments() throws SQLException {
        List<AdminBeans> adminBeans = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_COMMENT_COUNT);
        ResultSet resultSet = query.executeQuery();

        while(resultSet.next()){
            adminBeans.add(new AdminBeans(resultSet.getInt("contador"), resultSet.getInt("registro_revista")));
        }

        return adminBeans;
    }

    @Override
    public List<AdminBeans> listTopCommentsBetween(LocalDate start, LocalDate end) throws SQLException {
        List<AdminBeans> adminBeans = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_COMMENT_COUNT_BETWEEN);
        query.setString(1, start.toString());
        query.setString(2,end.toString());
        ResultSet resultSet = query.executeQuery();

        while(resultSet.next()){
            adminBeans.add(new AdminBeans(resultSet.getInt("contador"), resultSet.getInt("registro_revista")));
        }

        return adminBeans;
    }

}
