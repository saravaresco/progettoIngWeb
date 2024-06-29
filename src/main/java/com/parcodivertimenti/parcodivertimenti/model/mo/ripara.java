package com.parcodivertimenti.parcodivertimenti.model.mo;

public class ripara {
    private String cf_manutentore;
    private Long codice_attrazione;
    private String descrizione;

    public String getCf_manutentore(){return cf_manutentore;}
    public void setCf_manutentore(String cf_manutentore){this.cf_manutentore = cf_manutentore;}

    public Long getCodice_attrazione(){return codice_attrazione;}
    public void setCodice_attrazione(Long codice_attrazione){this.codice_attrazione = codice_attrazione;}

    public String getDescrizione(){return descrizione;}
    public void setDescrizione(String descrizione){this.descrizione = descrizione;}
}
