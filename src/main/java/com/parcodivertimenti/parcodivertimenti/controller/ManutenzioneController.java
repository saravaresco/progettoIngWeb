package com.parcodivertimenti.parcodivertimenti.controller;

import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;
import com.parcodivertimenti.parcodivertimenti.model.dao.riparaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl.RiparaDAOMySQLJDBCImpl;
import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;

@WebServlet("/ManutentoreController")
public class ManutenzioneController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String dbUrl = "jdbc:mysql://localhost:3306/parco_web";
        String dbUser = "root";
        String dbPassword = "sarA2002";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            RiparaDAOMySQLJDBCImpl riparaDAO = new RiparaDAOMySQLJDBCImpl(conn);

            if ("visualizza".equals(action)) {
                Long codiceAttrazione = Long.parseLong(request.getParameter("codiceAttrazioneVisualizza"));
                List<ripara> interventi = (List<ripara>) riparaDAO.findByCodiceAttrazione(codiceAttrazione);
                request.setAttribute("interventi", interventi);
                request.getRequestDispatcher("manutentore.jsp").forward(request, response);
            } else if ("inserisci".equals(action)) {
                String codiceAttrazione = request.getParameter("codiceAttrazioneInserisci");
                String descrizione = request.getParameter("descrizione");
                String cfManutentore = (String) request.getSession().getAttribute("cfManutentore"); // Assuming the CF_MANUTENTORE is stored in session
                ripara intervento = new ripara();
                riparaDAO.create(intervento.getCf_manutentore(), intervento.getCodice_attrazione(), intervento.getDescrizione());
                response.sendRedirect("manutentore.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementazione del metodo doGet per gestire le richieste GET

        String action = request.getParameter("action");

        if ("visualizza".equals(action)) {
            // Codice per gestire la visualizzazione degli interventi
            String dbUrl = "jdbc:mysql://localhost:3306/parco_web";
            String dbUser = "root";
            String dbPassword = "sarA2002";
            Connection conn = null;

            try {
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                RiparaDAOMySQLJDBCImpl riparaDAO = new RiparaDAOMySQLJDBCImpl(conn);

                Long codiceAttrazione = Long.parseLong(request.getParameter("codiceAttrazioneVisualizza"));
                List<ripara> interventi = (List<ripara>) riparaDAO.findByCodiceAttrazione(codiceAttrazione);
                request.setAttribute("interventi", interventi);
                request.getRequestDispatcher("manutentore.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            // Gestione di altre operazioni GET, se necessario
            response.sendRedirect("manutentore.jsp"); // Esempio di redirect a una pagina JSP
        }
    }

}
