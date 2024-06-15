package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.attrazioneDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;

import java.util.Date;

public class AttrazioneCookieImpl implements attrazioneDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public AttrazioneCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public attrazione create(
            Long codice,
            String nome,
            String tipologia,
            Date data_ultima_manutenzione) {

        attrazione loggedAttrazione = new attrazione();
        loggedAttrazione.setCodice(codice);
        loggedAttrazione.setNome(nome);
        loggedAttrazione.setTipologia(tipologia);
        loggedAttrazione.setData_ultima_manutenzione(data_ultima_manutenzione);


        Cookie cookie;
        cookie = new Cookie("loggedAttrazione", encode(loggedAttrazione));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAttrazione;

    }


    @Override
    public void update(attrazione loggedAttrazione) {

        Cookie cookie;
        cookie = new Cookie("loggedAttrazione", encode(loggedAttrazione));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(attrazione loggedAttrazione) {

        Cookie cookie;
        cookie = new Cookie("loggedAttrazione", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public attrazione findByCodice(Long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public attrazione findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public attrazione findByTipologia(String tipologia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public attrazione findByDateM(Date data_ultima_manutenzione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    private String encode(attrazione loggedAttrazione) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedAttrazione.getCodice() + "#" + loggedAttrazione.getNome() + "#" + loggedAttrazione.getTipologia() + "#" + loggedAttrazione.getData_ultima_manutenzione();
        return encodedLoggedUser;

    }

}
