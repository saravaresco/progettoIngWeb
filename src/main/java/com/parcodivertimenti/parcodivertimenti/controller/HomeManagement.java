package com.parcodivertimenti.parcodivertimenti.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;
import com.parcodivertimenti.parcodivertimenti.model.mo.attore;
import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;
import com.parcodivertimenti.parcodivertimenti.model.mo.manutentore;
import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.addettoGiostreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.addettoRistoranteDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.attoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.attrazioneDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.dipendenteDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.manutentoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.puntoRistoroDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;

public class HomeManagement {

    private HomeManagement() {
    }

    public static void view(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory sessionDAOFactory= null;
        addettoGiostre loggedAddettoGiostre;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            addettoGiostreDAO sessionAddettoGiostreDAO = sessionDAOFactory.getAddettoGiostreDAO();
            loggedAddettoGiostre = sessionAddettoGiostreDAO.findLoggedUser();

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedAddettoGiostre!=null);
            request.setAttribute("loggedUser", loggedAddettoGiostre);
            request.setAttribute("viewUrl", "homeManagement/view");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    public static void logon(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory sessionDAOFactory= null;
        DaoFactory daoFactory = null;
        addettoGiostre loggedAddettoGiostre;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            addettoGiostreDAO sessionAddettoGiostreDAO = sessionDAOFactory.getAddettoGiostreDAO();
            loggedAddettoGiostre = sessionAddettoGiostreDAO.findLoggedUser();

            daoFactory = DaoFactory.getDaoFactory(Configuration.DAO_IMPL,null);
            daoFactory.beginTransaction();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            addettoGiostreDAO userDAO = daoFactory.getAddettoGiostreDAO();
            addettoGiostre addettoGiostre = userDAO.findByUsername(username);

            if (addettoGiostre == null || !addettoGiostre.getPassword().equals(password)) {
                sessionAddettoGiostreDAO.delete(null);
                applicationMessage = "Username e password errati!";
                loggedAddettoGiostre=null;
            } else {
                loggedAddettoGiostre = sessionAddettoGiostreDAO.create(addettoGiostre.getCodice_fiscale(), null,null, addettoGiostre.getUsername(), addettoGiostre.getPassword());
            }

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedAddettoGiostre!=null);
            request.setAttribute("loggedUser", loggedAddettoGiostre);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "homeManagement/view");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory sessionDAOFactory= null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            addettoGiostreDAO sessionAddettoGiostreDAO = sessionDAOFactory.getAddettoGiostreDAO();
            sessionAddettoGiostreDAO.delete(null);

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",false);
            request.setAttribute("loggedUser", null);
            request.setAttribute("viewUrl", "homeManagement/view");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }
    }

}


