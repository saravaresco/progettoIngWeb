package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.attore;

public interface attoreDAO {

    public attore create(
            String codice_fiscale,
            String ruolo,
            String username,
            String password);

    public void update(attore att);

    public void delete(attore att);

    public attore findLoggedUser();/*serve a recuperare l'utente loggato*/

    public attore findByUserCF(String codice_fiscale);/*serve a recuperare utente dato il suo CF*/

    public attore findByRuolo(String ruolo);/*serve a recuperare utente dato il suo CF*/

    public attore findByUsername(String username);


}
