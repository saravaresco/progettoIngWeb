package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.riparaDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RiparaCookieImpl implements riparaDAO {
    HttpServletRequest request;
    HttpServletResponse response;

    public RiparaCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public ripara create(
            String cf_manutentore,
            Long codice_attrazione,
            String descrizione) {

        /*creo nuovo addettoGiostre*/
        ripara loggedRipara = new ripara();
        /*setto i vari parametri in base a quello che ricevo*/
        loggedRipara.setCf_manutentore(cf_manutentore);
        loggedRipara.setCodice_attrazione(codice_attrazione);

        /*creo cookie*/
        Cookie cookie;
        cookie = new Cookie("loggedRipara", encode(loggedRipara));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedRipara;

    }


    @Override
    public void update(ripara loggedRipara) {

        Cookie cookie;
        cookie = new Cookie("loggedRipara", encode(loggedRipara));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(ripara loggedRipara) {

        Cookie cookie;
        cookie = new Cookie("loggedRipara", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }


    @Override
    public ripara findByCFManutentore(String cf_manutentore) {
        throw new UnsupportedOperationException("Not supported yet.");
    } /* sollevo un'eccezione perchè non lo posso fare dai cookie*/


    @Override
    public ripara findByCodiceAttrazione(Long codice_attrazione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(ripara loggedRipara) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedRipara.getCf_manutentore() + "#" + loggedRipara.getCodice_attrazione() + "#" + loggedRipara.getDescrizione();
        return encodedLoggedUser;

    }

    private ripara decode(String encodedLoggedUser) {

        ripara loggeAddettoGiostre = new ripara();

        String[] values = encodedLoggedUser.split("#");

        loggeAddettoGiostre.setCf_manutentore((values[0]));
        loggeAddettoGiostre.setCodice_attrazione(Long.parseLong(values[2]));

        return loggeAddettoGiostre;

    }
}
