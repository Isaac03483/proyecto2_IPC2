package com.ameri.objects.interfaces.ad;

import com.ameri.objects.classes.ads.AdType;

import java.sql.SQLException;
import java.util.List;

public interface DAOAdType {

    void insert(AdType adsType) throws SQLException;

    void update(AdType adsType) throws  SQLException;

    void delete(AdType adsType) throws  SQLException;

    List<AdType> list() throws  SQLException;
}
