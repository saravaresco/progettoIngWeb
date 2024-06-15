package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.attoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.attore;
public class AttoreCookieImpl implements attoreDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public AttoreCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public attore create(
            String codice_fiscale,
            String ruolo,
            String username,
            String password) {

        attore loggedAttore = new attore();
        loggedAttore.setCodice_fiscale(codice_fiscale);
        loggedAttore.setRuolo(ruolo);


        Cookie cookie;
        cookie = new Cookie("loggedAttore", encode(loggedAttore));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAttore;

    }


    @Override
    public void update(attore loggedAttore) {

        Cookie cookie;
        cookie = new Cookie("loggedAttore", encode(loggedAttore));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(attore loggedAttore) {

        Cookie cookie;
        cookie = new Cookie("loggedAttore", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public attore findLoggedUser() {

        Cookie[] cookies = request.getCookies();
        attore loggedAttore = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedAttore == null; i++) {
                if (cookies[i].getName().equals("loggedAddettoRistorante")) {
                    loggedAttore = decode(cookies[i].getValue());
                }
            }
        }

        return loggedAttore;

    }

    @Override
    public attore findByUserCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public attore findByRuolo(String ruolo) {
        return null;
    }

    @Override
    public attore findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(attore loggedAttore) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedAttore.getCodice_fiscale() + "#" + loggedAttore.getRuolo();
        return encodedLoggedUser;

    }

    private attore decode(String encodedLoggedUser) {

        attore loggeAttore = new attore();

        String[] values = encodedLoggedUser.split("#");

        loggeAttore.setCodice_fiscale((values[0]));
        loggeAttore.setRuolo(values[1]);

        return loggeAttore;

    }
}
