package com.parcodivertimenti.parcodivertimenti.model.mo;

public class manutentore {

    private String codice_fiscale;
    private Long numero_interventi;
    private String username;
    private String password;

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public Long getNumero_interventi(){return numero_interventi;}
    public void setNumero_interventi(Long numero_interventi){this.numero_interventi = numero_interventi;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
}
