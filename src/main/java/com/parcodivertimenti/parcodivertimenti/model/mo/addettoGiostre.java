package com.parcodivertimenti.parcodivertimenti.model.mo;

public class addettoGiostre {

    private String codice_fiscale;
    private String mansione;
    private Long codice_giostra;
    private String username;
    private String password;


    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public String getMansione(){return mansione;}
    public void setMansione(String mansione){this.mansione = mansione;}

    public Long getCodice_giostra(){return codice_giostra;}
    public void setCodice_giostra(Long codice_giostra) {
        this.codice_giostra = codice_giostra;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}



}
