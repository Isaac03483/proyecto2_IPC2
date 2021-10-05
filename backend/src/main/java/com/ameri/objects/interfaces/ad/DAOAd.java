package com.ameri.objects.interfaces.ad;

import com.ameri.objects.classes.ads.Ad;

import java.sql.SQLException;
import java.util.List;

public interface DAOAd {

    void insert(Ad ad) throws SQLException;

    void update(Ad ad) throws  SQLException;

    void delete(Ad ad) throws  SQLException;

    List<Ad> list() throws SQLException;
}
