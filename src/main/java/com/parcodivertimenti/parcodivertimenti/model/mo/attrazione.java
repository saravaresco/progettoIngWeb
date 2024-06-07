package com.parcodivertimenti.parcodivertimenti.model.mo;

import java.util.Date;

public class attrazione {

    private Long codice;
    private String nome;
    private String tipologia;
    private Date data_ultima_manutenzione;

    public Long getCodice(){return codice;}
    public void setCodice(Long codice){this.codice = codice;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getTipologia(){return tipologia;}
    public void setTipologia(String tipologia){this.tipologia = tipologia;}

    public Date getData_ultima_manutenzione(){return data_ultima_manutenzione;}
    public void setData_ultima_manutenzione(Date data_ultima_manutenzione){this.data_ultima_manutenzione = data_ultima_manutenzione;}
}
