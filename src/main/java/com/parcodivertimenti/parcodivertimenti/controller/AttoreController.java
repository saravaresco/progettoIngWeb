package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;

import java.io.IOException;
import java.sql.*;

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
        spettacolo spettacolo = null;
        String error = null;
        String codiceSpettacolo = request.getParameter("codiceSpettacolo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parco_web", "root", "sarA2002");

            String query = "SELECT * FROM spettacolo WHERE NOME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codiceSpettacolo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                spettacolo = new spettacolo();
                spettacolo.setNome(resultSet.getString("NOME"));
                spettacolo.setTipologia(resultSet.getString("TIPOLOGIA"));
                spettacolo.setData(resultSet.getDate("DATA"));
                spettacolo.setLuogo(resultSet.getString("LUOGO"));
                spettacolo.setOrario_inizio(resultSet.getTime("ORARIO_INIZIO"));
                spettacolo.setDurata(resultSet.getTime("DURATA"));

                // Reindirizzamento a spettacoli2.jsp
                request.setAttribute("spettacolo", spettacolo);
                RequestDispatcher dispatcher = request.getRequestDispatcher("spettacoli.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nessuno spettacolo trovato, reindirizzamento a pagina di errore
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
        //Ottenere il nome dello spettacolo dall'utente
        String nomeSpettacolo = request.getParameter("spettacolo");

        //recupero dati spettacolo dal database
        spettacolo spet = retrieveSpettacoloByNome(nomeSpettacolo);

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

    public spettacolo retrieveSpettacoloByNome(String nomeSpettacolo) throws ServletException{
        spettacolo spett = null;
        String query = "SELECT * FROM spettacolo WHERE NOME = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nomeSpettacolo);

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
        Time orario_inizio = Time.valueOf(request.getParameter("orario_inizio"));  // Converti l'orario nel formato corretto
        Time durata = Time.valueOf(request.getParameter("durata"));  // Converti la durata nel formato corretto

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
                request.setAttribute("viewUrl", "confermaAzione");
            } else {
                // Se l'aggiornamento non è riuscito, gestisci l'errore appropriatamente
                //response.sendRedirect("error.jsp");
                request.setAttribute("viewUrl", "error");
            }
        } catch (Exception e) {
            throw new ServletException("Errore durante l'aggiornamento dello spettacolo", e);
        }
    }

    // Metodo di esempio per aggiornare lo spettacolo nel database
    private boolean updateSpettacoloInDatabase(spettacolo spett) {
        // Implementazione per aggiornare lo spettacolo nel database
        // Utilizza JDBC o un ORM per eseguire l'aggiornamento
        // Esempio (JDBC):
        String query = "UPDATE spettacolo SET TIPOLOGIA=?, LUOGO=?, DATA=?, ORARIO_INIZIO=?, DURATA=? WHERE NOME=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //stmt.setString(1, spett.getNome());
            stmt.setString(1, spett.getTipologia());
            stmt.setString(2, spett.getLuogo());
            stmt.setDate(3, (Date) spett.getData());
            stmt.setTime(4, spett.getOrario_inizio());
            stmt.setTime(5, spett.getDurata());
            stmt.setString(6, spett.getNome());
            //stmt.setInt(7, spett.getId());  // Assumi che lo spettacolo abbia un ID

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Ritorna true se almeno una riga è stata aggiornata
        } catch (SQLException e) {
            e.printStackTrace();  // Gestisci l'eccezione in base alla tua logica
            return false;
        }
    }



    /*public void updateSpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String spettacolo  = (String) session.getAttribute("spettacolo");

        // Verifica che idBiglietto non sia null
        if (spettacolo == null) {
            // Gestire il caso in cui nomeSpettacolo non sia presente nella sessione
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Ottenere i parametri modificati dal form
            Date data = Date.valueOf(request.getParameter("data"));
            String luogo = request.getParameter("luogo");
            Time orarioInizio = Time.valueOf(request.getParameter("orarioInizio"));
            Time durata = Time.valueOf(request.getParameter("durata"));

            // Query per aggiornare i dati del biglietto nel database
            String query = "UPDATE spettacolo SET DATA = ?, LUOGO = ?, ORARIO_INIZIO = ?, DURATA = ? WHERE NOME = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setDate(1, data);
                stmt.setString(2, luogo);
                stmt.setTime(3, orarioInizio);
                stmt.setTime(4, durata);
                stmt.setString(5, spettacolo);


                // Eseguire l'aggiornamento
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {


                    // Redirect alla pagina di successo o messaggio di conferma
                    RequestDispatcher dispatcher = request.getRequestDispatcher("confermaAzione.jsp");
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

    private void inserisciSpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String tipologia = request.getParameter("tipologia");
        String data = request.getParameter("data");
        String luogo = request.getParameter("luogo");
        String orarioInizio = request.getParameter("orarioInizio");
        String durata = request.getParameter("durata");

        String error = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parco_web", "root", "sarA2002");

            String query = "INSERT INTO spettacolo (NOME, TIPOLOGIA, DATA, LUOGO, ORARIO_INIZIO, DURATA) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, tipologia);
            statement.setDate(3, java.sql.Date.valueOf(data));
            statement.setString(4, luogo);
            statement.setTime(5, java.sql.Time.valueOf(orarioInizio));
            statement.setTime(6, java.sql.Time.valueOf(durata));

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nell'inserimento dello spettacolo.";
        }

        request.setAttribute("error", error);
        RequestDispatcher dispatcher = request.getRequestDispatcher("attore.jsp");
        dispatcher.forward(request, response);
    }*/


}

