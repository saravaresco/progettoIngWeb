package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.realizzaDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.realizza;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class RealizzaCookieImpl implements realizzaDAO {
    HttpServletRequest request;
    HttpServletResponse response;

    public RealizzaCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public realizza create(
            String cf_attore,
            String nome_spettacolo) {

        /*creo nuovo addettoGiostre*/
        realizza loggedRealizza = new realizza();
        /*setto i vari parametri in base a quello che ricevo*/
        loggedRealizza.setCf_attore(cf_attore);
        loggedRealizza.setNome_spettacolo(nome_spettacolo);

        /*creo cookie*/
        Cookie cookie;
        cookie = new Cookie("loggedRealizza", encode(loggedRealizza));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedRealizza;

    }


    @Override
    public void update(realizza loggedRealizza) {

        Cookie cookie;
        cookie = new Cookie("loggedRealizza", encode(loggedRealizza));
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    @Override
    public void delete(realizza loggedRealizza) {

        Cookie cookie;
        cookie = new Cookie("loggedRealizza", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }


    @Override
    public List<realizza> findByCFAttore(String cf_attore) {
        throw new UnsupportedOperationException("Not supported yet.");
    } /* sollevo un'eccezione perchè non lo posso fare dai cookie*/


    @Override
    public realizza findByNomeSpettacolo(String nome_spettacolo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(realizza loggedRealizza) {

        String encodedLoggedUser;
        encodedLoggedUser = loggedRealizza.getCf_attore() + "#" + loggedRealizza.getNome_spettacolo();
        return encodedLoggedUser;

    }

    private realizza decode(String encodedLoggedUser) {

        realizza loggedRealizza = new realizza();

        String[] values = encodedLoggedUser.split("#");

        loggedRealizza.setCf_attore((values[0]));
        loggedRealizza.setNome_spettacolo((values[2]));

        return loggedRealizza;

    }
}
