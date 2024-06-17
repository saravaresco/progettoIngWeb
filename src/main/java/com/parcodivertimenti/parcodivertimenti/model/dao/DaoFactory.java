package com.parcodivertimenti.parcodivertimenti.model.dao;

import com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl.CookieDAOFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.MySQLJDBCDAOFactory;

import java.util.Map;

public abstract class DaoFactory {

    // List of DAO types supported by the factory
    public static final String MYSQLJDBCIMPL = "MySQLJDBCImpl";
    public static final String COOKIEIMPL= "CookieImpl";

    public abstract void beginTransaction();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void closeTransaction();

    public abstract addettoGiostreDAO getAddettoGiostreDAO();

    public abstract addettoRistoranteDAO getAddettoRistoranteDAO();

    public abstract attoreDAO getAttoreDAO();

    public abstract attrazioneDAO getAttrazioneDAO();

    public abstract bigliettoDAO getBigliettoDAO();

    public abstract dipendenteDAO getDipendenteDAO();

    public abstract manutentoreDAO getManutentoreDAO();

    public abstract puntoRistoroDAO getPuntoRistoroDAO();

    public abstract spettacoloDAO getSpettacoloDAO();

    public abstract visitatoreDAO getVisitatoreDAO();

    public static MySQLJDBCDAOFactory getDaoFactory(String whichFactory, Map factoryParameters) {

        if (whichFactory.equals(MYSQLJDBCIMPL)) {
            return new MySQLJDBCDAOFactory(factoryParameters);
        } else if (whichFactory.equals(COOKIEIMPL)) {
            return new MySQLJDBCDAOFactory(factoryParameters);
        } else {
            return null;
        }
    }
}
