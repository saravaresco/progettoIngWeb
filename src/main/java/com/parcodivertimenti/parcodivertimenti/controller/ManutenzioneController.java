package com.parcodivertimenti.parcodivertimenti.controller;


import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manutentore")
public class ManutenzioneController extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parco_web";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sarA2002";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceAttrazione = request.getParameter("codice");
        if (codiceAttrazione != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String queryAttrazione = "SELECT * FROM attrazione WHERE CODICE = ?";
                PreparedStatement psAttrazione = conn.prepareStatement(queryAttrazione);
                psAttrazione.setInt(1, Integer.parseInt(codiceAttrazione));
                ResultSet rsAttrazione = psAttrazione.executeQuery();

                if (rsAttrazione.next()) {
                    request.setAttribute("attrazione", rsAttrazione);
                }

                String queryInterventi = "SELECT * FROM ripara WHERE CODICE_ATTRAZIONE = ?";
                PreparedStatement psInterventi = conn.prepareStatement(queryInterventi);
                psInterventi.setInt(1, Integer.parseInt(codiceAttrazione));
                ResultSet rsInterventi = psInterventi.executeQuery();

                List<ripara> interventi = new ArrayList<>();
                while (rsInterventi.next()) {
                    ripara intervento = new ripara();
                    intervento.setCf_manutentore(rsInterventi.getString("CF_MANUTENTORE"));
                    intervento.setCodice_attrazione(rsInterventi.getLong("CODICE_ATTRAZIONE"));
                    intervento.setDescrizione(rsInterventi.getString("DESCRIZIONE"));
                    interventi.add(intervento);
                }

                request.setAttribute("interventi", interventi);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        request.getRequestDispatcher("/manutentore2.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceAttrazione = request.getParameter("CODICE_ATTRAZIONE");
        String cfManutentore = request.getParameter("CF_MANUTENTORE");
        String descrizione = request.getParameter("DESCRRIZIONE");

        if (codiceAttrazione != null && cfManutentore != null && descrizione != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO ripara (CF_MANUTENTORE, CODICE_ATTRAZIONE, DESCRIZIONE) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, cfManutentore);
                ps.setLong(2, Long.parseLong(codiceAttrazione));
                ps.setString(3, descrizione);
                ps.executeUpdate();

                // Update the last maintenance date in attrazione table
                String updateQuery = "UPDATE manutentore SET NUMERO_INTERVENTI = NUMERO_INTERVENTI + 1 WHERE CODICE_FISCALE = ?";
                PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setString(1, cfManutentore);
                psUpdate.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        response.sendRedirect("manutenzione?codice=" + codiceAttrazione);
    }
}

