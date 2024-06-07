package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;

import java.sql.Time;
import java.util.Date;

public interface spettacoloDAO {

    public spettacolo create(
            String nome,
            String tipoloigia,
            Date data,
            String luogo,
            Time orario_inizio,
            Time durata);

    public void update(spettacolo spett);

    public void delete(spettacolo spett);

    public spettacolo findByNome(String nome);

    public spettacolo findByTipologia(String tipologia);

    public spettacolo findByData(Date data);

    public spettacolo findByLuogo(String luogo);

    public spettacolo findByOrarioI(Time orario_inizio);
}
