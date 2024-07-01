package com.parcodivertimenti.parcodivertimenti.controller;

import jakarta.servlet.RequestDispatcher;
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

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "sarA2002";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String action = request.getParameter("action");

        if ("visitatore".equals(userType)) {
            if ("register".equals(action)) {
                response.sendRedirect("register.jsp");
            } else if ("login".equals(action)) {
                response.sendRedirect("visitorArea.jsp");
            }
        } else if ("dipendente".equals(userType)) {
            String category = request.getParameter("category");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean isValid = authenticateEmployee(category, username, password);
            if (isValid) {
                switch (category) {
                    case "addetto_giostre":
                        response.sendRedirect("giostre.jsp");
                        break;
                    case "addetto_ristorante":
                        response.sendRedirect("jsp/homeManagement/employee/ristorante.jsp");
                        break;
                    case "manutentore":
                        response.sendRedirect("jsp/homeManagement/employee/manutentore2.jsp");
                        break;
                    case "attore":
                        response.sendRedirect("jsp/homeManagement/employee/attore.jsp");
                        break;
                }
            } else {
                request.setAttribute("errorMessage", "Username o password non corretti");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    private boolean authenticateEmployee(String category, String username, String password) {
        String query = "SELECT * FROM " + category + " WHERE USERNAME = ? AND PASSWORD = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
