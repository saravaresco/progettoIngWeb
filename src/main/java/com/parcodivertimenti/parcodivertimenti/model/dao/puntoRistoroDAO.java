package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;

import java.sql.Time;

public interface puntoRistoroDAO {

    public puntoRistoro create(
            Long ID,
            String nome,
            String tipo_cucina,
            Time orario_apertura,
            Time orario_chiusura);

    public void update(puntoRistoro user);

    public void delete(puntoRistoro user);

}
