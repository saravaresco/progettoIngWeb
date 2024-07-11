package com.parcodivertimenti.parcodivertimenti.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl.VisitatoreCookieImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;
import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "login2", urlPatterns = {"/login2"})
public class LoginVisitatore {

    private LoginVisitatore() {
    }

    public static void login(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory sessionDAOFactory = null;
        DaoFactory daoFactory = null;
        visitatore loggedVisitatore;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {
            Map<String, Object> sessionFactoryParameters = new HashMap<>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            visitatoreDAO sessionVisitatoreDAO = sessionDAOFactory.getVisitatoreDAO();
            loggedVisitatore = sessionVisitatoreDAO.findLoggedUser();

            daoFactory = DaoFactory.getDaoFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();

            String action = request.getParameter("action");
            if ("visitor".equals(action)) {
                authenticateVisitor(request, response, daoFactory, sessionDAOFactory);

            } else {
                response.sendRedirect("login.jsp");
            }

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedVisitatore != null);
            request.setAttribute("loggedVisitatore", loggedVisitatore);
            request.setAttribute("applicationMessage", applicationMessage);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
                logger.log(Level.SEVERE, "Transaction Rollback Error", t);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
                logger.log(Level.SEVERE, "Transaction Close Error", t);
            }
        }
    }

    private static void authenticateVisitor(HttpServletRequest request, HttpServletResponse response, DaoFactory daoFactory, DaoFactory sessionDAOFactory) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        visitatoreDAO visitatoreDAO = daoFactory.getVisitatoreDAO();
        visitatore loggedVisitatore = visitatoreDAO.findByUsername(username);

        if (loggedVisitatore != null) {
            VisitatoreCookieImpl visitatoreCookie = new VisitatoreCookieImpl(request, response);
            visitatoreCookie.create(
                    loggedVisitatore.getCodice_fiscale(),
                    loggedVisitatore.getNome(),
                    loggedVisitatore.getCognome(),
                    loggedVisitatore.getData_nascita(),
                    loggedVisitatore.getSesso(),
                    loggedVisitatore.getUsername(),
                    loggedVisitatore.getPassword()
            );
            response.sendRedirect("register.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }




}
