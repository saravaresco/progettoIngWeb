package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;

public interface addettoRistoranteDAO {

    public addettoRistorante create(
            String codice_fiscale,
            String posizione,
            Long ID_punto_ristoro,
            String username,
            String password);

    public void update(addettoRistorante addettoristorante);

    public void delete(addettoRistorante addettoristorante);

    public addettoRistorante findLoggedUser();/*serve a recuperare l'utente loggato*/

    public addettoRistorante findByUserCF(String codice_fiscale);/*serve a recuperare utente dato il suo CF*/

    public addettoRistorante findByPuntoRistoro(Long ID_punto_ristoro);

    public addettoRistorante findByUsername(String username);
}
