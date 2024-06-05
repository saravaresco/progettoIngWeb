package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.isw.es_07_rubrica.services.config.Configuration;

import com.isw.es_07_rubrica.model.dao.DAOFactory;
import com.isw.es_07_rubrica.model.dao.UserDAO;
import com.isw.es_07_rubrica.model.dao.ContactDAO;

public class MySQLJDBCDAOFactory extends DAOFactory {

    private Map factoryParameters;

    private Connection connection;

    public MySQLJDBCDAOFactory(Map factoryParameters) {
        this.factoryParameters=factoryParameters;
    }

    @Override
    public void beginTransaction() {

        try {
            Class.forName(Configuration.DATABASE_DRIVER);
            this.connection = DriverManager.getConnection(Configuration.DATABASE_URL);
            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void commitTransaction() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollbackTransaction() {

        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void closeTransaction() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMySQLJDBCImpl(connection);
    }

    @Override
    public ContactDAO getContactDAO() {
        return new ContactDAOMySQLJDBCImpl(connection);
    }
}