package com.ameri.dao.ad;

import com.ameri.database.Connector;
import com.ameri.objects.classes.ads.AdType;
import com.ameri.objects.enums.ad.AdStatus;
import com.ameri.objects.interfaces.ad.DAOAd;
import com.ameri.objects.classes.ads.Ad;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAdImpl implements DAOAd {

    private final String INSERT_AD="INSERT INTO anuncio (tipo_anuncio, nombre_anuncio, nombre_anunciante, texto_anuncio, contenido_anuncio, cantidad_apariciones, total_pagar, estado_anuncio, fecha_inicio, fecha_fin) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String ACTIVATE_AD ="UPDATE anuncio SET estado_anuncio='ACTIVO' WHERE registro_anuncio=?";
    private final String UPDATE_AD_INFO = "UPDATE anuncio SET tipo_anuncio=?, nombre_anuncio=?, nombre_anunciante=?, texto_anuncio=?, contenido_anuncio=?, total_pagar=total_pagar + ?, estado_anuncio =?, fecha_fin=?";
    private final String GET_ALL_ADS = "SELECT * FROM anuncio";
    private final String GET_ADS_WHERE_STATUS ="SELET * FROM anuncio WHERE estado_anuncio=?";
    private final String DESACTIVATE_AD = "UPDATE anuncio SET estado_anuncio='VENCIDO' WHERE fecha_fin < CURDDATE()";

    public DAOAdImpl(){
        new Connector();
    }
    @Override
    public void insert(Ad ad) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_AD);
        query.setString(1, ad.getAdType().getTypeName());
        query.setString(2,ad.getAdName());
        query.setString(3,ad.getClientName());
        query.setString(4,ad.getAdText());
        query.setString(5, ad.getAdContent());
        query.setInt(6, ad.getViews());
        query.setBigDecimal(7,ad.getTotalCost());
        query.setString(8,ad.getAdStatus().getStatus());
        query.setDate(9,ad.getStartDate());
        query.setDate(10, ad.getEndDate());
        query.executeUpdate();
    }

    @Override
    public void activateAd(Ad ad) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(ACTIVATE_AD);
        query.setInt(1, ad.getAdRecord());
        query.executeUpdate();
    }

    @Override
    public void updateAdInfo(Ad ad) throws SQLException {

    }

    @Override
    public void updateUrl(Ad ad) throws SQLException {

    }

    @Override
    public void desactivateAd() throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(DESACTIVATE_AD);
        query.executeUpdate();
    }


    @Override
    public void delete(Ad ad) throws SQLException {

    }

    @Override
    public List<Ad> listAllAds() throws SQLException {
        List<Ad> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ALL_ADS);
        return getAds(list, query);
    }

    @Override
    public List<Ad> listAdWhereStatus(AdStatus adStatus) throws SQLException {
        List<Ad> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ADS_WHERE_STATUS);
        query.setString(1,adStatus.getStatus());
        return getAds(list, query);
    }

    private List<Ad> getAds(List<Ad> list, PreparedStatement query) throws SQLException {
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){
            list.add(new Ad(resultSet.getInt("registro_anuncio"), new AdType(resultSet.getString("tipo_anuncio"),new BigDecimal(0)), resultSet.getString("nombre_anuncio"), resultSet.getString("nombre_anunciante"), resultSet.getString("texto_anuncio"), resultSet.getString("contenido_anuncio"),resultSet.getInt("cantidad_apariciones"), resultSet.getBigDecimal("total_pagar"), AdStatus.value(resultSet.getString("estado_anuncio")), resultSet.getString("url_anuncio"), resultSet.getString("fecha_inicio"),resultSet.getString("fecha_fin")));
        }

        return list;
    }

}
