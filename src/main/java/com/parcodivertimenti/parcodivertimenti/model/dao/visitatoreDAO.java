package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.util.Date;

public interface visitatoreDAO {

    public visitatore create(
            String codice_fiscale,
            String nome,
            String cognome,
            Date data_nascita,
            String sesso);

    public void update(visitatore visit);

    public void delete(visitatore visit);

    public visitatore findLoggedUser();

    public visitatore findByCF(String codice_fiscale);

    public visitatore findByNome(String nome);

    public visitatore findByCognome(String cognome);

    public visitatore findByDataNascita(Date data_nascita);

}
