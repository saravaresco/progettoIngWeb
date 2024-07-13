package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.dipendenteDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;

import java.util.Date;

public class DipendenteCookieImpl implements dipendenteDAO{

    HttpServletRequest request;
    HttpServletResponse response;

    public DipendenteCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public dipendente create(
            String codice_fiscale,
            String nome,
            String cognome,
            String data_nascita,
            String sesso,
            Long stipendio,
            String username,
            String password) {

        dipendente loggedDipendente = new dipendente();
        loggedDipendente.setCodice_fiscale(codice_fiscale);
        loggedDipendente.setNome(nome);
        loggedDipendente.setCognome(cognome);
        loggedDipendente.setData_nascita(data_nascita);
        loggedDipendente.setSesso(sesso);
        loggedDipendente.setStipendio(stipendio);

        Cookie cookie;
        cookie = new Cookie("loggedDipendente", encode(loggedDipendente));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedDipendente;

    }


    @Override
    public void update(dipendente loggedDipendente) {

        Cookie cookie;
        cookie = new Cookie("loggedDipendente", encode(loggedDipendente));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(dipendente loggedDipendente) {

        Cookie cookie;
        cookie = new Cookie("loggedDipendente", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public dipendente findLoggedUser(String username, String password, String categoria) {

        Cookie[] cookies = request.getCookies();
        dipendente loggedDipendente = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedDipendente == null; i++) {
                if (cookies[i].getName().equals("loggedDipendente")) {
                    loggedDipendente = decode(cookies[i].getValue());
                }
            }
        }

        return loggedDipendente;

    }

    @Override
    public dipendente findByCF(String codice_fiscale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dipendente findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dipendente findByCognome(String cognome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dipendente findByDataNascita(String data_nascita) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dipendente findByStipendio(Long stipendio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dipendente findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(dipendente loggedDipendente) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedDipendente.getCodice_fiscale() + "#" + loggedDipendente.getNome() + "#" + loggedDipendente.getCognome() + "#" + loggedDipendente.getData_nascita() + "#" + loggedDipendente.getSesso() + "#" + loggedDipendente.getStipendio();
        return encodedLoggedUser;

    }

    private dipendente decode(String encodedLoggedUser) {

        dipendente loggedDipendente = new dipendente();

        String[] values = encodedLoggedUser.split("#");

        loggedDipendente.setCodice_fiscale((values[0]));
        loggedDipendente.setNome(values[1]);
        loggedDipendente.setCognome(values[2]);
        loggedDipendente.setData_nascita((values[3]));
        loggedDipendente.setSesso(values[4]);
        loggedDipendente.setStipendio(Long.parseLong(values[5]));

        return loggedDipendente;

    }

}
