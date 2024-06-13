package com.parcodivertimenti.parcodivertimenti.model.mo;

import java.util.Date;

public class dipendente {

    private String codice_fiscale;
    private String nome;
    private String cognome;
    private Date data_nascita;
    private String sesso;
    private Long stipendio;
    private String username;
    private String password;

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    public String getCognome(){return cognome;}
    public void setCognome(String cognome){this.cognome = cognome;}

    public Date getData_nascita(){return data_nascita;}
    public void setData_nascita(Date data_nascita){this.data_nascita = data_nascita;}

    public String getSesso(){return sesso;}
    public void setSesso(String sesso){this.sesso = sesso;}

    public Long getStipendio(){return stipendio;}
    public void setStipendio(Long stipendio){this.stipendio = stipendio;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
}
