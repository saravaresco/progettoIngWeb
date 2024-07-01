<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eventi</title>
</head>
<body>
<h1>Eventi del Parco Divertimenti</h1>
<nav>
    <ul>
        <li><a href="view.jsp">Home</a></li>
        <li><a href="eventi.jsp">Eventi</a></li>
        <li><a href="attrazioni.jsp">Attrazioni</a></li>
        <li><a href="mappa.jsp">Contatti</a></li>
    </ul>
</nav>

<div>
    <h2>Lista degli eventi:</h2>
    <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Caricamento del driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connessione al database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parco_web", "root", "sarA2002");

            // Creazione dello statement
            stmt = conn.createStatement();

            // Esecuzione della query
            String sql = "SELECT * FROM spettacolo";
            rs = stmt.executeQuery(sql);

            // Visualizzazione dei risultati
            while(rs.next()) {
                String nome = rs.getString("NOME");
                String tipologia = rs.getString("TIPOLOGIA");
                String data = rs.getString("DATA");
                String luogo = rs.getString("LUOGO");
                String orarioInizio = rs.getString("ORARIO_INIZIO");
                String durata = rs.getString("DURATA");
    %>
    <div>
        <h3><%= nome %></h3>
        <p>Tipologia: <%= tipologia %></p>
        <p>Data: <%= data %></p>
        <p>Luogo: <%= luogo %></p>
        <p>Orario Inizio: <%= orarioInizio %></p>
        <p>Durata: <%= durata %> minuti</p>
    </div>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiusura delle risorse
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }
    %>
</div>
</body>
</html>



