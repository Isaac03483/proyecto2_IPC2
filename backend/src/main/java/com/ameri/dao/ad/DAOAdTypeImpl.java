package com.ameri.dao.ad;

import com.ameri.database.Connector;
import com.ameri.objects.classes.ads.AdType;
import com.ameri.objects.interfaces.ad.DAOAdType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOAdTypeImpl implements DAOAdType {

    private final String INSERT_AD_TYPE ="INSERT INTO tipo_anuncio (nombre_tipo, costo_dia) VALUES (?,?)";
    private final String UPDATE_DAY_COST = "UPDATE tipo_anuncio SET costo_dia =? WHERE nombre_tipo = ?";

    public DAOAdTypeImpl(){
        new Connector();
    }

    @Override
    public void insert(AdType adsType) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_AD_TYPE);
        query.setString(1, adsType.getTypeName());
        query.setBigDecimal(2, adsType.getDayCost());
        query.executeUpdate();
    }

    @Override
    public void update(AdType adsType) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_DAY_COST);
        query.setBigDecimal(1, adsType.getDayCost());
        query.setString(2, adsType.getTypeName());
        query.executeUpdate();

    }

    @Override
    public void delete(AdType adsType) throws SQLException {

    }

    @Override
    public List<AdType> list() throws SQLException {
        return null;
    }
}
