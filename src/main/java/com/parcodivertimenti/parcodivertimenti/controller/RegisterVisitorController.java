package com.parcodivertimenti.parcodivertimenti.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register-visitor")
public class RegisterVisitorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String codiceFiscale = request.getParameter("codiceFiscale");
        String dataNascita = request.getParameter("dataNascita");
        String sesso = request.getParameter("sesso");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Inserisci i dati nel database
        boolean success = registerVisitor(nome, cognome, codiceFiscale, dataNascita, sesso, username, password);

        if (success) {
            response.sendRedirect("login.jsp"); // Reindirizza alla pagina di login dopo la registrazione
        } else {
            response.sendRedirect("newVisitor.jsp"); // Reindirizza di nuovo alla pagina di registrazione in caso di errore
        }
    }

    // Metodo per registrare un nuovo visitatore nel database
    private boolean registerVisitor(String nome, String cognome, String codiceFiscale,
                                    String dataNascita, String sesso, String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per inserire i dati nel database visitatore
            String query = "INSERT INTO visitatore (CODICE_FISCALE, NOME, COGNOME,  DATA_NASCITA, SESSO, USERNAME, PASSWORD) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);
            stmt.setString(2, nome);
            stmt.setString(3, cognome);
            stmt.setString(4, dataNascita);
            stmt.setString(5, sesso);
            stmt.setString(6, username);
            stmt.setString(7, password);

            // Esegui la query di inserimento
            int rowsInserted = stmt.executeUpdate();

            // Verifica se l'inserimento è riuscito
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
