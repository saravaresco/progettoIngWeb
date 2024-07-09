package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/modify-ticket")
public class ModifyTicketController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ticketId = request.getParameter("ticketId");

        // Ottieni i dettagli del biglietto
        //biglietto ticket = getTicketById(ticketId);
        request.setAttribute("ticket", ticketId);
        request.getRequestDispatcher("modifyTicket.jsp").forward(request, response);

        /*if (ticket != null) {
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("modifyTicket.jsp").forward(request, response);
        } else {
            response.sendRedirect("register.jsp"); // Gestire il caso in cui il biglietto non esista
        }*/
    }

    // Metodo per ottenere i dettagli di un biglietto dato l'ID
    /*private biglietto getTicketById(String ticketId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        biglietto ticket = null;

        try {
            // Carica il driver JDBC
            Class.forName(JDBC_DRIVER);

            // Crea la connessione al database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Query per ottenere i dettagli del biglietto dato l'ID
            String query = "SELECT * FROM biglietto WHERE ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, ticketId);

            // Esegui la query
            rs = stmt.executeQuery();

            // Processa i risultati
            if (rs.next()) {
                ticket = new biglietto();
                ticket.setID(rs.getLong("ID"));
                ticket.setData_acquisto(rs.getDate("DATA_ACQUISTO"));
                ticket.setTipologia1(rs.getString("TIPOLOGIA1"));
                ticket.setTipologia2(rs.getString("TIPOLOGIA2"));
                ticket.setPrezzo(rs.getLong("PREZZO"));
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

        return ticket;
    }*/

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }*/
}

