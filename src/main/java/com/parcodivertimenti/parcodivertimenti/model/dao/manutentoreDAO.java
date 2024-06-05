package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.manutentore;

public interface manutentoreDAO {

    public manutentore create(
            String codiceFiscale,
            Long numero_interventi);

    public void update(manutentore user);

    public void delete(manutentore user);

}
