package com.parcodivertimenti.parcodivertimenti.model.dao;

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

    public void update(spettacolo user);

    public void delete(spettacolo user);
}
