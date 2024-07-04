
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Intestazioni HTML e stili CSS -->
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Modifica Biglietto</h1>

    <% biglietto ticket = (biglietto) request.getAttribute("ticket"); %>

    <form action="modify-ticket" method="post">
        <div class="form-group">
            <label for="idBiglietto">ID Biglietto:</label>
            <input type="text" class="form-control" id="idBiglietto" name="idBiglietto" value="<%= ticket.getID() %>" readonly>
        </div>
        <div class="form-group">
            <label for="dataAcquisto">Data Acquisto:</label>
            <input type="text" class="form-control" id="dataAcquisto" name="dataAcquisto" value="<%= ticket.getData_acquisto() %>" readonly>
        </div>
        <div class="form-group">
            <label for="tipologia1">Tipologia 1:</label>
            <input type="text" class="form-control" id="tipologia1" name="tipologia1" value="<%= ticket.getTipologia1() %>">
        </div>
        <div class="form-group">
            <label for="tipologia2">Tipologia 2:</label>
            <input type="text" class="form-control" id="tipologia2" name="tipologia2" value="<%= ticket.getTipologia2() %>">
        </div>
        <div class="form-group">
            <label for="prezzo">Prezzo:</label>
            <input type="text" class="form-control" id="prezzo" name="prezzo" value="<%= ticket.getPrezzo() %>">
        </div>
        <button type="submit" class="btn btn-custom">Salva Modifiche</button>
    </form>
</div>
</body>
</html>
