<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Ristoranti</title>
</head>
<body>
<h1>Inserisci il Nome del Ristorante</h1>
<form action="ristorante" method="post">
    <input type="hidden" name="action" value="view">
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
</body>
</html>
