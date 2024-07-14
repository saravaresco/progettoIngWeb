<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    spettacolo spett = (spettacolo) request.getAttribute("spettacolo");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Spettacolo</title>
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
<h1 class="text-center">Modifica Spettacolo</h1>
<form action="Dispatcher" method="get">
    <input type="hidden" name="controllerAction" value="AttoreController.updateSpettacolo">
    <div class="form-group">
        <label>Nome:</label>
        <input type="text" name="nome" value="<%= spett != null ? spett.getNome() : "" %>" required />
    </div>
    <div>
        <label>Tipologia:</label>
        <input type="text" name="tipologia" value="<%= spett != null ? spett.getTipologia() : "" %>" required />
    </div>
    <div>
        <label>Data:</label>
        <input type="date" name="data" value="<%= spett != null ? spett.getData() : "" %>" required />
    </div>
    <div>
        <label>Luogo:</label>
        <input type="text" name="luogo" value="<%= spett != null ? spett.getLuogo() : "" %>" required />
    </div>
    <div>
        <label>Orario Inizio:</label>
        <input type="time" name="orario_inizio" value="<%= spett != null ? spett.getOrario_inizio() : "" %>" required />
    </div>
    <div>
        <label>Durata:</label>
        <input type="time" name="durata" value="<%= spett != null ? spett.getDurata() : "" %>" required />
    </div>
    <div>
        <button type="submit" class="btn btn-custom">Salva Modifiche</button>
    </div>
</form>
</div>
</body>
</html>
