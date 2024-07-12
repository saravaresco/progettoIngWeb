package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.util.Date;

public class VisitatoreCookieImpl implements visitatoreDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public VisitatoreCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public visitatore create(
            String codice_fiscale,
            String nome,
            String cognome,
            String data_nascita,
            String sesso,
            String username,
            String password) {

        visitatore loggedVisitatore = new visitatore();
        loggedVisitatore.setCodice_fiscale(codice_fiscale);
        loggedVisitatore.setNome(nome);
        loggedVisitatore.setCognome(cognome);
        loggedVisitatore.setData_nascita(data_nascita);
        loggedVisitatore.setSesso(sesso);


        Cookie cookie;
        cookie = new Cookie("loggedVisitatore", encode(loggedVisitatore));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedVisitatore;

    }


    @Override
    public void update(visitatore loggedVisitatore) {

        Cookie cookie;
        cookie = new Cookie("loggedVisitatore", encode(loggedVisitatore));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(visitatore loggedVisitatore) {

        Cookie cookie;
        cookie = new Cookie("loggedVisitatore", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public visitatore findLoggedUser(String username, String password) {

        Cookie[] cookies = request.getCookies();
        visitatore loggedvisitatore = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedvisitatore == null; i++) {
                if (cookies[i].getName().equals("loggedDipendente")) {
                    loggedvisitatore = decode(cookies[i].getValue());
                }
            }
        }

        return loggedvisitatore;

    }

    @Override
    public visitatore findByCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public visitatore findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public visitatore findByCognome(String cognome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public visitatore findByDataNascita(String data_nascita) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public visitatore findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(visitatore loggedDipendente) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedDipendente.getCodice_fiscale() + "#" + loggedDipendente.getNome() + "#" + loggedDipendente.getCognome() + "#" + loggedDipendente.getData_nascita() + "#" + loggedDipendente.getSesso();
        return encodedLoggedUser;

    }

    private visitatore decode(String encodedLoggedUser) {

        visitatore loggedVisitatore = new visitatore();

        String[] values = encodedLoggedUser.split("#");

        loggedVisitatore.setCodice_fiscale((values[0]));
        loggedVisitatore.setNome(values[1]);
        loggedVisitatore.setCognome(values[2]);
        loggedVisitatore.setData_nascita(values[3]);
        loggedVisitatore.setSesso(values[4]);

        return loggedVisitatore;

    }

}
