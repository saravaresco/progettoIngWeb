package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.util.Date;

public interface bigliettoDAO {

    public biglietto create(
            Long ID,
            String codiceFiscale,
            Long prezzo,
            Date data_acquisto,
            Long tipoligia);

    public void update(biglietto user);

    public void delete(biglietto user);


}
