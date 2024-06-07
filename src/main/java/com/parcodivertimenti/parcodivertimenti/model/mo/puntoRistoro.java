package com.parcodivertimenti.parcodivertimenti.model.mo;

import java.sql.Time;
import java.util.Date;

public class puntoRistoro {
    private Long ID;
    private String nome;
    private String tipo_cucina;
    private Time orario_apertura;
    private Time orario_chiusura;

    public Long getID(){return ID;}
    public void setID(Long ID){this.ID = ID;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getTipo_cucina(){return tipo_cucina;}
    public void setTipo_cucina(String tipo_cucina){this.tipo_cucina = tipo_cucina;}

    public Time getOrario_apertura(){return orario_apertura;}
    public void setOrario_apertura(Time orario_apertura){this.orario_apertura = orario_apertura;}

    public Time getOrario_chiusura(){return orario_chiusura;}
    public void setOrario_chiusura(Time orario_chiusura){this.orario_chiusura = orario_chiusura;}
}
