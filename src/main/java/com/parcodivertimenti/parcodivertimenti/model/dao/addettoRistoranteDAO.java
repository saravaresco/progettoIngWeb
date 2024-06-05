package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;

public interface addettoRistoranteDAO {

    public addettoRistorante create(
            String codiceFiscale,
            String posizione,
            Long ID_punto_ristoro);

    public void update(addettoRistorante user);

    public void delete(addettoRistorante user);

    public addettoRistorante findLoggedUser();/*serve a recuperare l'utente loggato*/

    public addettoRistorante findByUserCF(Long userCF);/*serve a recuperare utente dato il suo CF*/
}
