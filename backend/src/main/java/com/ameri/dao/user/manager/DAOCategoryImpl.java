package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.interfaces.user.manager.DAOCategory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOCategoryImpl implements DAOCategory {

    private final String INSERT_CATEGORY="INSERT INTO categoria (nombre_categoria) VALUES (?)";
    private final String UPDATE_CATEGORY="UPDATE categoria SET nombre_categoria WHERE registro_categoria=?";

    public DAOCategoryImpl(){
        new Connector();
    }

    @Override
    public void insert(Category category) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_CATEGORY);
        query.setString(1,category.getCategoryName());
        query.executeUpdate();
    }

    @Override
    public void update(Category category) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_CATEGORY);
        query.setString(1,category.getCategoryName());
        query.setInt(2,category.getCategoryRecord());
        query.executeUpdate();
    }

    @Override
    public void delete(Category category) throws SQLException {

    }

    @Override
    public List<Category> list() throws SQLException {
        return null;
    }
}
