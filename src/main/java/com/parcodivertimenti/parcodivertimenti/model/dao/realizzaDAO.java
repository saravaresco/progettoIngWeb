package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.realizza;

public interface realizzaDAO {

    public realizza create(
            String CF_attore,
            String nome_spettacolo);

    public void update(realizza user);

    public void delete(realizza user);


}
