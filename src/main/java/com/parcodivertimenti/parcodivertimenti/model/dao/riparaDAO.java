package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;

public interface riparaDAO {

    public ripara create(
            String CF_manutentore,
            Long codice_attrazione);

    public void update(ripara user);

    public void delete(ripara user);

}
