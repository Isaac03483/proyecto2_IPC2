package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.interfaces.user.editor.DAOComment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOCommentImpl implements DAOComment {

    private final String INSERT_COMMENT="INSERT INTO comentario (registro_revista, nombre_suscriptor, texto, fecha_comentario) VALUES (?,?,?,?)";
    private final String UPDATE_COMMENT="UPDATE comentario SET texto=?,fecha_comentario=? WHERE registro_revista=?";
    private final String DELETE_COMMENT="DELETE FROM comentario WHERE registro_revista=?";

    public DAOCommentImpl(){
        Connector connector = new Connector();
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
        return null;
    }
}
