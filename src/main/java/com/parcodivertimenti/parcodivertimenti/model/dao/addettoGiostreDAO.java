package com.parcodivertimenti.parcodivertimenti.model.dao;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
public interface addettoGiostreDAO {
    public addettoGiostre create(
            String codiceFiscale,
            String mansione,
            Long codice_giostra);

    public void update(addettoGiostre user);

    public void delete(addettoGiostre user);

    public addettoGiostre findLoggedUser();/*serve a recuperare l'utente loggato*/

    public addettoGiostre findByUserCF(Long userCF);/*serve a recuperare utente dato il suo CF*/


}