package com.parcodivertimenti.parcodivertimenti.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Logger;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("existing")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Verifica le credenziali nel database
            boolean isAuthenticated = verifyVisitorCredentials(username, password);

            if (isAuthenticated) {
                response.sendRedirect("register.jsp"); // Reindirizza alla pagina personale del visitatore
            } else {
                response.sendRedirect("login.jsp"); // Reindirizza di nuovo alla pagina di login
            }
        }
    }

    // Verifica le credenziali del visitatore nel database
    private boolean verifyVisitorCredentials(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per verificare le credenziali nel database visitatore
            String query = "SELECT * FROM visitatore WHERE USERNAME = ? AND PASSWORD = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Esegui la query
            rs = stmt.executeQuery();


            // Se trova almeno una riga, le credenziali sono valide
            if (rs.next()) {
                logger.info("Credenziali valide per l'utente: " + username + password);
                return true;
            }else {
                logger.warning("Credenziali non valide per l'utente: " + username);
                return false;
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

        return false;
    }


}
