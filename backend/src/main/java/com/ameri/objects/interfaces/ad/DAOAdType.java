package com.ameri.objects.interfaces.ad;

import com.ameri.objects.classes.ads.AdsType;

import java.sql.SQLException;
import java.util.List;

public interface DAOAdType {

    void insert(AdsType adsType) throws SQLException;

    void update(AdsType adsType) throws  SQLException;

    void delete(AdsType adsType) throws  SQLException;

    List<AdsType> list() throws  SQLException;
}
