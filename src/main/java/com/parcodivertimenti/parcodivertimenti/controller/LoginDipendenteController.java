package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.DipendenteDAOMySQLJDBCImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.VisitatoreDAOMySQLJDBCImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.dipendenteDAO;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDipendenteController {

    public LoginDipendenteController() {
    }

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sarA2002";

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("category");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            dipendenteDAO dipendenteDAO = new DipendenteDAOMySQLJDBCImpl(conn);
            dipendente dipendente = dipendenteDAO.findLoggedUser(username, password, role);

            if (dipendente != null) {
                // Credenziali corrette, salva username, password e ruolo nella sessione
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("role", role);

                // Reindirizza alla pagina del ruolo
                switch (role) {
                    case "manutentore":
                        RequestDispatcher dispatcher = request.getRequestDispatcher("manutentore.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case "addetto_giostre":
                        RequestDispatcher dispatcher1 = request.getRequestDispatcher("giostre.jsp");
                        dispatcher1.forward(request, response);
                        break;
                    case "addetto_ristorante":
                        RequestDispatcher dispatcher2 = request.getRequestDispatcher("ristorante.jsp");
                        dispatcher2.forward(request, response);
                        break;
                    case "attore":
                        RequestDispatcher dispatcher3 = request.getRequestDispatcher("attore.jsp");
                        dispatcher3.forward(request, response);
                        break;
                }

            }else {
                // Credenziali errate, reindirizza alla pagina error.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Errore di connessione al database", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
