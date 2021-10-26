package com.ameri.dao.ad;

import com.ameri.database.Connector;
import com.ameri.objects.classes.ads.AdType;
import com.ameri.objects.interfaces.ad.DAOAdType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAOAdTypeImpl implements DAOAdType {

    private final String UPDATE_DAY_COST = "UPDATE tipo_anuncio SET costo_dia =? WHERE nombre_tipo = ?";
    private final String GET_AD_TYPE = "SELECT * FROM tipo_anuncio";

    public DAOAdTypeImpl(){
        new Connector();
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
        List<AdType> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_AD_TYPE);
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){
            list.add(new AdType(resultSet.getString("nombre_tipo"), resultSet.getBigDecimal("costo_dia")));
        }

        return list;
    }
}
