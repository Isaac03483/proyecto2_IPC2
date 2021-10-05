package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.manager.Manager;
import com.ameri.objects.interfaces.user.manager.DAOManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOManagerImpl implements DAOManager {

    private final String INSERT_MANAGER = "INSERT INTO administrador(nombre_usuario,estado_administrador) VALUES (?,?)";
    private final String UPDATE_MANAGER = "UPDATE administrador SET estado_administrador=? WHERE nombre_usuario=?";

    public DAOManagerImpl(){
        Connector connector = new Connector();
    }

    @Override
    public void insert(Manager manager) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MANAGER);
        query.setString(1,manager.getManagerName());
        query.setString(2, manager.getManagerStatus().getStatus());
        query.executeUpdate();
    }

    @Override
    public void update(Manager manager) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_MANAGER);
        query.setString(1, manager.getManagerStatus().getStatus());
        query.setString(2,manager.getManagerName());
        query.executeUpdate();
    }

    @Override
    public void delete(Manager manager) throws SQLException {

    }

    @Override
    public List<Manager> list() throws SQLException {
        return null;
    }

    @Override
    public Manager selectManager(User user) throws SQLException {
        return null;
    }
}
