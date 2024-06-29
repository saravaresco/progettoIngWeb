package com.parcodivertimenti.parcodivertimenti.model.dao;
import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;

public interface riparaDAO {
    public ripara create(
            String cf_manutentore,
            Long codice_attrazione,
            String descrizione);

    public void update(ripara Ripara);

    public void delete(ripara Ripara);
    public ripara findByCFManutentore(String cf_manutentore);
    public ripara findByCodiceAttrazione(Long codice_attrazione);

}
