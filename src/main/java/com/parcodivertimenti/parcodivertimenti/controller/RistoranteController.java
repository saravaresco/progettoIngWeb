package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/ristorante")
public class RistoranteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            visualizzaRistorante(request, response);
        }
    }

    private void visualizzaRistorante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeRistorante = request.getParameter("nomeRistorante");
        puntoRistoro ristorante = null;
        String error = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuoDatabase", "username", "password");

            String query = "SELECT * FROM punto_ristoro WHERE NOME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomeRistorante);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ristorante = new puntoRistoro();
                ristorante.setID(resultSet.getLong("ID"));
                ristorante.setNome(resultSet.getString("NOME"));
                ristorante.setTipo_cucina(resultSet.getString("TIPO_CUCINA"));
                ristorante.setOrario_apertura(resultSet.getTime("ORARIO_APERTURA"));
                ristorante.setOrario_chiusura(resultSet.getTime("ORARIO_CHIUSURA"));
            } else {
                error = "Ristorante non trovato.";
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nel recupero dei dettagli del ristorante.";
        }

        request.setAttribute("ristorante", ristorante);
        request.setAttribute("error", error);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ristorante.jsp");
        dispatcher.forward(request, response);
    }

}

