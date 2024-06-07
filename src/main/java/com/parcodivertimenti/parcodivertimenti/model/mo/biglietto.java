package com.parcodivertimenti.parcodivertimenti.model.mo;

import java.util.Date;

public class biglietto {

    private Long ID;
    private String codice_fiscale;
    private Long prezzo;
    private Date data_acquisto;
    private Long tipologia;

    public Long getID(){return ID;}
    public void setID(Long ID){this.ID = ID;}

    public String getCodice_fiscale(){return codice_fiscale;}
    public void setCodice_fiscale(String codice_fiscale){this.codice_fiscale = codice_fiscale;}

    public Long getPrezzo(){return prezzo;}
    public void setPrezzo(Long prezzo){this.prezzo = prezzo;}

    public Date getData_acquisto(){return data_acquisto;}
    public void setData_acquisto(Date data_acquisto){this.data_acquisto = data_acquisto;}

    public Long getTipologia(){return tipologia;}
    public void setTipologia(Long tipologia){this.tipologia = tipologia;}

}
