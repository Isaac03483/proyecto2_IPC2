package com.ameri.objects.interfaces.user.manager;

import com.ameri.objects.classes.user.manager.Impuesto;

import java.sql.SQLException;

public interface DAOImpuesto {

    void insert(Impuesto impuesto) throws SQLException;

    void update(Impuesto impuesto) throws SQLException;

    Impuesto selectImpuesto() throws SQLException;
}
