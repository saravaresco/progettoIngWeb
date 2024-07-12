package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.util.Date;

public interface visitatoreDAO {

    public visitatore create(
            String codice_fiscale,
            String nome,
            String cognome,
            String data_nascita,
            String sesso,
            String username,
            String password);

    public void update(visitatore visit);

    public void delete(visitatore visit);

    public visitatore findLoggedUser(String username, String password);

    public visitatore findByCF(String codice_fiscale);

    public visitatore findByNome(String nome);

    public visitatore findByCognome(String cognome);

    public visitatore findByDataNascita(String data_nascita);

    public visitatore findByUsername(String username);

}
