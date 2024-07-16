package com.parcodivertimenti.parcodivertimenti.controller;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;
import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;
import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;



public class ParkController extends HttpServlet {
    public ParkController() {
    }

    public static void homePage(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory sessionDAOFactory= null;
        Logger logger = LogService.getApplicationLogger();

        try{
            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            sessionDAOFactory.commitTransaction();

            request.setAttribute("viewUrl", "view");
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

