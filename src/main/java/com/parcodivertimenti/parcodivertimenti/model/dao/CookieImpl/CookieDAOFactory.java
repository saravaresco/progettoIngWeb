package com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.*;
import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class CookieDAOFactory extends DaoFactory {

    private Map factoryParameters;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieDAOFactory(Map factoryParameters) {
        this.factoryParameters=factoryParameters;
    }

    @Override
    public void beginTransaction() {

        try {
            this.request=(HttpServletRequest) factoryParameters.get("request");
            this.response=(HttpServletResponse) factoryParameters.get("response");;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void commitTransaction() {}

    @Override
    public void rollbackTransaction() {}

    @Override
    public void closeTransaction() {}

    @Override
    public addettoGiostreDAO getAddettoGiostreDAO() {
        return new AddettoGiostreCookieImpl(request,response);
    } /*uso questa sintassi se mi serve il cookie */

    @Override
    public addettoRistoranteDAO getAddettoRistoranteDAO() {
        return new AddettoRistoranteCookieImpl(request,response);
    }

    @Override
    public attoreDAO getAttoreDAO() {
        return new AttoreCookieImpl(request,response);
    }

    @Override
    public attrazioneDAO getAttrazioneDAO() {
        return new AttrazioneCookieImpl(request,response);
    }

    @Override
    public bigliettoDAO getBigliettoDAO() {
        return new BigliettoCookieImpl(request,response);
    }

    @Override
    public dipendenteDAO getDipendenteDAO() {
        return new DipendenteCookieImpl(request,response);
    }

    @Override
    public manutentoreDAO getManutentoreDAO() {
        return new ManutentoreCookieImpl(request,response);
    }

    @Override
    public puntoRistoroDAO getPuntoRistoroDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public spettacoloDAO getSpettacoloDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public visitatoreDAO getVisitatoreDAO() {
        return new VisitatoreCookieImpl(request,response);
    }

    /*@Override
    public ContactDAO getContactDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/ /*quando non serve il cookie*/


}
