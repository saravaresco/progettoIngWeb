package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;

import java.util.Date;

public interface dipendenteDAO {

    public dipendente create(
            String codice_fiscale,
            String nome,
            String cognome,
            String data_nascita,
            String sesso,
            Long stipendio,
            String username,
            String password);

    public void update(dipendente dip);

    public void delete(dipendente dip);

    public dipendente findLoggedUser(String username, String password, String categoria);

    public dipendente findByCF(String codice_fiscale);

    public dipendente findByNome(String nome);

    public dipendente findByCognome(String cognome);

    public dipendente findByDataNascita(String data_nascita);

    public dipendente findByStipendio(Long stipendio);

    public dipendente findByUsername(String username);

}
