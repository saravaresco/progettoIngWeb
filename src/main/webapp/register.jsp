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
<p>Tipo1: <%= ticket.getTipologia1() %></p>
<p>Tipo2: <%= ticket.getTipologia2() %></p>
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

<h2>Acquista Nuovo Biglietto</h2>
<form action="purchaseTicket" method="post">
    <%--@declare id="tipoprezzo"--%>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="cognome" required><br>

    <label for="codiceFiscale">Codice Fiscale:</label>
    <input type="text" id="codiceFiscale" name="codiceFiscale" required><br><br>

    <label for="mail">Mail:</label>
    <input type="email" id="mail" name="mail" required><br><br>

    <label for="tipologia1">Tipologia di biglietto:</label>
    <select id="tipologia1" name="tipologia1" required>
        <option value="serale">Serale</option>
        <option value="giornaliero">Giornaliero</option>
        <option value="abbonamento">Abbonamento</option>
    </select><br><br>

    <label for="tipologia2">Tipo di Prezzo:</label>
    <select id="tipologia2" name="tipologia2" required>
        <option value="intero">Intero</option>
        <option value="ridotto">Ridotto</option>
    </select><br>

        <label for="metodoPagamento">Metodo di Pagamento:</label>
        <select id="metodoPagamento" name="metodoPagamento" required>
            <option value="cassa">Paga alla cassa</option>
            <option value="carta">Carta di Credito</option>
        </select><br>

        <div id="datiCarta" style="display: none;">
            <label for="numeroCarta">Numero Carta:</label>
            <input type="text" id="numeroCarta" name="numeroCarta"><br>

            <label for="scadenzaCarta">Scadenza Carta (MM/YY):</label>
            <input type="text" id="scadenzaCarta" name="scadenzaCarta"><br>

            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv"><br>
        </div>

        <button type="submit">Acquista Biglietto</button>
</form>

<script>
    document.getElementById('metodoPagamento').addEventListener('change', function () {
        var datiCarta = document.getElementById('datiCarta');
        if (this.value === 'carta') {
            datiCarta.style.display = 'block';
        } else {
            datiCarta.style.display = 'none';
        }
    });
</script>


</form>
</body>
</html>
