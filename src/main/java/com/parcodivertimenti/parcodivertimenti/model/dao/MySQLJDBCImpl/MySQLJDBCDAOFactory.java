package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;

import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.puntoRistoroDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.manutentoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.dipendenteDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.attrazioneDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.attoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.addettoRistoranteDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.addettoGiostreDAO;

public class MySQLJDBCDAOFactory extends DaoFactory {

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
    public addettoGiostreDAO getAddettoGiostreDAO() {
        return new AddettoGiostreDAOMySQLJDBCImpl(connection);
    }

    @Override
    public addettoRistoranteDAO getContactDAO() {
        return new AddettoRistoranteDAOMySQLJDBCImpl(connection);
    }
}