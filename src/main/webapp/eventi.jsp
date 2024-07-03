<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eventi</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        h1 {
            text-align: center;
            color: #ffffff;
            background-color: #4CAF50;
            padding: 20px;
            margin: 0;
        }
        nav {
            text-align: center;
            background-color: #333;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            /*display: flex;
            justify-content: center;
            background-color: #333;*/
            text-align: center;
        }
        nav ul li {
            /*margin: 0 15px;*/
            display: inline-block;
        }
        nav ul li a {
            text-decoration: none;
            color: #ffffff;
            padding: 10px 20px;
            display: block;
        }
        nav ul li a:hover {
            background-color: #575757;
            /*border-radius: 5px;*/
        }
        .content {
            flex-grow: 1;
            padding: 20px;
        }
        .event {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin: 20px 0;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .event h3 {
            color: #4CAF50;
        }
        .event p {
            color: #555;
        }
        footer {
            background-color: #4CAF50;
            color: #ffffff;
            text-align: center;
            padding: 10px 0;
            width: 100%;
        }
    </style>
</head>
<body>
<h1>Eventi del Parco Divertimenti</h1>
<nav>
    <ul>
        <li><a href="view.jsp"><strong>Home</strong></a></li>
        <li><a href="attrazioni.jsp"><strong>Attrazioni</strong></a></li>
        <li><a href="mappa.jsp"><strong>Mappa</strong></a></li>
        <li><a href="login.jsp"><strong>Login</strong></a></li>
    </ul>
</nav>

<div class="content">
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
    <div class="event">
        <h3><%= nome %></h3>
        <p><strong>Tipologia:</strong> <%= tipologia %></p>
        <p><strong>Data:</strong>Data: <%= data %></p>
        <p><strong>Luogo:</strong> <%= luogo %></p>
        <p><strong>Orario Inizio:</strong> <%= orarioInizio %></p>
        <p><strong>Durata:</strong> <%= durata %> </p>
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
<footer>
    <p>&copy; 2024 Fantasia Park. Tutti i diritti riservati.</p>
</footer>
</body>
</html>



