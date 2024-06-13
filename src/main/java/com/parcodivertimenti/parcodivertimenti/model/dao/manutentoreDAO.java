package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.manutentore;

public interface manutentoreDAO {

    public manutentore create(
            String codice_fiscale,
            Long numero_interventi,
            String username,
            String password);

    public void update(manutentore manut);

    public void delete(manutentore manut);

    public manutentore findLoggedUser();

    public manutentore findByCF(String codice_fiscale);

    public manutentore findByNumeroInt(Long numero_interventi);

    public manutentore findByUsername(String username);

}
