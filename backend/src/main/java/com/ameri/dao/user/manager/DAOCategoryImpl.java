package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.interfaces.user.manager.DAOCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategoryImpl implements DAOCategory {

    private final String INSERT_CATEGORY="INSERT INTO categoria (nombre_categoria) VALUES (?)";
    private final String UPDATE_CATEGORY="UPDATE categoria SET nombre_categoria=? WHERE registro_categoria=?";
    private final String DELETE_CATEGORY ="DELETE FROM categoria WHERE registro_categoria=?";
    private final String GET_CATEGORY ="SELECT * FROM categoria WHERE nombre_categoria=?";
    private final String ALL = "SELECT * FROM categoria";

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
        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_CATEGORY);
        query.setInt(1,category.getCategoryRecord());
        query.executeUpdate();
    }

    @Override
    public List<Category> list() throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(ALL);
        ResultSet result = query.executeQuery();
        List<Category> categories = new ArrayList<>();

        while(result.next()){
            categories.add(new Category(result.getString("nombre_categoria"), result.getInt("registro_categoria")));
        }

        return categories;
    }

    @Override
    public Category getCategory(Category category) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_CATEGORY);
        query.setString(1, category.getCategoryName());
        ResultSet result = query.executeQuery();

        if(result.next()){
            return new Category(result.getString("nombre_categoria"), result.getInt("registro_categoria"));
        }

        return null;
    }
}
