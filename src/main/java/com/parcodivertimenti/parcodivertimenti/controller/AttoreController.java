package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/attore")
public class AttoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String codiceSpettacolo = request.getParameter("codiceSpettacolo");

        if ("view".equals(action)) {
            visualizzaSpettacolo(request, response, codiceSpettacolo);
        } else if ("update".equals(action)) {
            modificaSpettacolo(request, response);
        } else if ("insert".equals(action)) {
            inserisciSpettacolo(request, response);
        }
    }

    private void visualizzaSpettacolo(HttpServletRequest request, HttpServletResponse response, String codiceSpettacolo) throws ServletException, IOException {
        spettacolo spettacolo = null;
        String error = null;

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
            } else {
                error = "Spettacolo non trovato.";
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nel recupero dei dettagli dello spettacolo.";
        }

        request.setAttribute("spettacolo", spettacolo);
        request.setAttribute("error", error);
        RequestDispatcher dispatcher = request.getRequestDispatcher("attore.jsp");
        dispatcher.forward(request, response);
    }

    private void modificaSpettacolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String tipologia = request.getParameter("tipologia");
        String data = request.getParameter("data");
        String luogo = request.getParameter("luogo");
        String orarioInizio = request.getParameter("orarioInizio");
        String durata = request.getParameter("durata");

        String error = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuoDatabase", "username", "password");

            String query = "UPDATE spettacolo SET TIPOLOGIA = ?, DATA = ?, LUOGO = ?, ORARIO_INIZIO = ?, DURATA = ? WHERE NOME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tipologia);
            statement.setDate(2, java.sql.Date.valueOf(data));
            statement.setString(3, luogo);
            statement.setTime(4, java.sql.Time.valueOf(orarioInizio));
            statement.setTime(5, java.sql.Time.valueOf(durata));
            statement.setString(6, nome);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                error = "Spettacolo non trovato.";
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nella modifica dello spettacolo.";
        }

        request.setAttribute("error", error);
        visualizzaSpettacolo(request, response, nome);
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
    }


}

