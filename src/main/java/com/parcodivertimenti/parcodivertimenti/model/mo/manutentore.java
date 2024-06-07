package com.parcodivertimenti.parcodivertimenti.model.mo;

public class manutentore {

    private String codice_fiscale;
    private Long numero_interventi;

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public Long getNumero_interventi(){return numero_interventi;}
    public void setNumero_interventi(Long numero_interventi){this.numero_interventi = numero_interventi;}
}
