package com.ameri.objects.interfaces.magazine;

import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineTag;

import java.sql.SQLException;

public interface DAOMagazineTag {

    void insert(MagazineTag tag) throws SQLException;

    void update(MagazineTag tag) throws SQLException;

    void delete(MagazineTag tag) throws SQLException;

    void listMagazineTags(Magazine magazine) throws SQLException;
}
