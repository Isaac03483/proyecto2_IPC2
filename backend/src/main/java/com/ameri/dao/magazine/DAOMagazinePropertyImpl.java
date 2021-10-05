package com.ameri.dao.magazine;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineProperty;
import com.ameri.objects.interfaces.magazine.DAOMagazineProperty;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOMagazinePropertyImpl implements DAOMagazineProperty {

    private final String INSERT_MAGAZINE_PROPERTY="INSERT INTO caracteristica_revista (registro_revista,fecha_aceptacion,estado_revista,costo_por_dia,fecha_modificacion_cpd, like_revista, comentario,suscripcion) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE_MAGAZINE_PROPERTY="UPDATE caracteristica_revista SET fecha_aceptacion=?,estado_revista=?,costo_por_dia=?,fecha_modificacion_cpd=?,like_revista=?,comentario=?,suscripcion=? WHERE registro_revista=?";
    public DAOMagazinePropertyImpl(){
        Connector connector = new Connector();
    }

    @Override
    public void insert(MagazineProperty property) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MAGAZINE_PROPERTY);
        query.setInt(1,property.getMagazineRecord());
        query.setDate(2, property.getAcceptDate());
        query.setString(3, property.getMagazineStatus().getStatus());
        query.setBigDecimal(4, property.getDayCost());
        query.setDate(5,property.getUpdateDate());
        query.setString(6,property.getLike().getStatus());
        query.setString(7,property.getComment().getStatus());
        query.setString(8,property.getSubscription().getStatus());
        query.executeUpdate();
    }

    @Override
    public void update(MagazineProperty property) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_MAGAZINE_PROPERTY);
        query.setDate(1, property.getAcceptDate());
        query.setString(2, property.getMagazineStatus().getStatus());
        query.setBigDecimal(3, property.getDayCost());
        query.setDate(4,property.getUpdateDate());
        query.setString(5,property.getLike().getStatus());
        query.setString(6,property.getComment().getStatus());
        query.setString(7,property.getSubscription().getStatus());
        query.setInt(8,property.getMagazineRecord());
        query.executeUpdate();
    }

    @Override
    public void delete(MagazineProperty property) throws SQLException {

    }

    @Override
    public MagazineProperty getInfo(Magazine magazine) throws SQLException {
        return null;
    }
}
