<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Spettacoli</title>
</head>
<body>
<h1>Inserisci il Codice dello Spettacolo</h1>
<form action="attore" method="post">
    <input type="hidden" name="action" value="view">
    <label for="codiceSpettacolo">Codice Spettacolo:</label>
    <input type="text" id="codiceSpettacolo" name="codiceSpettacolo" required>
    <button type="submit">Visualizza Dettagli</button>
</form>

<%
    spettacolo spettacolo = (spettacolo) request.getAttribute("spettacolo");
    String error = (String) request.getAttribute("error");

    if (spettacolo != null) {
%>
<h2>Dettagli dello Spettacolo</h2>
<form action="attore" method="post">
    <label for="nomeUpdate">Nome:</label>
    <input type="text" id="nomeUpdate" name="nome" value="<%= spettacolo.getNome() %>" required><br>
    <label for="tipologiaUpdate">Tipologia:</label>
    <input type="text" id="tipologiaUpdate" name="tipologia" value="<%= spettacolo.getTipologia() %>" required><br>
    <label for="dataUpdate">Data:</label>
    <input type="date" id="dataUpdate" name="data" value="<%= spettacolo.getData() %>" required><br>
    <label for="luogoUpdate">Luogo:</label>
    <input type="text" id="luogoUpdate" name="luogo" value="<%= spettacolo.getLuogo() %>" required><br>
    <label for="orarioInizioUpdate">Orario Inizio:</label>
    <input type="time" id="orarioInizioUpdate" name="orarioInizio" value="<%= spettacolo.getOrario_inizio() %>" required><br>
    <label for="durataUpdate">Durata:</label>
    <input type="time" id="durataUpdate" name="durata" value="<%= spettacolo.getDurata() %>" required><br>
    <button type="submit">Modifica</button>
</form>
<%
} else if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<%
    }
%>

<h1>Inserisci un Nuovo Spettacolo</h1>
<form action="attore" method="post">
    <input type="hidden" name="action" value="insert">
    <label for="nomeInsert">Nome:</label>
    <input type="text" id="nomeInsert" name="nome" required><br>
    <label for="tipologiaInsert">Tipologia:</label>
    <input type="text" id="tipologiaInsert" name="tipologia" required><br>
    <label for="dataInsert">Data:</label>
    <input type="date" id="dataInsert" name="data" required><br>
    <label for="luogoInsert">Luogo:</label>
    <input type="text" id="luogoInsert" name="luogo" required><br>
    <label for="orarioInizioInsert">Orario Inizio:</label>
    <input type="time" id="orarioInizioInsert" name="orarioInizio" required><br>
    <label for="durataInsert">Durata:</label>
    <input type="time" id="durataInsert" name="durata" required><br>
    <button type="submit">Inserisci</button>
</form>
</body>
</html>
