package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;

import java.util.Date;

public interface attrazioneDAO {

    public attrazione create(
            Long codice,
            String nome,
            String tipologia,
            Date data_ultima_manutenzione);

    public void update(attrazione user);

    public void delete(attrazione user);

}
