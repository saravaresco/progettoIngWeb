
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
            <input type="hidden" name="controllerAction" value="ManutenzioneController.recuperaInterventi">
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

