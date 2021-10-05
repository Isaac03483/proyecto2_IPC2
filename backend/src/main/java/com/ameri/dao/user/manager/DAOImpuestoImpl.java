package com.ameri.dao.user.manager;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.manager.Impuesto;
import com.ameri.objects.interfaces.user.manager.DAOImpuesto;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOImpuestoImpl implements DAOImpuesto {

    private final String INSERT_IMPUESTO = "INSERT INTO porcentaje_impuesto (porcentaje, fecha_actualizacion) VALUES (?,?)";
    private final String UPDATE_IMPUESTO = "UPDATE porcentaje_impuesto SET porcentaje=?, fecha_actualizacion=?";

    public DAOImpuestoImpl(){
        new Connector();
    }

    @Override
    public void insert(Impuesto impuesto) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_IMPUESTO);
        query.setBigDecimal(1,impuesto.getPercentage());
        query.setDate(2,impuesto.getUpdateDate());
        query.executeUpdate();
    }

    @Override
    public void update(Impuesto impuesto) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_IMPUESTO);
        query.setBigDecimal(1,impuesto.getPercentage());
        query.setDate(2,impuesto.getUpdateDate());
        query.setInt(3,impuesto.getRegistroImpuesto());
        query.executeUpdate();

    }

    @Override
    public Impuesto selectImpuesto() throws SQLException {
        return null;
    }
}
