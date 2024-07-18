<%@ page import="java.util.List" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.realizza" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spettacoli realizzati</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f7ef;
            padding-top: 20px;
        }
        .container {
            max-width: 900px;
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .ticket {
            border: 2px solid #28a745;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #d4edda;
        }
        .ticket p {
            margin: 0;
        }
        .btn-custom {
            background-color: #28a745;
            border-color: #28a745;
            color: white;
            width: 100%;
            margin-bottom: 20px;
            padding: 15px;
            font-size: 1.2em;
        }
        .btn-custom:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center title">Spettacoli realizzati</h1>
    <div id="userTickets">
        <%
            List<spettacolo> spettacoli = (List<spettacolo>) request.getAttribute("spettacoli");
            if (spettacoli != null && !spettacoli.isEmpty()) {
                for (spettacolo spettacolo : spettacoli) {
        %>
        <div class="ticket">
            <p><strong>Nome spettacolo:</strong> <%= spettacolo.getNome() %></p>
            <p><strong>Tipologia:</strong> <%= spettacolo.getTipologia() %></p>
            <p><strong>Data:</strong> <%= spettacolo.getData() %></p>
            <p><strong>Luogo:</strong> <%= spettacolo.getLuogo() %></p>
            <p><strong>Orario inizio:</strong> <%= spettacolo.getOrario_inizio() %></p>
            <p><strong>Durata:</strong> <%= spettacolo.getDurata() %></p>
        </div>
        <%
            }
        } else {
        %>
        <p>Nessuno spettacolo trovato.</p>
        <% } %>
    </div>
    <a href="attore.jsp" class="btn btn-custom">Torna all'Area Personale</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
