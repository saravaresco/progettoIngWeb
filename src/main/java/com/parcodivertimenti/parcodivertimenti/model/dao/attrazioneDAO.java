package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;

import java.util.Date;

public interface attrazioneDAO {

    public attrazione create(
            Long codice,
            String nome,
            String tipologia,
            Date data_ultima_manutenzione);

    public void update(attrazione attraz);

    public void delete(attrazione attraz);


    public attrazione findByCodice(Long codice);/*serve a recuperare attrazione dato il codice*/

    public attrazione findByNome(String nome);

    public attrazione findByTipologia(String tipologia);

    public attrazione findByDateM(Date data_ultima_manutenzione);


}
