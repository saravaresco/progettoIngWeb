package com.parcodivertimenti.parcodivertimenti.model.dao;

import java.util.Map;

public abstract class DaoFactory {

    // List of DAO types supported by the factory
    public static final String MYSQLJDBCIMPL = "MySQLJDBCImpl";
    public static final String COOKIEIMPL= "CookieImpl";

    public abstract void beginTransaction();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void closeTransaction();

    public abstract UserDAO getUserDAO();

    public abstract ContactDAO getContactDAO();

    public static DAOFactory getDAOFactory(String whichFactory, Map factoryParameters) {

        if (whichFactory.equals(MYSQLJDBCIMPL)) {
            return new MySQLJDBCDAOFactory(factoryParameters);
        } else if (whichFactory.equals(COOKIEIMPL)) {
            return new CookieDAOFactory(factoryParameters);
        } else {
            return null;
        }
    }
}
