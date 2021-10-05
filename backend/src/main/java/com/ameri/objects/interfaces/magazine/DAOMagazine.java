package com.ameri.objects.interfaces.magazine;

import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.manager.Category;

import java.sql.SQLException;
import java.util.List;

public interface DAOMagazine {

    void insert(Magazine magazine) throws SQLException;

    void update(Magazine magazine) throws SQLException;

    void delete(Magazine magazine) throws SQLException;

    List<Magazine> listAllMagazines() throws SQLException;

    List<Magazine> listEditorMagazines(Profile profile) throws SQLException;

    List<Magazine> listMagazinesWhereName(Magazine magazine) throws SQLException;

    List<Magazine> listMagazinesWhereCategory(Category category) throws SQLException;
}
