package com.parcodivertimenti.parcodivertimenti.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.VisitatoreDAOMySQLJDBCImpl;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.dao.CookieImpl.VisitatoreCookieImpl;
import com.parcodivertimenti.parcodivertimenti.model.dao.DaoFactory;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;
import com.parcodivertimenti.parcodivertimenti.services.config.Configuration;
import com.parcodivertimenti.parcodivertimenti.services.logservice.LogService;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "login2", urlPatterns = {"/login2"})
public class LoginVisitatore {

    public LoginVisitatore() {
    }

    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sarA2002";



    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            visitatoreDAO visitatoreDAO = new VisitatoreDAOMySQLJDBCImpl(conn);
            visitatore visitatore = visitatoreDAO.findLoggedUser(username, password);

            if (visitatore != null) {
                // Credenziali corrette, salva username e password nella sessione
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                // Credenziali corrette, reindirizza alla pagina areaPersonale.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            } else {
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

    public void forgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String updateQuery = "UPDATE visitatore SET PASSWORD = ? WHERE USERNAME = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Reindirizza alla pagina di conferma o login dopo l'aggiornamento della password
                RequestDispatcher dispatcher = request.getRequestDispatcher("passwordUpdated.jsp");
                dispatcher.forward(request, response);
            } else {
                // Reindirizza alla pagina di errore se l'email non è stata trovata
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
