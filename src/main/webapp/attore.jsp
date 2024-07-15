
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
                document.getElementById("modifySection").style.display = 'none';
            });

            document.getElementById("insertButton").addEventListener("click", function() {
                document.getElementById("insertSection").style.display = 'block';
                document.getElementById("searchSection").style.display = 'none';
                document.getElementById("modifySection").style.display = 'none';
            });

            document.getElementById("modifyButton").addEventListener("click", function() {
                document.getElementById("modifySection").style.display = 'block';
                document.getElementById("searchSection").style.display = 'none';
                document.getElementById("insertSection").style.display = 'none'; // Nascondi anche la sezione di inserimento nuovo spettacolo
            });
        });


    </script>
</head>
<body>
<div class="container">
    <h1 class="text-center">Gestione Spettacoli</h1>
    <div class="text-center">
        <button id="searchButton" class="btn btn-primary">Cerca Spettacolo</button>
        <button id="modifyButton" class="btn btn-primary">Modifica Spettacolo</button>
        <button id="insertButton" class="btn btn-success">Inserisci Nuovo Spettacolo</button>
    </div>

    <div id="searchSection" class="hidden-section mt-4">
        <h2>Inserisci il Nome dello Spettacolo</h2>
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="AttoreController.getSpettacolo">
            <div class="form-group">
                <label for="codiceSpettacolo">Nome Spettacolo:</label>
                <input type="text" class="form-control" id="codiceSpettacolo" name="codiceSpettacolo" required>
            </div>
            <button type="submit" class="btn btn-primary">Visualizza Dettagli</button>
        </form>
    </div>

    <div id="modifySection" class="hidden-section mt-4">
        <h2>Modifica spettacolo</h2>
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="AttoreController.modifySpettacolo">
            <div class="form-group">
                <label for="spettacolo">Inserisci il nome dello spettacolo da modificare:</label>
                <input type="text" class="form-control" id="spettacolo" name="spettacolo" required>
            </div>
            <button type="submit" class="btn btn-primary">Modifica Biglietto</button>
        </form>

    </div>



    <div id="insertSection" class="hidden-section mt-4">
        <h2>Inserisci un Nuovo Spettacolo</h2>
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="AttoreController.insert">
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
                <input type="time" class="form-control" id="orarioInizioInsert" name="orarioInizio" step="1" required>
            </div>
            <div class="form-group">
                <label for="durataInsert">Durata:</label>
                <input type="time" class="form-control" id="durataInsert" name="durata" step="1" required>
            </div>
            <button type="submit" class="btn btn-success">Inserisci</button>
        </form>
    </div>


</div>

</body>
</html>

