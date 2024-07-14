<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Biglietto</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f7ef;
            padding-top: 20px;
        }
        .container {
            max-width: 600px;
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            margin: auto;
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 20px;
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
    <h1 class="text-center">Modifica Biglietto</h1>

    <% biglietto ticket = (biglietto) request.getAttribute("ticket"); %>

    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="RegisterVisitorController.updateTicket">


        <div class="form-group">
            <label for="codiceFiscale">Codice Fiscale:</label>
            <input type="text" class="form-control" id="codiceFiscale" name="codiceFiscale" value="<%= ticket.getCodice_fiscale() %>" readonly>
        </div>

        <div class="form-group">
            <label for="prezzo">Prezzo:</label>
            <input type="text" class="form-control" id="prezzo" name="prezzo" value="<%= ticket.getPrezzo() %>" readonly>
        </div>

        <div class="form-group">
            <label for="dataAcquisto">Data Acquisto:</label>
            <input type="text" class="form-control" id="dataAcquisto" name="dataAcquisto" value="<%= ticket.getData_acquisto() %>" readonly>
        </div>

        <div class="form-group">
            <label for="tipologia1">Tipologia 1:</label>
            <input type="text" class="form-control" id="tipologia1" name="tipologia1" value="<%= ticket.getTipologia1() %>" readonly>
        </div>

        <div class="form-group">
            <label for="tipologia2">Tipologia 2:</label>
            <input type="text" class="form-control" id="tipologia2" name="tipologia2" value="<%= ticket.getTipologia2() %>" readonly>
        </div>

        <div class="form-group">
            <label for="mail">Mail:</label>
            <input type="email" class="form-control" id="mail" name="mail" value="<%= ticket.getMail() %>" required>
        </div>

        <button type="submit" class="btn btn-custom">Salva Modifiche</button>
    </form>
</div>
</body>
</html>


