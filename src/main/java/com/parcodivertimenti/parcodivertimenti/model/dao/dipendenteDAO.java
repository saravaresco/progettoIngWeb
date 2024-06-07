package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;

import java.util.Date;

public interface dipendenteDAO {

    public dipendente create(
            String codice_fiscale,
            String nome,
            String cognome,
            Date data_nascita,
            String sesso,
            Long stipendio);

    public void update(dipendente dip);

    public void delete(dipendente dip);

    public dipendente findLoggedUser();

    public dipendente findByCF(String codice_fiscale);

    public dipendente findByNome(String nome);

    public dipendente findByCognome(String cognome);

    public dipendente findByDataNascita(Date data_nascita);

    public dipendente findByStipendio(Long stipendio);

}
