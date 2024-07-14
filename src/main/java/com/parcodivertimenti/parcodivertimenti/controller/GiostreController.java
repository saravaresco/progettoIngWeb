package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;

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

@WebServlet("/giostre")
public class GiostreController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void getAttrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceAttrazione = request.getParameter("codiceAttrazione");

        attrazione attrazione = null;
        String error = null;

        try {
            // Connessione al database
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parco_web", "root", "sarA2002");

            String query = "SELECT * FROM attrazione WHERE CODICE = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codiceAttrazione);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                attrazione = new attrazione();
                attrazione.setCodice(resultSet.getLong("CODICE"));
                attrazione.setNome(resultSet.getString("NOME"));
                attrazione.setTipologia(resultSet.getString("TIPOLOGIA"));
                attrazione.setData_ultima_manutenzione(resultSet.getDate("DATA_ULTIMA_MANUTENZIONE"));
            } else {
                error = "Attrazione non trovata.";
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            error = "Errore nel recupero dei dettagli dell'attrazione.";
        }

        request.setAttribute("attrazione", attrazione);
        request.setAttribute("error", error);
        RequestDispatcher dispatcher = request.getRequestDispatcher("giostre.jsp");
        dispatcher.forward(request, response);
    }


}

