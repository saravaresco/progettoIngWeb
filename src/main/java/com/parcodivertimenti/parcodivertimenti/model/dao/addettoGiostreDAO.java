package com.parcodivertimenti.parcodivertimenti.model.dao;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
public interface addettoGiostreDAO {
    public addettoGiostre create(
            String codice_fiscale,
            String mansione,
            Long codice_giostra);

    public void update(addettoGiostre addettogiostre);

    public void delete(addettoGiostre addettogiostre);

    public addettoGiostre findLoggedUser();/*serve a recuperare l'utente loggato*/

    public addettoGiostre findByUserCF(String codice_fiscale);/*serve a recuperare utente dato il suo CF*/

    public addettoGiostre findByMansione(String mansione);


}