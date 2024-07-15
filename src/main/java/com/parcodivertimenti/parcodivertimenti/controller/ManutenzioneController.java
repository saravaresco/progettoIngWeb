package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;
import com.parcodivertimenti.parcodivertimenti.model.dao.riparaDAO;

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
import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.RiparaDAOMySQLJDBCImpl;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ManutentoreController")
public class ManutenzioneController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002"; // Sostituisci con la tua password del database

    public List<ripara> getUserTickets(String codiceFiscale) throws ServletException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            riparaDAO riparaDAO = new RiparaDAOMySQLJDBCImpl(conn);
            return (List<ripara>) riparaDAO.findByCFManutentore(codiceFiscale);
        } catch (SQLException e) {
            throw new ServletException("Errore di connessione al database", e);
        }
    }

    public void getIntervento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceFiscale = request.getParameter("codiceFiscale");
        if (codiceFiscale != null && !codiceFiscale.isEmpty()) {
            List<ripara> userTickets = getUserTickets(codiceFiscale);
            request.setAttribute("userTickets", userTickets);
            request.getRequestDispatcher("interventi.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Codice Fiscale non può essere vuoto");
            request.getRequestDispatcher("interventi.jsp").forward(request, response);
        }
    }

    public void retrieveInterventi(HttpServletRequest request, HttpServletResponse response)
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

        // Query per recuperare il codice fiscale del manutentore basato su username e password
        String query = "SELECT CODICE_FISCALE FROM manutentore WHERE USERNAME = ? AND PASSWORD = ?";
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

        if(codiceFiscale != null){

            // Se il codice fiscale è stato trovato, recuperare i bigliettigli interventi
            List<ripara> ripara = retrieveTicketsByCodiceFiscale(codiceFiscale);

            // Salvare i biglietti nella request per poterli visualizzare nella JSP
            request.setAttribute("biglietti", ripara);

            // Reindirizzare alla pagina per visualizzare i biglietti (esempio)
            RequestDispatcher dispatcher = request.getRequestDispatcher("interventi.jsp");
            dispatcher.forward(request, response);
        } else {
            // Gestione caso in cui l'utente non esista o ci siano problemi di autenticazione
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Metodo per recuperare i biglietti associati a un dato codice fiscale
    private List<ripara> retrieveTicketsByCodiceFiscale(String codiceFiscale) throws ServletException {
        List<ripara> ripara = new ArrayList<>();
        String query = "SELECT * FROM ripara WHERE CF_MANUTENTORE = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, codiceFiscale);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    String cfManutentore = rs.getString("CF_MANUTENTORE");
                    Long codeceAttrazione = rs.getLong("CODICE_ATTRAZIONE");
                    String descrizione = rs.getString("DESCRIZIONE");

                    ripara rip = new ripara();
                    rip.setCf_manutentore(cfManutentore);
                    rip.setCodice_attrazione((long) codeceAttrazione);
                    rip.setDescrizione(descrizione);


                    ripara.add(rip);
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei biglietti dell'utente", e);
        }

        return ripara;
    }

    public void recuperaInterventi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera il codice dell'attrazione dai parametri della richiesta
        String codiceAttrazioneStr = request.getParameter("codiceAttrazioneVisualizza");

        if (codiceAttrazioneStr != null && !codiceAttrazioneStr.isEmpty()) {
            try {
                Long codiceAttrazione = Long.valueOf(codiceAttrazioneStr);

                // Eseguire il recupero dei dati del biglietto dal database
                List <ripara> riparazione = retrieveAttrazioneByCodice(codiceAttrazione);

                // Verificare se l'attrazione è stata trovata
                if (riparazione != null & !riparazione.isEmpty()) {
                    // Inserire il biglietto come attributo nella richiesta per essere utilizzato nel JSP
                    request.setAttribute("riparazione", riparazione);

                    // Inviare il controllo alla pagina di visualizzazione degli interventi per l'attrazione
                    RequestDispatcher dispatcher = request.getRequestDispatcher("interventiAttrazione.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Se le riparazioni non sono state trovate, gestire l'errore o redirigere a una pagina di errore
                    request.setAttribute("error", "Nessuna riparazione trovata per l'attrazione specificata");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Gestire l'errore nel caso in cui il codice attrazione non possa essere convertito in Long
                request.setAttribute("error", "Codice attrazione non valido");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Gestire il caso in cui il codice attrazione non è presente nei parametri della richiesta
            request.setAttribute("error", "Codice attrazione non fornito");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Metodo per simulare il recupero dell'attrazione dal database
    public List<ripara> retrieveAttrazioneByCodice(Long id) throws ServletException {
        List<ripara> riparazioni = new ArrayList<>();
        String query = "SELECT * FROM ripara WHERE CODICE_ATTRAZIONE = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ripara rip = new ripara();

                    rip.setCf_manutentore(rs.getString("CF_MANUTENTORE"));
                    rip.setCodice_attrazione(rs.getLong("CODICE_ATTRAZIONE"));
                    rip.setDescrizione(rs.getString("DESCRIZIONE"));

                    riparazioni.add(rip);

                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei biglietti dell'utente", e);
        }

        return riparazioni;
    }

    //Metodo per inserire un nuovo intervento
    public void newIntervento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (username == null || password == null) {
            // Se username o password non sono presenti nella sessione, gestire l'errore o il redirect necessario
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); // Esempio
            dispatcher.forward(request, response);
            return;
        }

        // Query per recuperare il codice fiscale del manutentore basato su username e password
        String codiceFiscale = null;
        int numeroInterventi = 0;
        String queryCF = "SELECT CODICE_FISCALE, NUMERO_INTERVENTI_ESEGUITI FROM manutentore WHERE USERNAME = ? AND PASSWORD = ?";


        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmtCF = conn.prepareStatement(queryCF)) {

            stmtCF.setString(1, username);
            stmtCF.setString(2, password);

            try (ResultSet rs = stmtCF.executeQuery()) {
                if (rs.next()) {
                    codiceFiscale = rs.getString("CODICE_FISCALE");
                    numeroInterventi = rs.getInt("NUMERO_INTERVENTI_ESEGUITI");
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero del codice fiscale dell'utente", e);
        }

        if (codiceFiscale != null) {
            // Recupera i dati dal form
            String codiceAttrazioneStr = request.getParameter("codiceAttrazioneInserisci");
            String descrizione = request.getParameter("descrizione");

            try {
                Long codiceAttrazione = Long.valueOf(codiceAttrazioneStr);

                // Query per inserire il nuovo intervento
                String queryInsert = "INSERT INTO ripara (CF_MANUTENTORE, CODICE_ATTRAZIONE, DESCRIZIONE) VALUES (?, ?, ?)";
                // Query per aggiornare il numero di interventi
                String queryUpdateInterventi = "UPDATE manutentore SET NUMERO_INTERVENTI_ESEGUITI = ? WHERE CODICE_FISCALE = ?";

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement stmtInsert = conn.prepareStatement(queryInsert);
                     PreparedStatement stmtUpdateInterventi = conn.prepareStatement(queryUpdateInterventi)) {

                    conn.setAutoCommit(false); // Inizia una transazione

                    // Inserisce il nuovo intervento
                    stmtInsert.setString(1, codiceFiscale);
                    stmtInsert.setLong(2, codiceAttrazione);
                    stmtInsert.setString(3, descrizione);
                    int rowsInserted = stmtInsert.executeUpdate();

                    if (rowsInserted > 0) {
                        // Incrementa il numero di interventi
                        numeroInterventi++;
                        stmtUpdateInterventi.setInt(1, numeroInterventi);
                        stmtUpdateInterventi.setString(2, codiceFiscale);
                        int rowsUpdated = stmtUpdateInterventi.executeUpdate();

                        if (rowsUpdated > 0) {
                            conn.commit(); // Conferma la transazione
                            request.setAttribute("success", "Intervento inserito con successo!");
                        } else {
                            conn.rollback(); // Annulla la transazione
                            request.setAttribute("error", "Errore durante l'aggiornamento del numero di interventi.");
                        }
                    } else {
                        conn.rollback(); // Annulla la transazione
                        request.setAttribute("error", "Errore durante l'inserimento dell'intervento.");
                    }

                } catch (SQLException e) {
                    throw new ServletException("Errore durante l'inserimento dell'intervento nel database", e);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Codice attrazione non valido");
            }

        } else {
            request.setAttribute("error", "Errore durante il recupero del codice fiscale dell'utente");
        }

        // Inviare il controllo alla pagina di conferma o errore
        RequestDispatcher dispatcher = request.getRequestDispatcher("confermaAzione.jsp");
        dispatcher.forward(request, response);
    }
}
