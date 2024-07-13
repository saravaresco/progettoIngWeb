package com.parcodivertimenti.parcodivertimenti.model.mo;

import java.util.Date;

public class biglietto {

    private Long ID;
    private String codice_fiscale;
    private Long prezzo;
    private String data_acquisto;
    private String tipologia1;
    private String tipologia2;
    private String mail;

    public biglietto() {
    }

    public Long getID(){return ID;}
    public void setID(Long ID){this.ID = ID;}

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public Long getPrezzo(){return prezzo;}
    public void setPrezzo(Long prezzo){this.prezzo = prezzo;}

    public String getData_acquisto(){return data_acquisto;}
    public void setData_acquisto(String data_acquisto){this.data_acquisto = data_acquisto;}

    public String getTipologia1(){return tipologia1;}
    public void setTipologia1(String tipologia){this.tipologia1 = tipologia;}
    public String getTipologia2(){return tipologia2;}
    public void setTipologia2(String tipologia){this.tipologia2 = tipologia;}

    public String getMail(){return mail;}
    public void setMail(String mail){this.mail = mail;}

}
