package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.util.Date;

public class BigliettoCookieImpl implements bigliettoDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public BigliettoCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public biglietto create(
            Long ID,
            String codice_fiscale,
            Long prezzo,
            Date data_acquisto,
            String tipologia) {

        biglietto loggedBiglietto = new biglietto();
        loggedBiglietto.setID(ID);
        loggedBiglietto.setCodice_fiscale(codice_fiscale);
        loggedBiglietto.setPrezzo(prezzo);
        loggedBiglietto.setData_acquisto(data_acquisto);
        loggedBiglietto.setTipologia(tipologia);


        Cookie cookie;
        cookie = new Cookie("loggedBiglietto", encode(loggedBiglietto));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedBiglietto;

    }


    @Override
    public void update(biglietto loggedBiglietto) {

        Cookie cookie;
        cookie = new Cookie("loggedBiglietto", encode(loggedBiglietto));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(biglietto loggedBiglietto) {

        Cookie cookie;
        cookie = new Cookie("loggedBiglietto", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public biglietto findById(Long ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public biglietto findByCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public biglietto findByData(Date data_acquisto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public biglietto findByTipologia(String tipologia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    private String encode(biglietto loggedBiglietto) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedBiglietto.getID() + "#" + loggedBiglietto.getCodice_fiscale() + "#" + loggedBiglietto.getPrezzo() + "#" + loggedBiglietto.getData_acquisto() + "#" + loggedBiglietto.getTipologia();
        return encodedLoggedUser;

    }


}