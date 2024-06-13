package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.addettoGiostreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;

public class AddettoGiostreCookieImpl implements addettoGiostreDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public AddettoGiostreCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public addettoGiostre create(
            String codice_fiscale,
            String mansione,
            Long codice_giostra,
            String username,
            String password,
            String languageCode) {

        addettoGiostre loggedAddettoGiostre = new addettoGiostre();
        loggedAddettoGiostre.setCodice_fiscale(codice_fiscale);
        loggedAddettoGiostre.setMansione(mansione);
        loggedAddettoGiostre.setCodice_giostra(codice_giostra);

        Cookie cookie;
        cookie = new Cookie("loggedAddettoGiostre", encode(loggedAddettoGiostre));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAddettoGiostre;

    }


    @Override
    public void update(addettoGiostre loggedAddettoGiostre) {

        Cookie cookie;
        cookie = new Cookie("loggedAddettoGiostre", encode(loggedAddettoGiostre));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(addettoGiostre loggedAddettoGiostre) {

        Cookie cookie;
        cookie = new Cookie("loggedAddettoGiostre", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public addettoGiostre findLoggedUser() {

        Cookie[] cookies = request.getCookies();
        addettoGiostre loggedAddettoGiostre = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedAddettoGiostre == null; i++) {
                if (cookies[i].getName().equals("loggedAddettoGiostre")) {
                    loggedAddettoGiostre = decode(cookies[i].getValue());
                }
            }
        }

        return loggedAddettoGiostre;

    }

    @Override
    public addettoGiostre findByUserCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public addettoGiostre findByMansione(String mansione) {
        return null;
    }

    @Override
    public addettoGiostre findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(addettoGiostre loggedAddettoGiostre) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedAddettoGiostre.getCodice_fiscale() + "#" + loggedAddettoGiostre.getMansione() + "#" + loggedAddettoGiostre.getCodice_giostra();
        return encodedLoggedUser;

    }

    private addettoGiostre decode(String encodedLoggedUser) {

        addettoGiostre loggeAddettoGiostre = new addettoGiostre();

        String[] values = encodedLoggedUser.split("#");

        loggeAddettoGiostre.setCodice_fiscale((values[0]));
        loggeAddettoGiostre.setMansione(values[1]);
        loggeAddettoGiostre.setCodice_giostra(Long.parseLong(values[2]));

        return loggeAddettoGiostre;

    }
}
