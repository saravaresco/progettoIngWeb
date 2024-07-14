<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Ristoranti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        h1, h2 {
            color: #2c3e50;
        }
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Inserisci il Nome del Ristorante</h1>
    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="RistoranteController.getRistorante">
        <label for="nomeRistorante">Nome Ristorante:</label>
        <input type="text" id="nomeRistorante" name="nomeRistorante" required>
        <button type="submit">Visualizza Dettagli</button>
    </form>

    <%
        puntoRistoro ristorante = (puntoRistoro) request.getAttribute("ristorante");
        String error = (String) request.getAttribute("error");

        if (ristorante != null) {
    %>
    <h2>Dettagli del Ristorante</h2>
    <p>ID: <%= ristorante.getID() %></p>
    <p>Nome: <%= ristorante.getNome() %></p>
    <p>Tipologia Cucina: <%= ristorante.getTipo_cucina() %></p>
    <p>Orario Apertura: <%= ristorante.getOrario_apertura() %></p>
    <p>Orario Chiusura: <%= ristorante.getOrario_chiusura() %></p>
    <%
    } else if (error != null) {
    %>
    <p style="color: red;"><%= error %></p>
    <%
        }
    %>
</div>
</body>
</html>
