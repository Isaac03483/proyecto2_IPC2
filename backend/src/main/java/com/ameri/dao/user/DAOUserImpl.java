package com.ameri.dao.user;

import com.ameri.database.Connector;
import com.ameri.objects.enums.user.UserType;
import com.ameri.objects.interfaces.user.DAOUser;
import com.ameri.objects.classes.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOUserImpl implements DAOUser {

    private final String INSERT_USER = "INSERT INTO usuario (nombre_usuario, password, tipo) VALUES(?,?,?)";
    private final String UPDATE_USER = "UPDATE usuario SET password = ? WHERE usuario = ?";
    private final String GET_USER = "SELECT * FROM usuario WHERE nombre_usuario =?";

    public DAOUserImpl(){
        new Connector();
    }

    @Override
    public void insert(User user) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_USER);
        query.setString(1, user.getUserName());
        query.setString(2, user.getUserPassword());
        query.setString(3, user.getUserType().getType());
        query.executeUpdate();

    }

    @Override
    public void update(User user) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_USER);
        query.setString(1, user.getUserPassword());
        query.setString(2, user.getUserName());
        query.executeUpdate();

    }

    @Override
    public void delete(User user) throws SQLException {

    }

    @Override
    public List<User> list() throws SQLException {
        return null;
    }

    @Override
    public User getUser(String userName) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_USER);
        query.setString(1,userName);
        ResultSet result = query.executeQuery();

        if(result.next()){
            return new User(result.getString("nombre_usuario"), result.getString("password"), UserType.value(result.getString("tipo")));
        }

        return null;
    }
}
