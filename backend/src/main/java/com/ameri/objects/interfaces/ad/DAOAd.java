package com.ameri.objects.interfaces.ad;

import com.ameri.objects.classes.ads.Ad;
import com.ameri.objects.enums.ad.AdStatus;

import java.sql.SQLException;
import java.util.List;

public interface DAOAd {

    void insert(Ad ad) throws SQLException;

    void activateAd(Ad ad) throws  SQLException;

    void updateAdInfo(Ad ad) throws SQLException;

    void updateUrl(Ad ad) throws SQLException;

    void desactivateAd() throws SQLException;

    void delete(Ad ad) throws  SQLException;

    List<Ad> listAllAds() throws SQLException;

    List<Ad> listAdWhereStatus(AdStatus adStatus) throws SQLException;
}
