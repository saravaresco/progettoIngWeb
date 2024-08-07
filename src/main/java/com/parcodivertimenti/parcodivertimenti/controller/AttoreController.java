package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

@WebServlet("/attore")
public class AttoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Configura le informazioni del tuo database MySQL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root"; // Sostituisci con il tuo username del database
    private static final String DB_PASS = "sarA2002";


    public void getSpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<spettacolo> spettacoli = new ArrayList<>();
        String error = null;
        String codiceSpettacolo = request.getParameter("codiceSpettacolo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String query = "SELECT * FROM spettacolo WHERE NOME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codiceSpettacolo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                spettacolo spettacolo = new spettacolo();
                spettacolo.setNome(resultSet.getString("NOME"));
                spettacolo.setTipologia(resultSet.getString("TIPOLOGIA"));
                spettacolo.setData(resultSet.getDate("DATA"));
                spettacolo.setLuogo(resultSet.getString("LUOGO"));
                spettacolo.setOrario_inizio(resultSet.getTime("ORARIO_INIZIO"));
                spettacolo.setDurata(resultSet.getTime("DURATA"));

                spettacoli.add(spettacolo);
            }
            if (!spettacoli.isEmpty()) {
                request.setAttribute("spettacoli", spettacoli);
                RequestDispatcher dispatcher = request.getRequestDispatcher("spettacoli.jsp");
                dispatcher.forward(request, response);
            } else {
                error = "Spettacolo non trovato.";
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }


            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nel recupero dei dettagli dello spettacolo.";
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("errore.jsp");
            dispatcher.forward(request, response);

        }


    }



    public void modifySpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Ottenere il nome e data dello spettacolo dall'utente
        String nomeSpettacolo = request.getParameter("spettacolo");
        String dataSpettacoloString = request.getParameter("spettacoloData");
        Date dataSpettacolo = null;

        try {
            // Validazione del formato "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);  // Impostare lenient a false per rendere la validazione rigorosa
            dataSpettacolo = new Date(dateFormat.parse(dataSpettacoloString).getTime());
        } catch (ParseException e) {
            // Gestione dell'errore se la data non è nel formato corretto
            request.setAttribute("error", "Formato data non valido. Assicurarsi di inserire la data nel formato corretto (yyyy-MM-dd).");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //recupero dati spettacolo dal database
        spettacolo spet = retrieveSpettacoloByNome(nomeSpettacolo, dataSpettacolo);

        // Verificare se il biglietto è stato trovato
        if (spet != null) {
            // Salva l'ID del biglietto nella sessione
            HttpSession session = request.getSession();
            session.setAttribute("spettacolo", spet);
            // Inserire il biglietto come attributo nella richiesta per essere utilizzato nel JSP
            request.setAttribute("spettacolo", spet);

            // Inviare il controllo alla pagina di modifica biglietto (potrebbe essere un altro JSP)
            RequestDispatcher dispatcher = request.getRequestDispatcher("modifySpettacolo.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se il biglietto non è stato trovato, gestire l'errore o redirigere a una pagina di errore
            response.sendRedirect("error.jsp");
        }
    }

    public spettacolo retrieveSpettacoloByNome(String nomeSpettacolo, Date dataSpettacolo) throws ServletException{
        spettacolo spett = null;
        String query = "SELECT * FROM spettacolo WHERE NOME = ? AND DATA = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nomeSpettacolo);
            stmt.setDate(2, dataSpettacolo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    spett= new spettacolo();

                    spett.setNome(rs.getString("NOME"));
                    spett.setTipologia(rs.getString("TIPOLOGIA"));
                    spett.setData(rs.getDate("DATA"));
                    spett.setLuogo(rs.getString("LUOGO"));
                    spett.setOrario_inizio(rs.getTime("ORARIO_INIZIO"));
                    spett.setDurata(rs.getTime("DURATA"));



                }
            }

        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei biglietti dell'utente", e);
        }

        return spett;


    }


    public void updateSpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottenere i parametri modificati dal form
        String nome = request.getParameter("nome");
        String tipologia = request.getParameter("tipologia");
        String luogo = request.getParameter("luogo");
        Date data = Date.valueOf(request.getParameter("data"));  // Converti la data nel formato corretto

        String orarioInizioParam = request.getParameter("orario_inizio");
        String durataParam = request.getParameter("durata");

        // Aggiungere i secondi di default a "00" se non forniti
        orarioInizioParam = completeTimeWithSeconds(orarioInizioParam);
        durataParam = completeTimeWithSeconds(durataParam);

        Time orario_inizio = Time.valueOf(orarioInizioParam);
        Time durata = Time.valueOf(durataParam);


        // Recuperare lo spettacolo dall'attributo di sessione
        spettacolo spett = (spettacolo) request.getSession().getAttribute("spettacolo");

        // Aggiornare i dati dello spettacolo con quelli modificati
        spett.setNome(nome);
        spett.setTipologia(tipologia);
        spett.setLuogo(luogo);
        spett.setData(data);
        spett.setOrario_inizio(orario_inizio);
        spett.setDurata(durata);

        // Chiamare un metodo di servizio (o DAO) per aggiornare lo spettacolo nel database
        try {
            boolean success = updateSpettacoloInDatabase(spett);  // Metodo per aggiornare nel database
            if (success) {
                // Se l'aggiornamento è riuscito, reindirizza alla pagina di conferma o di dettaglio dello spettacolo
                //response.sendRedirect("confermaAzione.jsp");  // Sostituisci con l'ID corretto dello spettacolo
                request.setAttribute("viewUrl", "spettacoloModificato");
            } else {
                // Se l'aggiornamento non è riuscito, gestisci l'errore appropriatamente
                //response.sendRedirect("error.jsp");
                request.setAttribute("viewUrl", "error");
            }
        } catch (Exception e) {
            throw new ServletException("Errore durante l'aggiornamento dello spettacolo", e);
        }
    }

    // Metodo per completare il formato dell'orario con i secondi di default a "00"
    private String completeTimeWithSeconds(String time) {
        if (time.matches("\\d{2}:\\d{2}")) {
            return time + ":00";
        } else if (time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return time;
        } else {
            throw new IllegalArgumentException("Formato dell'orario non valido: " + time);
        }
    }


    // Metodo di esempio per aggiornare lo spettacolo nel database
    private boolean updateSpettacoloInDatabase(spettacolo spett) {
        // Implementazione per aggiornare lo spettacolo nel database
        String query = "UPDATE spettacolo SET TIPOLOGIA=?, LUOGO=?, ORARIO_INIZIO=?, DURATA=? WHERE NOME=? AND DATA=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //stmt.setString(1, spett.getNome());
            stmt.setString(1, spett.getTipologia());
            stmt.setString(2, spett.getLuogo());

            stmt.setTime(3, spett.getOrario_inizio());
            stmt.setTime(4, spett.getDurata());
            stmt.setString(5, spett.getNome());
            stmt.setDate(6, (Date) spett.getData());
            //stmt.setInt(7, spett.getId());  // Assumi che lo spettacolo abbia un ID

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Ritorna true se almeno una riga è stata aggiornata
        } catch (SQLException e) {
            e.printStackTrace();  // Gestisci l'eccezione in base alla tua logica
            return false;
        }
    }



    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recupera i dati dal form
        String nome = request.getParameter("nome");
        String tipologia = request.getParameter("tipologia");
        String data = request.getParameter("data");
        String luogo = request.getParameter("luogo");

        String orarioInizio = request.getParameter("orarioInizio") ;
        String durata = request.getParameter("durata") ;

        // Aggiungere i secondi di default a "00" se non forniti
        orarioInizio = completeTimeWithSeconds(orarioInizio);
        durata = completeTimeWithSeconds(durata);

        // Recupera il codice fiscale dell'attore loggato dalla sessione
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        String codiceFiscaleAttore = null;
        String error = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parco_web", "root", "sarA2002");

            // Recupera il codice fiscale dell'attore loggato
            String queryAttore = "SELECT CODICE_FISCALE FROM attore WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement statementAttore = connection.prepareStatement(queryAttore);
            statementAttore.setString(1, username);
            statementAttore.setString(2, password);

            ResultSet resultSet = statementAttore.executeQuery();
            if (resultSet.next()) {
                codiceFiscaleAttore = resultSet.getString("CODICE_FISCALE");
            } else {
                error = "Attore non trovato o credenziali errate.";
            }
            resultSet.close();
            statementAttore.close();

            if (codiceFiscaleAttore != null) {
                // Avvia una transazione
                connection.setAutoCommit(false);

                // Inserisci il nuovo spettacolo nella tabella 'spettacolo'
                String querySpettacolo = "INSERT INTO spettacolo (NOME, TIPOLOGIA, DATA, LUOGO, ORARIO_INIZIO, DURATA) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statementSpettacolo = connection.prepareStatement(querySpettacolo);
                statementSpettacolo.setString(1, nome);
                statementSpettacolo.setString(2, tipologia);
                statementSpettacolo.setDate(3, java.sql.Date.valueOf(data));
                statementSpettacolo.setString(4, luogo);
                statementSpettacolo.setTime(5, java.sql.Time.valueOf(orarioInizio));
                statementSpettacolo.setTime(6, java.sql.Time.valueOf(durata));

                int rowsInsertedSpettacolo = statementSpettacolo.executeUpdate();

                if (rowsInsertedSpettacolo > 0) {
                    // Inserisci i dati nella tabella 'realizza'
                    String queryRealizza = "INSERT INTO realizza (CF_ATTORE, NOME_SPETTACOLO) VALUES (?, ?)";
                    PreparedStatement statementRealizza = connection.prepareStatement(queryRealizza);
                    statementRealizza.setString(1, codiceFiscaleAttore);
                    statementRealizza.setString(2, nome);

                    int rowsInsertedRealizza = statementRealizza.executeUpdate();

                    if (rowsInsertedRealizza > 0) {
                        // Conferma la transazione
                        connection.commit();
                        request.setAttribute("success", "Spettacolo inserito con successo e associazione con attore registrata!");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("spettacoloInserito.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Rollback della transazione in caso di errore
                        connection.rollback();
                        request.setAttribute("error", "Errore durante l'associazione dello spettacolo con l'attore.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                        dispatcher.forward(request, response);
                    }

                    statementRealizza.close();
                } else {
                    // Rollback della transazione in caso di errore
                    connection.rollback();
                    request.setAttribute("error", "Errore durante l'inserimento dello spettacolo.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    dispatcher.forward(request, response);
                }

                statementSpettacolo.close();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nell'inserimento dello spettacolo e nell'associazione con l'attore.";
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }

        request.setAttribute("error", error);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("confermaAzione.jsp");
        //dispatcher.forward(request, response);
    }


}

