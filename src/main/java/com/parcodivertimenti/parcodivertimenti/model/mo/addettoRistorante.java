package com.parcodivertimenti.parcodivertimenti.model.mo;

public class addettoRistorante {

    private String codice_fiscale;
    private String posizione;
    private Long ID_punto_ristoro;
    private String username;
    private String password;

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public String getPosizione(){return posizione;}
    public void setPosizione(String posizione){this.posizione = posizione;}

    public Long getID_punto_ristoro(){return ID_punto_ristoro;}
    public void setID_punto_ristoro(Long ID_punto_ristoro){this.ID_punto_ristoro = ID_punto_ristoro;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
}
