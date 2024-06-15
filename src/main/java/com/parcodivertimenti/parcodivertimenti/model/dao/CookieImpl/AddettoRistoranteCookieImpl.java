package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.addettoRistoranteDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;

public class AddettoRistoranteCookieImpl implements addettoRistoranteDAO {

    HttpServletRequest request;
    HttpServletResponse response;

    public AddettoRistoranteCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public addettoRistorante create(
            String codice_fiscale,
            String posizione,
            Long ID_punto_ristoro,
            String username,
            String password) {

        addettoRistorante loggedAddettoRistorante = new addettoRistorante();
        loggedAddettoRistorante.setCodice_fiscale(codice_fiscale);
        loggedAddettoRistorante.setPosizione(posizione);
        loggedAddettoRistorante.setID_punto_ristoro(ID_punto_ristoro);

        Cookie cookie;
        cookie = new Cookie("loggedAddettoRistorante", encode(loggedAddettoRistorante));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAddettoRistorante;

    }


    @Override
    public void update(addettoRistorante loggedAddettoRistorante) {

        Cookie cookie;
        cookie = new Cookie("loggedAddettoRistorante", encode(loggedAddettoRistorante));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(addettoRistorante loggedAddettoRistorante) {

        Cookie cookie;
        cookie = new Cookie("loggedAddettoRistorante", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public addettoRistorante findLoggedUser() {

        Cookie[] cookies = request.getCookies();
        addettoRistorante loggedAddettoRistorante = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedAddettoRistorante == null; i++) {
                if (cookies[i].getName().equals("loggedAddettoRistorante")) {
                    loggedAddettoRistorante = decode(cookies[i].getValue());
                }
            }
        }

        return loggedAddettoRistorante;

    }

    @Override
    public addettoRistorante findByUserCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public addettoRistorante findByPuntoRistoro(Long ID_punto_ristoro) {
        return null;
    }

    @Override
    public addettoRistorante findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(addettoRistorante loggedAddettoRistorante) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedAddettoRistorante.getCodice_fiscale() + "#" + loggedAddettoRistorante.getPosizione() + "#" + loggedAddettoRistorante.getID_punto_ristoro();
        return encodedLoggedUser;

    }

    private addettoRistorante decode(String encodedLoggedUser) {

        addettoRistorante loggeAddettoRistorante = new addettoRistorante();

        String[] values = encodedLoggedUser.split("#");

        loggeAddettoRistorante.setCodice_fiscale((values[0]));
        loggeAddettoRistorante.setPosizione(values[1]);
        loggeAddettoRistorante.setID_punto_ristoro(Long.parseLong(values[2]));

        return loggeAddettoRistorante;

    }
}
