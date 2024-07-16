<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.visitatore" %>
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

    <% visitatore visitatore = (visitatore) request.getAttribute("visit"); %>

    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="RegisterVisitorController.updateUser">
        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" class="form-control" id="nome" name="nome" value="<%= visitatore.getNome() %>" required>
        </div>
        <div class="form-group">
            <label for="cognome">Cognome:</label>
            <input type="text" class="form-control" id="cognome" name="cognome" value="<%= visitatore.getCognome()  %>" required>
        </div>
        <div class="form-group">
            <label for="codiceFiscale">Codice Fiscale:</label>
            <input type="text" class="form-control" id="codiceFiscale" name="codiceFiscale" value="<%=  visitatore.getCodice_fiscale() %>" readonly>
        </div>
        <div class="form-group">
            <label for="dataNascita">Data di Nascita:</label>
            <input type="date" class="form-control" id="dataNascita" name="dataNascita" value="<%= visitatore.getData_nascita() %>" required>
        </div>
        <div class="form-group">
            <label for="sesso">Sesso:</label>
            <select class="form-control" id="sesso" name="sesso" required>
                <option value="M" <%= visitatore != null && "M".equals(visitatore.getSesso()) ? "selected" : "" %>>Maschile</option>
                <option value="F" <%= visitatore != null && "F".equals(visitatore.getSesso()) ? "selected" : "" %>>Femminile</option>
            </select>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" value="<%=visitatore.getUsername() %>" readonly>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" value="<%= visitatore.getPassword() %>" readonly>
        </div>
        <button type="submit" class="btn btn-primary">Aggiorna Dati</button>
    </form>
</div>
</body>
</html>
