package com.parcodivertimenti.parcodivertimenti.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/newVisitor")
public class NewVisitorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sarA2002";

    public void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // parametri inseriti
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String codiceFiscale = request.getParameter("codiceFiscale");
        String dataNascita = request.getParameter("dataNascita");
        String sesso = request.getParameter("sesso");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // JDBC variables for opening and managing connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Connessione al database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // inserimento dati
            String sql = "INSERT INTO visitatore (CODICE_FISCALE, NOME, COGNOME, DATA_NASCITA, SESSO, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            preparedStatement.setString(3, codiceFiscale);
            preparedStatement.setString(4, dataNascita);
            preparedStatement.setString(5, sesso);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, password);

            // Execute the query
            preparedStatement.executeUpdate();

            // Redirect to a success page or provide a success message
            //response.sendRedirect("confermaIscrizione.jsp");

            // Set the view URL to redirect to the confirmation page
            request.setAttribute("viewUrl", "confermaIscrizione");

        } catch (SQLException | ClassNotFoundException e) {
            // Handle any database or class not found errors
            e.printStackTrace();
            // Redirect to an error page or provide an error message
            //response.sendRedirect("login.jsp");
            // Set the view URL to redirect to the error page
            request.setAttribute("viewUrl", "error");

        } finally {
            // Close the connection and statement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
