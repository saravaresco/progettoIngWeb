package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;

import java.sql.Time;

public interface puntoRistoroDAO {

    public puntoRistoro create(
            Long ID,
            String nome,
            String tipo_cucina,
            Time orario_apertura,
            Time orario_chiusura);

    public void update(puntoRistoro puntoristoro);

    public void delete(puntoRistoro puntoristoro);

    public puntoRistoro findById(Long ID);

    public puntoRistoro findByNome(String nome);

    public puntoRistoro findByTipoCucina(String tipo_cucina);

    public puntoRistoro findByOrarioA(Time orario_apertura);

    public puntoRistoro findByOrarioC(Time orario_chiusura);

}
