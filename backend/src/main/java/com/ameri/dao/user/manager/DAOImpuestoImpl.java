package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.manager.Impuesto;
import com.ameri.objects.interfaces.user.manager.DAOImpuesto;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpuestoImpl implements DAOImpuesto {

    private final String INSERT_IMPUESTO = "INSERT INTO porcentaje_impuesto (registro, porcentaje, fecha_actualizacion) VALUES (?,?,?)";
    private final String UPDATE_IMPUESTO = "UPDATE porcentaje_impuesto SET porcentaje=?, fecha_actualizacion=?";
    private final String SELECT_IMPUESTO = "SELECT * FROM porcentaje_impuesto";

    public DAOImpuestoImpl(){
        new Connector();
    }

    @Override
    public void insert(Impuesto impuesto) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_IMPUESTO);
        query.setInt(1,impuesto.getRegistroImpuesto());
        query.setBigDecimal(2,impuesto.getPercentage());
        query.setDate(3,impuesto.getUpdateDate());
        query.executeUpdate();
    }

    @Override
    public void update(Impuesto impuesto) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_IMPUESTO);
        query.setBigDecimal(1,impuesto.getPercentage());
        query.setDate(2,impuesto.getUpdateDate());
        query.executeUpdate();

    }

    @Override
    public Impuesto selectImpuesto() throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(SELECT_IMPUESTO);
        ResultSet result = query.executeQuery();

        if(result.next()){
            return new Impuesto(result.getInt("registro"), result.getBigDecimal("porcentaje").intValue(), String.valueOf(result.getDate("fecha_actualizacion")));
        }
        return null;
    }
}
