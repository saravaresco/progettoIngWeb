package com.parcodivertimenti.parcodivertimenti.controller;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;
import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;
import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;



@WebServlet("/")
public class ParkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/parco_web", "root", "sarA2002");
            System.out.println("Database connected successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database not connected successfully");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/attrazioni":
                    listAttractions(request, response);
                    break;
                case "/food":
                    listFood(request, response);
                    break;
                case "/eventi":
                    listShows(request, response);
                    break;
                case "/map":
                    showMap(request, response);
                    break;
                case "/login":
                    showLogin(request, response);
                    break;
                default:
                    homePage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
        dispatcher.forward(request, response);
    }

    public void listAttractions(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<attrazione> attrazioni = new ArrayList<>();
        String sql = "SELECT * FROM attrazioni";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            attrazione attraction = new attrazione();
            attraction.setCodice(resultSet.getLong("CODICE"));
            attraction.setNome(resultSet.getString("NOME"));
            attraction.setTipologia(resultSet.getString("TIPOLOGIA"));
            attrazioni.add(attraction);
        }

        request.setAttribute("attrazioni", attrazioni);
        RequestDispatcher dispatcher = request.getRequestDispatcher("attrazioni.jsp");
        dispatcher.forward(request, response);
    }

    public void listFood(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<puntoRistoro> foodList = new ArrayList<>();
        String sql = "SELECT * FROM punto_ristoro";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            puntoRistoro food = new puntoRistoro();
            food.setID(resultSet.getLong("id"));
            food.setNome(resultSet.getString("nome"));
            food.setTipo_cucina(resultSet.getString("tipo cucina"));
            food.setOrario_apertura(resultSet.getTime("orario apertura"));
            food.setOrario_chiusura(resultSet.getTime("orario chiusura"));
            foodList.add(food);
        }

        request.setAttribute("food", foodList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("puntiRistoro.jsp");
        dispatcher.forward(request, response);
    }

    public void listShows(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<spettacolo> shows = new ArrayList<>();
        String sql = "SELECT * FROM spettacolo";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println(sql);

        while (resultSet.next()) {
            spettacolo show = new spettacolo();
            show.setNome(resultSet.getString("NOME"));
            show.setTipologia(resultSet.getString("TIPOLOGIA"));
            show.setData(resultSet.getDate("DATA"));
            show.setLuogo(resultSet.getString("LUOGO"));
            show.setOrario_inizio(resultSet.getTime("ORARIO_INIZIO"));
            show.setDurata(resultSet.getTime("DURATA"));
            shows.add(show);
        }

        request.setAttribute("shows", shows);
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventi.jsp");
        dispatcher.forward(request, response);
    }

    public void showMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("map.jsp");
        dispatcher.forward(request, response);
    }

    public void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

