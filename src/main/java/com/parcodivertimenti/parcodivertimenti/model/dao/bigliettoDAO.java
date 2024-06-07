package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.util.Date;

public interface bigliettoDAO {

    public biglietto create(
            Long ID,
            String codice_fiscale,
            Long prezzo,
            Date data_acquisto,
            Long tipoligia);

    public void update(biglietto bigl);

    public void delete(biglietto bigl);

    public biglietto findById(Long ID);

    public biglietto findByCF(String codice_fiscale);

    public biglietto findByData(Date data_acquisto);

    public biglietto findByTipologia(Long tipologia);


}
