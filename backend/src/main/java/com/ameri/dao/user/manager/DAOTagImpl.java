package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.manager.Tag;
import com.ameri.objects.interfaces.user.manager.DAOTag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTagImpl implements DAOTag {

    public final String INSERT_TAG = "INSERT INTO etiqueta VALUES(?)";
    public final String UPDATE_TAG = "UPDATE etiqueta SET nombre_etiqueta =? WHERE nombre_etiqueta =?";
    public final String DELETE_TAG = "DELETE FROM etiqueta WHERE nombre_etiqueta =?";
    public final String GET_ALL_TAGS = "SELECT * FROM etiqueta";
    public final String GET_TAG ="SELECT * FROM etiqueta WHERE nombre_etiqueta = ?";

    public DAOTagImpl() {
        new Connector();
    }

    @Override
    public void insert(Tag tag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_TAG);
        query.setString(1, tag.getTagName());
        query.executeUpdate();

    }

    @Override
    public void update(Tag tag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_TAG);
        query.setString(1, tag.getTagName());
        query.setString(2, tag.getOldTagName());
        query.executeUpdate();
    }

    @Override
    public void delete(Tag tag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_TAG);
        query.setString(1, tag.getTagName());
        query.executeUpdate();
    }

    @Override
    public List<Tag> list(Tag tag) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ALL_TAGS);
        ResultSet result = query.executeQuery();

        while(result.next()){
            tags.add(new Tag(result.getString("nombre_etiqueta")));
        }

        return tags;
    }

    @Override
    public Tag getTag(Tag tag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_TAG);
        query.setString(1, tag.getTagName());
        ResultSet result = query.executeQuery();

        if(result.next()){
            return new Tag(result.getString("nombre_etiqueta"));
        }

        return null;
    }
}
