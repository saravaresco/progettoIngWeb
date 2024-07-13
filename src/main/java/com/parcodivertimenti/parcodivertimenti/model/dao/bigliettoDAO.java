package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.util.Date;
import java.util.List;

public interface bigliettoDAO {

    public biglietto create(
            Long ID,
            String codice_fiscale,
            Long prezzo,
            String data_acquisto,
            String tipologia1,
            String tipologia2,
            String mail);

    public void update(biglietto bigl);

    public void delete(biglietto bigl);

    public biglietto findById(Long ID);

    public List<biglietto> findByCF(String codice_fiscale);

    public biglietto findByData(String data_acquisto);

    public biglietto findByTipologia1(String tipologia1);

    public biglietto findByTipologia2(String tipologia2);


    public biglietto findByMail(String mail);

}
