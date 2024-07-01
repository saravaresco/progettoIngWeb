<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Personale Visitatore</title>
</head>
<body>
<h1>Area Personale Visitatore</h1>
<h2>Biglietti Acquistati</h2>

<%
    List<biglietto> userTickets = (List<biglietto>) session.getAttribute("userTickets");
    if (userTickets != null && !userTickets.isEmpty()) {
        for (biglietto ticket : userTickets) {
%>
<p>ID Biglietto: <%= ticket.getID() %></p>
<p>Data Acquisto: <%= ticket.getData_acquisto() %></p>
<p>Tipo: <%= ticket.getTipologia() %></p>
<p>Prezzo: <%= ticket.getPrezzo() %></p>
<hr>
<%
    }
} else {
%>
<p>Nessun biglietto acquistato.</p>
<%
    }
%>

<h2>Modifica Biglietti Acquistati</h2>
<p>Implementa qui la logica per modificare i biglietti, se necessario.</p>

<h2>Acquista Nuovi Biglietti</h2>
<form action="register" method="post">
    Tipo:
    <select name="ticketType">
        <option value="standard">Standard</option>
        <option value="vip">VIP</option>
        <option value="famiglia">Famiglia</option>
    </select>
    <button type="submit" name="action" value="purchaseTicket">Acquista</button>
</form>
</body>
</html>
