<%--<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo" %>
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
--%>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Spettacoli</title>
    <style>
        /* Bootstrap CSS */
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            /*width: 100%;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;*/
            max-width: 800px;
            margin: 50px auto;
            padding: 40px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .btn {
            display: inline-block;
            font-weight: 400;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            user-select: none;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 0.25rem;
            transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .btn-primary {
            color: #fff;
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-primary:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }
        .btn-success {
            color: #fff;
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-success:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }
        .btn-warning {
            color: #212529;
            background-color: #ffc107;
            border-color: #ffc107;
        }
        .btn-warning:hover {
            background-color: #e0a800;
            border-color: #d39e00;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .form-control {
            display: block;
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .form-control:focus {
            border-color: #80bdff;
            outline: 0;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .text-center {
            text-align: center;
        }
        .mt-4 {
            margin-top: 1.5rem;
        }
        .hidden-section {
            display: none;
        }
        h1, h2 {
            color: #343a40;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("searchButton").addEventListener("click", function() {
                document.getElementById("searchSection").style.display = 'block';
                document.getElementById("insertSection").style.display = 'none';
            });
            document.getElementById("insertButton").addEventListener("click", function() {
                document.getElementById("insertSection").style.display = 'block';
                document.getElementById("searchSection").style.display = 'none';
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1 class="text-center">Gestione Spettacoli</h1>
    <div class="text-center">
        <button id="searchButton" class="btn btn-primary">Cerca Spettacolo</button>
        <button id="insertButton" class="btn btn-success">Inserisci Nuovo Spettacolo</button>
    </div>

    <div id="searchSection" class="hidden-section mt-4">
        <h2>Inserisci il Codice dello Spettacolo</h2>
        <form action="attore" method="post">
            <input type="hidden" name="action" value="view">
            <div class="form-group">
                <label for="codiceSpettacolo">Codice Spettacolo:</label>
                <input type="text" class="form-control" id="codiceSpettacolo" name="codiceSpettacolo" required>
            </div>
            <button type="submit" class="btn btn-primary">Visualizza Dettagli</button>
        </form>
        <%
            spettacolo spettacolo = (spettacolo) request.getAttribute("spettacolo");
            String error = (String) request.getAttribute("error");

            if (spettacolo != null) {
        %>
        <h2>Dettagli dello Spettacolo</h2>
        <form action="attore" method="post">
            <div class="form-group">
                <label for="nomeUpdate">Nome:</label>
                <input type="text" class="form-control" id="nomeUpdate" name="nome" value="<%= spettacolo.getNome() %>" required>
            </div>
            <div class="form-group">
                <label for="tipologiaUpdate">Tipologia:</label>
                <input type="text" class="form-control" id="tipologiaUpdate" name="tipologia" value="<%= spettacolo.getTipologia() %>" required>
            </div>
            <div class="form-group">
                <label for="dataUpdate">Data:</label>
                <input type="date" class="form-control" id="dataUpdate" name="data" value="<%= spettacolo.getData() %>" required>
            </div>
            <div class="form-group">
                <label for="luogoUpdate">Luogo:</label>
                <input type="text" class="form-control" id="luogoUpdate" name="luogo" value="<%= spettacolo.getLuogo() %>" required>
            </div>
            <div class="form-group">
                <label for="orarioInizioUpdate">Orario Inizio:</label>
                <input type="time" class="form-control" id="orarioInizioUpdate" name="orarioInizio" value="<%= spettacolo.getOrario_inizio() %>" required>
            </div>
            <div class="form-group">
                <label for="durataUpdate">Durata:</label>
                <input type="time" class="form-control" id="durataUpdate" name="durata" value="<%= spettacolo.getDurata() %>" required>
            </div>
            <button type="submit" class="btn btn-warning">Modifica</button>
        </form>
        <%
        } else if (error != null) {
        %>
        <p style="color: red;"><%= error %></p>
        <%
            }
        %>
    </div>

    <div id="insertSection" class="hidden-section mt-4">
        <h2>Inserisci un Nuovo Spettacolo</h2>
        <form action="attore" method="post">
            <input type="hidden" name="action" value="insert">
            <div class="form-group">
                <label for="nomeInsert">Nome:</label>
                <input type="text" class="form-control" id="nomeInsert" name="nome" required>
            </div>
            <div class="form-group">
                <label for="tipologiaInsert">Tipologia:</label>
                <input type="text" class="form-control" id="tipologiaInsert" name="tipologia" required>
            </div>
            <div class="form-group">
                <label for="dataInsert">Data:</label>
                <input type="date" class="form-control" id="dataInsert" name="data" required>
            </div>
            <div class="form-group">
                <label for="luogoInsert">Luogo:</label>
                <input type="text" class="form-control" id="luogoInsert" name="luogo" required>
            </div>
            <div class="form-group">
                <label for="orarioInizioInsert">Orario Inizio:</label>
                <input type="time" class="form-control" id="orarioInizioInsert" name="orarioInizio" required>
            </div>
            <div class="form-group">
                <label for="durataInsert">Durata:</label>
                <input type="time" class="form-control" id="durataInsert" name="durata" required>
            </div>
            <button type="submit" class="btn btn-success">Inserisci</button>
        </form>
    </div>
</div>
</body>
</html>

