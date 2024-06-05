package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.attore;

public interface attoreDAO {

    public attore create(
            String codiceFiscale,
            String ruolo);

    public void update(attore user);

    public void delete(attore user);


}
