package com.parcodivertimenti.parcodivertimenti.model.mo;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;

public class spettacolo {

    private String nome;
    private String tipologia;
    private Date data;
    private String luogo;
    private Time orario_inizio;
    private Time durata;

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getTipologia(){return tipologia;}
    public void setTipologia(String tipologia){this.tipologia = tipologia;}

    public Date getData(){return data;}
    public void setData(Date data){this.data = data;}

    public String getLuogo(){return luogo;}
    public void setLuogo(String luogo){this.luogo = luogo;}

    public Time getOrario_inizio(){return orario_inizio;}
    public void setOrario_inizio(Time orario_inizio){this.orario_inizio = orario_inizio;}

    public Time getDurata(){return durata;}
    public void setDurata(Time durata){this.durata = durata;}
}
