package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;

import java.util.Date;

public interface dipendenteDAO {

    public dipendente create(
            String codiceFiscale,
            String nome,
            String cognome,
            Date data_nascita,
            String sesso,
            Long stipendio);

    public void update(dipendente user);

    public void delete(dipendente user);

}
