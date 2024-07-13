package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.BigliettoDAOMySQLJDBCImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register-visitor")
public class RegisterVisitorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database


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

    public void retrieveTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (username == null || password == null) {
            // Se username o password non sono presenti nella sessione, gestire l'errore o il redirect necessario
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); // Esempio
            dispatcher.forward(request, response);
            return;
        }

        // Query per recuperare il codice fiscale dell'utente basato su username e password
        String query = "SELECT CODICE_FISCALE FROM visitatore WHERE USERNAME = ? AND PASSWORD = ?";
        String codiceFiscale = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    codiceFiscale = rs.getString("CODICE_FISCALE");
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero del codice fiscale dell'utente", e);
        }

        // Se il codice fiscale è stato trovato, recuperare i biglietti associati a quel codice fiscale
        if (codiceFiscale != null) {
            List<biglietto> biglietti = retrieveTicketsByCodiceFiscale(codiceFiscale);

            // Salvare i biglietti nella request per poterli visualizzare nella JSP
            request.setAttribute("biglietti", biglietti);

            // Reindirizzare alla pagina per visualizzare i biglietti (esempio)
            RequestDispatcher dispatcher = request.getRequestDispatcher("visualizzaBiglietti.jsp");
            dispatcher.forward(request, response);
        } else {
            // Gestione caso in cui l'utente non esista o ci siano problemi di autenticazione
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Metodo per recuperare i biglietti associati a un dato codice fiscale
    private List<biglietto> retrieveTicketsByCodiceFiscale(String codiceFiscale) throws ServletException {
        List<biglietto> biglietti = new ArrayList<>();
        String query = "SELECT * FROM biglietto WHERE CODICE_FISCALE_V = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, codiceFiscale);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String codiceFiscaleV = rs.getString("CODICE_FISCALE_V");
                    double prezzo = rs.getDouble("PREZZO");
                    String dataAcquisto = rs.getString("DATA_ACQUISTO");
                    String tipologia1 = rs.getString("TIPOLOGIA1");
                    String tipologia2 = rs.getString("TIPOLOGIA2");
                    String mail = rs.getString("MAIL");

                    biglietto biglietto = new biglietto();
                    biglietto.setID((long) id);
                    biglietto.setCodice_fiscale(codiceFiscaleV);
                    biglietto.setPrezzo((long) prezzo);
                    biglietto.setData_acquisto(dataAcquisto);
                    biglietto.setTipologia1(tipologia1);
                    biglietto.setTipologia2(tipologia2);
                    biglietto.setMail(mail);

                    biglietti.add(biglietto);
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei biglietti dell'utente", e);
        }

        return biglietti;
    }

    public void modifyTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottenere l'ID del biglietto dall'input dell'utente
        Long idBiglietto = Long.valueOf(request.getParameter("idBiglietto"));

        // Eseguire il recupero dei dati del biglietto dal database
        biglietto ticket = retrieveTicketsByCodice(idBiglietto);

        // Verificare se il biglietto è stato trovato
        if (ticket != null) {
            // Salva l'ID del biglietto nella sessione
            HttpSession session = request.getSession();
            session.setAttribute("idBiglietto", idBiglietto);
            // Inserire il biglietto come attributo nella richiesta per essere utilizzato nel JSP
            request.setAttribute("ticket", ticket);

            // Inviare il controllo alla pagina di modifica biglietto (potrebbe essere un altro JSP)
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifyTicket.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se il biglietto non è stato trovato, gestire l'errore o redirigere a una pagina di errore
            response.sendRedirect("error.jsp");
        }
    }

    // Metodo per simulare il recupero del biglietto dal database
    public biglietto retrieveTicketsByCodice(Long id) throws ServletException {
        biglietto biglietto = null;
        String query = "SELECT * FROM biglietto WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    biglietto = new biglietto();

                    biglietto.setID(rs.getLong("ID"));
                    biglietto.setCodice_fiscale(rs.getString("CODICE_FISCALE_V"));
                    biglietto.setPrezzo(rs.getLong("PREZZO"));
                    biglietto.setData_acquisto(rs.getString("DATA_ACQUISTO"));
                    biglietto.setTipologia1(rs.getString("TIPOLOGIA1"));
                    biglietto.setTipologia2(rs.getString("TIPOLOGIA2"));
                    biglietto.setMail(rs.getString("MAIL"));


                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei biglietti dell'utente", e);
        }

        return biglietto;
    }

    public void updateTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idBiglietto = (Long) session.getAttribute("idBiglietto");

        // Verifica che idBiglietto non sia null
        if (idBiglietto == null) {
            // Gestire il caso in cui idBiglietto non sia presente nella sessione
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Ottenere i parametri modificati dal form
            String mail = request.getParameter("mail");

            // Query per aggiornare i dati del biglietto nel database
            String query = "UPDATE biglietto SET MAIL = ? WHERE ID = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, mail);
                stmt.setLong(2, idBiglietto);

                // Eseguire l'aggiornamento
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Redirect alla pagina di successo o messaggio di conferma
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ticketUpdated.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Gestire il caso in cui il biglietto non sia stato trovato o non aggiornato
                    response.sendRedirect("error.jsp");
                }

            } catch (SQLException e) {
                throw new ServletException("Errore durante l'aggiornamento del biglietto", e);
            }
        }catch (NumberFormatException e) {
            // Gestire il caso in cui idBiglietto non sia un numero valido
            response.sendRedirect("error.jsp");
        }

    }
}

