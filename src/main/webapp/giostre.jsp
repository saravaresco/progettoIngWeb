<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.attrazione" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli Attrazione</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        h1, h2 {
            color: #2c3e50;
        }
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .details {
            background-color: #ecf0f1;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Inserisci il Codice dell'Attrazione</h1>
    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="GiostreController.getAttrazione">
        <label for="codiceAttrazione">Codice Attrazione:</label>
        <input type="text" id="codiceAttrazione" name="codiceAttrazione" required>
        <button type="submit">Visualizza Dettagli</button>
    </form>

    <%
        attrazione attrazione = (attrazione) request.getAttribute("attrazione");
        String error = (String) request.getAttribute("error");

        if (attrazione != null) {
    %>
    <div class="details">
        <h2>Dettagli dell'Attrazione</h2>
        <p><strong>Codice:</strong> <%= attrazione.getCodice() %></p>
        <p><strong>Nome:</strong> <%= attrazione.getNome() %></p>
        <p><strong>Tipologia:</strong> <%= attrazione.getTipologia() %></p>
        <p><strong>Data Ultima Manutenzione:</strong> <%= attrazione.getData_ultima_manutenzione() %></p>
    </div>
    <%
    } else if (error != null) {
    %>
    <p style="color: red;"><%= error %></p>
    <%
        }
    %>
</div>
</body>
</html>

