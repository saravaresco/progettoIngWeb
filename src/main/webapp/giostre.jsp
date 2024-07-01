<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.attrazione" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli Attrazione</title>
</head>
<body>
<h1>Inserisci il Codice dell'Attrazione</h1>
<form action="giostre" method="post">
    <label for="codiceAttrazione">Codice Attrazione:</label>
    <input type="text" id="codiceAttrazione" name="codiceAttrazione" required>
    <button type="submit">Visualizza Dettagli</button>
</form>

<%
    attrazione attrazione = (attrazione) request.getAttribute("attrazione");
    String error = (String) request.getAttribute("error");

    if (attrazione != null) {
%>
<h2>Dettagli dell'Attrazione</h2>
<p><strong>Codice:</strong> <%= attrazione.getCodice() %></p>
<p><strong>Nome:</strong> <%= attrazione.getNome() %></p>
<p><strong>Tipologia:</strong> <%= attrazione.getTipologia() %></p>
<p><strong>Data Ultima Manutenzione:</strong> <%= attrazione.getData_ultima_manutenzione() %></p>
<%
} else if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<%
    }
%>
</body>
</html>

