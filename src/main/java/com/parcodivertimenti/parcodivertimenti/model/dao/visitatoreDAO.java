package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.util.Date;

public interface visitatoreDAO {

    public visitatore create(
            String codiceFiscale,
            String nome,
            String cognome,
            Date data_nascita,
            String sesso);

    public void update(visitatore user);

    public void delete(visitatore user);

}
