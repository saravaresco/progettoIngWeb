package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.BigliettoDAOMySQLJDBCImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register-visitor")
public class RegisterVisitorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codiceFiscale = request.getParameter("codiceFiscale");
        if (codiceFiscale != null && !codiceFiscale.isEmpty()) {
            List<biglietto> userTickets = getUserTickets(codiceFiscale);
            request.setAttribute("userTickets", userTickets);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

        // Inserisci i dati nel database
       /* boolean success = registerVisitor(nome, cognome, codiceFiscale, dataNascita, sesso, username, password);

        if (success) {
            // Salva il codice fiscale nella sessione
            request.getSession().setAttribute("codiceFiscale", codiceFiscale);
            response.sendRedirect("confermaIscrizione.jsp"); // Reindirizza alla pagina persona la registrazione
        } else {
            response.sendRedirect("newVisitor.jsp"); // Reindirizza di nuovo alla pagina di registrazione in caso di errore
        }
    }*/

    // Metodo per registrare un nuovo visitatore nel database
    /*private boolean registerVisitor(String nome, String cognome, String codiceFiscale,
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
    }*/

    // Metodo per ottenere i biglietti acquistati da un utente
    /*public List<biglietto> getUserTickets(String codiceFiscale) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<biglietto> tickets = new ArrayList<>();

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per ottenere i biglietti acquistati dall'utente
            String query = "SELECT * FROM biglietto WHERE CODICE_FISCALE_V = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, codiceFiscale);

            // Esegui la query
            rs = stmt.executeQuery();

            // Processa i risultati
            while (rs.next()) {
                biglietto ticket = new biglietto();
                ticket.setID(rs.getLong("ID"));
                ticket.setData_acquisto(rs.getDate("DATA_ACQUISTO"));
                ticket.setTipologia1(rs.getString("TIPOLOGIA1"));
                ticket.setTipologia2(rs.getString("TIPOLOGIA2"));
                ticket.setPrezzo(rs.getLong("PREZZO"));
                tickets.add(ticket);
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

        return tickets;
    }*/

    public List<biglietto> getUserTickets(String codiceFiscale) throws ServletException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            bigliettoDAO bigliettoDAO = new BigliettoDAOMySQLJDBCImpl(conn);
            return (List<biglietto>) bigliettoDAO.findByCF(codiceFiscale);
        } catch (SQLException e) {
            throw new ServletException("Errore di connessione al database", e);
        }
    }

    public void getTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceFiscale = request.getParameter("codiceFiscale");
        if (codiceFiscale != null && !codiceFiscale.isEmpty()) {
            List<biglietto> userTickets = getUserTickets(codiceFiscale);
            request.setAttribute("userTickets", userTickets);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Codice Fiscale non può essere vuoto");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

