package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;

import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.riparaDAO;
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
        return (addettoGiostreDAO) new AddettoGiostreDAOMySQLJDBCImpl(connection);
    }



    @Override
    public addettoRistoranteDAO getAddettoRistoranteDAO() {
        return (addettoRistoranteDAO) new AddettoRistoranteDAOMySQLJDBCImpl(connection);
    }

    @Override
    public attoreDAO getAttoreDAO() {
        return new AttoreDAOMySQLJDBCImpl(connection);
    }

    @Override
    public attrazioneDAO getAttrazioneDAO() {
        return new AttrazioneDAOMySQLJDBCImpl(connection);
    }

    @Override
    public bigliettoDAO getBigliettoDAO() {
        return new BigliettoDAOMySQLJDBCImpl(connection);
    }

    @Override
    public dipendenteDAO getDipendenteDAO() {
        return new DipendenteDAOMySQLJDBCImpl(connection);
    }

    @Override
    public manutentoreDAO getManutentoreDAO() {
        return new ManutentoreDAOMySQLJDBCImpl(connection);
    }

    @Override
    public puntoRistoroDAO getPuntoRistoroDAO() {
        return new PuntoRistoroDAOMySQLJDBCImpl(connection);
    }

    @Override
    public riparaDAO getRiparaDAO() { return new RiparaDAOMySQLJDBCImpl(connection); }

    @Override
    public spettacoloDAO getSpettacoloDAO() {
        return new SpettacoloDAOMySQLJDBCImpl(connection);
    }

    @Override
    public visitatoreDAO getVisitatoreDAO() {
        return new VisitatoreDAOMySQLJDBCImpl(connection);
    }
}