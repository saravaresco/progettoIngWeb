package com.parcodivertimenti.parcodivertimenti.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.VisitatoreDAOMySQLJDBCImpl;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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

    public LoginVisitatore() {
    }

    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sarA2002";

    /*public static void login(HttpServletRequest request, HttpServletResponse response) {
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
    }*/

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            visitatoreDAO visitatoreDAO = new VisitatoreDAOMySQLJDBCImpl(conn);
            visitatore visitatore = visitatoreDAO.findLoggedUser(username, password);

            if (visitatore != null) {
                // Credenziali corrette, reindirizza alla pagina areaPersonale.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            } else {
                // Credenziali errate, reindirizza alla pagina error.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Errore di connessione al database", e);
        }


    }
}
