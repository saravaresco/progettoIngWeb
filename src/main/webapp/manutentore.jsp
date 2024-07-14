<%--<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.ripara" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manutenzione Attrazione</title>
</head>
<body>
<h1>Manutenzione Attrazione</h1>

<%
    ResultSet attrazione = (ResultSet) request.getAttribute("attrazione");
    List<ripara> interventi = (List<ripara>) request.getAttribute("interventi");
%>

<%
    if (attrazione != null) {
%>
<h2>Dati Tecnici</h2>
<p>Codice: <%= attrazione.getInt("CODICE") %></p>
<p>Nome: <%= attrazione.getString("NOME") %></p>
<p>Tipologia: <%= attrazione.getString("TIPOLOGIA") %></p>


<h2>Interventi Effettuati</h2>
<ul>
    <%
        if (interventi != null && !interventi.isEmpty()) {
            for (ripara intervento : interventi) {
    %>
    <li><%= intervento.getDescrizione() %> (Manutentore: <%= intervento.getCf_manutentore() %>)</li>
    <%
        }
    } else {
    %>
    <li>Nessun intervento effettuato.</li>
    <%
        }
    %>
</ul>

<h2>Nuovo Intervento</h2>
<form action="manutentore.jsp" method="post">
    <input type="hidden" name="codice_attrazione" value="<%= attrazione.getInt("CODICE") %>" />
    <p>
        <label for="cf_manutentore">Codice Fiscale Manutentore:</label>
        <input type="text" name="cf_manutentore" id="cf_manutentore" required />
    </p>
    <p>
        <label for="descrizione">Descrizione:</label>
        <textarea name="descrizione" id="descrizione" required></textarea>
    </p>
    <p>
        <input type="submit" value="Inserisci Intervento" />
    </p>
</form>
<%
} else {
%>
<p>Attrazione non trovata.</p>
<%
    }
%>
</body>
</html>
--%>
<%--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.controller.ManutenzioneController" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Interventi Attrazioni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .form-control {
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
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
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
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
        .btn-secondary {
            color: #fff;
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
        .mt-4 {
            margin-top: 1.5rem;
        }
        .text-center {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Gestione Interventi Attrazioni</h1>

    <h2>Visualizza Interventi</h2>
    <form action="manutentoreController" method="post">
        <input type="hidden" name="action" value="visualizza">
        <div class="form-group">
            <label for="codiceAttrazione">Codice Attrazione:</label>
            <input type="text" class="form-control" id="codiceAttrazione" name="codiceAttrazione" required>
        </div>
        <button type="submit" class="btn btn-primary">Visualizza Interventi</button>
    </form>

    <hr>

    <div id="inserisciIntervento" style="display: none;">
        <h2>Inserisci Nuovo Intervento</h2>
        <form action="manutentoreController" method="post">
            <input type="hidden" name="action" value="inserisci">
            <input type="hidden" name="codiceAttrazione" id="codiceAttrazioneInsert" value="">
            <div class="form-group">
                <label for="descrizione">Descrizione:</label>
                <input type="text" class="form-control" id="descrizione" name="descrizione" required>
            </div>
            <button type="submit" class="btn btn-success">Inserisci Intervento</button>
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            document.querySelector("#visualizzaInterventiBtn").addEventListener("click", function() {
                var codiceAttrazione = document.querySelector("#codiceAttrazione").value.trim();
                if (codiceAttrazione !== "") {
                    document.querySelector("#inserisciIntervento").style.display = "block";
                    document.querySelector("#codiceAttrazioneInsert").value = codiceAttrazione;
                } else {
                    alert("Inserisci il codice dell'attrazione prima di inserire un intervento.");
                }
            });
        });
    </script>

    <div class="text-center mt-4">
        <button id="visualizzaInterventiBtn" class="btn btn-secondary">Inserisci Intervento</button>
    </div>

    <%
        List<ripara> interventi = (List<ripara>) request.getAttribute("interventi");
        if (interventi != null && !interventi.isEmpty()) {
    %>
    <h2>Interventi Registrati</h2>
    <ul>
        <% for (ripara intervento : interventi) { %>
        <li><strong>Codice Attrazione:</strong> <%= intervento.getCodice_attrazione() %> | <strong>Descrizione:</strong> <%= intervento.getDescrizione() %></li>
        <% } %>
    </ul>
    <% } %>

</div>
</body>
</html>
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.ripara"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pagina Manutentore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h1, h2 {
            color: #333;
        }
        .center-buttons {
            text-align: center;
            margin-bottom: 30px;
        }
        button {
            background-color: #28a745;
            color: #fff;
            padding: 15px 25px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
        }
        button:hover {
            background-color: #218838;
        }
        .form-section {
            margin-bottom: 30px;
            display: none;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
    <script>
        function showField(fieldId) {
            var sections = document.getElementsByClassName('form-section');
            for (var i = 0; i < sections.length; i++) {
                sections[i].style.display = 'none';
            }
            document.getElementById(fieldId).style.display = 'block';
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Gestione Interventi Manutentore</h1>

    <div class="center-buttons">
        <form action="Dispatcher" method="get" id="retrieveInterventiForm">
            <input type="hidden" name="controllerAction" value="ManutenzioneController.retrieveInterventi">
            <button type="submit" onclick="showField('visualizzaInterventiM')">Visualizza Interventi del manutentore</button>
        </form>

        <button type="button" onclick="showField('visualizzaInterventi')">Visualizza Interventi per attrazione</button>
        <button type="button" onclick="showField('nuovoIntervento')">Inserisci Nuovo Intervento</button>
    </div>

    <div id="visualizzaInterventiM" class="form-section">
        <h2>Interventi del manutentore</h2>
        <div id="interventiManutentore">
            <% List<ripara> interventiManutentore = (List<ripara>) request.getAttribute("interventiManutentore");
                if(interventiManutentore != null && !interventiManutentore.isEmpty()){
                    for(ripara rip : interventiManutentore){%>
            <div class="rip">
                <p><strong>Codice fiscale:</strong> <%= rip.getCf_manutentore()%></p>
                <p><strong>Codice attrazione:</strong> <%= rip.getCodice_attrazione()%></p>
                <p><strong>Descrizione:</strong> <%= rip.getDescrizione()%></p>
            </div>
            <%}
            } else { %>
            <p>Nessun intervento effettuato</p>
            <% } %>
        </div>

    </div>

    <div id="visualizzaInterventi" class="form-section">
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="ManutenzioneController.retrieveInterventi">
            <h2>Visualizza Interventi</h2>
            <label for="codiceAttrazioneVisualizza">Codice Attrazione:</label>
            <input type="text" id="codiceAttrazioneVisualizza" name="codiceAttrazioneVisualizza">
            <button type="submit" name="action" value="visualizza">Visualizza Interventi</button>
        </form>
    </div>

    <div id="nuovoIntervento" class="form-section">
        <form action="ManutentoreController" method="post">
            <h2>Inserisci Nuovo Intervento</h2>
            <label for="codiceAttrazioneInserisci">Codice Attrazione:</label>
            <input type="text" id="codiceAttrazioneInserisci" name="codiceAttrazioneInserisci">
            <label for="descrizione">Descrizione:</label>
            <input type="text" id="descrizione" name="descrizione">
            <button type="submit" name="action" value="inserisci">Inserisci Intervento</button>
        </form>
    </div>

    <%
        List<ripara> interventi = (List<ripara>) request.getAttribute("interventi");
        if (interventi != null && !interventi.isEmpty()) {
    %>
    <h2>Interventi Effettuati</h2>
    <table>
        <tr>
            <th>CF Manutentore</th>
            <th>Codice Attrazione</th>
            <th>Descrizione</th>
        </tr>
        <%
            for (ripara intervento : interventi) {
        %>
        <tr>
            <td><%= intervento.getCf_manutentore() %></td>
            <td><%= intervento.getCodice_attrazione() %></td>
            <td><%= intervento.getDescrizione() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</div>
</body>
</html>

