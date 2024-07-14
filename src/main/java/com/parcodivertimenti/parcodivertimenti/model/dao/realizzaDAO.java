package com.parcodivertimenti.parcodivertimenti.model.dao;
import com.parcodivertimenti.parcodivertimenti.model.mo.realizza;

import java.util.List;

public interface realizzaDAO {
    public realizza create(
            String cf_attore,
            String nome_spettacolo);

    public void update(realizza Ripara);

    public void delete(realizza Ripara);
    public List<realizza> findByCFAttore(String cf_attore);
    public realizza findByNomeSpettacolo(String nome_spettacolo);

}
