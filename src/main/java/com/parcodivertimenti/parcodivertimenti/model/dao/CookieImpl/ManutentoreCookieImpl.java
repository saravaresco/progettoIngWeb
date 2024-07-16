package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.manutentoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.manutentore;

import java.util.Date;
public class ManutentoreCookieImpl implements manutentoreDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public ManutentoreCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public manutentore create(
            String codice_fiscale,
            Long numero_interventi,
            String username,
            String password) {

        manutentore loggedManutentore = new manutentore();
        loggedManutentore.setCodice_fiscale(codice_fiscale);
        loggedManutentore.setNumero_interventi(numero_interventi);


        Cookie cookie;
        cookie = new Cookie("loggedManutentore", encode(loggedManutentore));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedManutentore;

    }


    @Override
    public void update(manutentore loggedManutentore) {

        Cookie cookie;
        cookie = new Cookie("loggedManutentore", encode(loggedManutentore));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(manutentore loggedManutentore) {

        Cookie cookie;
        cookie = new Cookie("loggedManutentore", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public manutentore findLoggedUser() {

        Cookie[] cookies = request.getCookies();
        manutentore loggedManutentore = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedManutentore == null; i++) {
                if (cookies[i].getName().equals("loggedManutentore")) {
                    loggedManutentore = decode(cookies[i].getValue());
                }
            }
        }

        return loggedManutentore;

    }

    @Override
    public manutentore findByCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public manutentore findByNumeroInt(Long numero_interventi) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public manutentore findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(manutentore loggedManutentore) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedManutentore.getCodice_fiscale() + "#" + loggedManutentore.getNumero_interventi();
        return encodedLoggedUser;

    }

    private manutentore decode(String encodedLoggedUser) {

        manutentore loggedManutentore = new manutentore();

        String[] values = encodedLoggedUser.split("#");

        loggedManutentore.setCodice_fiscale((values[0]));
        loggedManutentore.setNumero_interventi(Long.parseLong(values[1]));

        return loggedManutentore;

    }

}
