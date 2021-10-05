package com.ameri.objects.interfaces.magazine;

import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineProperty;

import java.sql.SQLException;

public interface DAOMagazineProperty {

    void insert(MagazineProperty property) throws SQLException;

    void update(MagazineProperty property) throws SQLException;

    void delete(MagazineProperty property) throws  SQLException;

    MagazineProperty getInfo(Magazine magazine) throws SQLException;
}
