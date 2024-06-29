package com.parcodivertimenti.parcodivertimenti.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    // Configurazione delle credenziali di accesso al database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sarA2002";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean validCredentials = validateCredentials(username, password, role);

        if (validCredentials) {
            switch (role) {
                case "visitatore":
                    response.sendRedirect("visitatore/home.jsp");
                    break;
                case "addetto_giostre":
                    response.sendRedirect("addetto_giostre/home.jsp");
                    break;
                case "addetto_ristorante":
                    response.sendRedirect("addetto_ristorante/home.jsp");
                    break;
                case "manutentore":
                    response.sendRedirect("manutentore.jsp");
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }

    private boolean validateCredentials(String username, String password, String role) {
        String sql = "SELECT * FROM " + role + " WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Restituisce true se le credenziali sono corrette
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
