package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl.VisitatoreCookieImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;
import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    /*protected void loginReg(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");


        if (action != null && action.equals("visitor")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Verifica le credenziali nel database
            boolean isAuthenticated = verifyVisitorCredentials(username, password);

            if (isAuthenticated) {
                // Crea e salva il visitatore nei cookie
                VisitatoreCookieImpl visitatoreCookie = new VisitatoreCookieImpl(request, response);
                visitatore loggedVisitatore = getVisitatoreByUsername(username);
                visitatoreCookie.create(
                        loggedVisitatore.getCodice_fiscale(),
                        loggedVisitatore.getNome(),
                        loggedVisitatore.getCognome(),
                        loggedVisitatore.getData_nascita(),
                        loggedVisitatore.getSesso(),
                        loggedVisitatore.getUsername(),
                        loggedVisitatore.getPassword()
                );
                response.sendRedirect("register.jsp"); // Reindirizza alla pagina personale del visitatore
            } else {
                response.sendRedirect("login.jsp"); // Reindirizza di nuovo alla pagina di login
            }
        }
        else if (action.equals("employee")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String category = request.getParameter("category");

            // Verifica le credenziali nel database per i dipendenti
            String redirectPage = verifyEmployeeCredentials(username, password, category);

            if (redirectPage != null) {
                response.sendRedirect(redirectPage); // Reindirizza alla pagina specifica del dipendente
            } else {
                response.sendRedirect("login.jsp"); // Reindirizza di nuovo alla pagina di login
            }
        }
        else {
            response.sendRedirect("login.jsp"); // Reindirizza di nuovo alla pagina di login
        }

    }

    // Verifica le credenziali del visitatore nel database
    private boolean verifyVisitorCredentials(String username, String password) {
        return verifyCredentials(username, password, "visitatore");
    }

    // Verifica le credenziali del dipendente nel database in base alla categoria
    private String verifyEmployeeCredentials(String category, String username, String password) {
        String tableName = "";
        String redirectPage = "";

        switch (category) {
            case "attore":
                tableName = "attore";
                redirectPage = "attore.jsp";
                break;
            case "manutentore":
                tableName = "manutentore";
                redirectPage = "/manutentore.jsp";
                break;
            case "addetto_giostre":
                tableName = "addetto_giostre";
                redirectPage = "giostre.jsp";
                break;
            case "addetto_ristorante":
                tableName = "addetto_ristorante";
                redirectPage = "ristorante.jsp";
                break;
            default:
                logger.warning("Categoria non valida: " + category);
                return null;
        }

        boolean isAuthenticated = verifyCredentials(username, password, tableName);
        return isAuthenticated ? redirectPage : null;
    }

    // Metodo generico per verificare le credenziali nel database
    private boolean verifyCredentials(String username, String password, String tableName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per verificare le credenziali nel database
            String query = "SELECT * FROM " + tableName + " WHERE USERNAME = ? AND PASSWORD = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Esegui la query
            rs = stmt.executeQuery();

            // Se trova almeno una riga, le credenziali sono valide
            if (rs.next()) {
                logger.info("Credenziali valide per l'utente: " + username);
                return true;
            } else {
                logger.warning("Credenziali non valide per l'utente: " + username);
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Chiudi le risorse
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Metodo per ottenere le informazioni del visitatore dal database
    private visitatore getVisitatoreByUsername(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        visitatore loggedVisitatore = null;

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per ottenere il visitatore dal database
            String query = "SELECT * FROM visitatore WHERE USERNAME = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            // Esegui la query
            rs = stmt.executeQuery();

            // Se trova almeno una riga, crea un oggetto visitatore
            if (rs.next()) {
                loggedVisitatore = new visitatore();
                loggedVisitatore.setCodice_fiscale(rs.getString("codice_fiscale"));
                loggedVisitatore.setNome(rs.getString("nome"));
                loggedVisitatore.setCognome(rs.getString("cognome"));
                loggedVisitatore.setData_nascita(rs.getString("data_nascita"));
                loggedVisitatore.setSesso(rs.getString("sesso"));
                loggedVisitatore.setUsername(rs.getString("username"));
                loggedVisitatore.setPassword(rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loggedVisitatore;
    }*/

    /*public static void view(HttpServletRequest request, HttpServletResponse response) {

        DaoFactory sessionDAOFactory= null;
        DaoFactory daoFactory = null;
        visitatore loggedUser;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DaoFactory.getDaoFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            visitatoreDAO sessionUserDAO = sessionDAOFactory.getVisitatoreDAO();
            loggedUser = sessionUserDAO.findLoggedUser();

            daoFactory = DaoFactory.getDaoFactory(Configuration.DAO_IMPL,null);
            daoFactory.beginTransaction();

            //commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "addressBookManagement/view");

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

    }*/

}
