package com.ameri.dao.ad;

import com.ameri.database.Connector;
import com.ameri.objects.interfaces.ad.DAOAd;
import com.ameri.objects.classes.ads.Ad;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOAdImpl implements DAOAd {

    private final String INSERT_AD="INSERT INTO anuncio (tipo_anuncio, nombre_anuncio, nombre_anunciante, texto_anuncio, contenido_anuncio, cantidad_apariciones, total_pagar, estado_anuncio, fecha_inicio, fecha_fin)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)";

    public DAOAdImpl(){
        new Connector();
    }
    @Override
    public void insert(Ad ad) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_AD);
        query.setString(1, ad.getAdsType().getTypeName());
        query.setString(2,ad.getAdName());
        query.setString(3,ad.getClientName());
        query.setString(4,ad.getAdText());
        query.setString(5, ad.getAdContent());
        query.setInt(6, ad.getViews());
        query.setBigDecimal(7,ad.getTotalCost());
        query.setString(8,ad.getAdsStatus().getStatus());
        query.setDate(9,ad.getStartDate());
        query.setDate(10, ad.getEndDate());
        query.executeUpdate();
    }

    @Override
    public void update(Ad ad) throws SQLException {

    }

    @Override
    public void delete(Ad ad) throws SQLException {

    }

    @Override
    public List<Ad> list() throws SQLException {

        return null;
    }
}
