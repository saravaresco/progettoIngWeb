<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.controller.RegisterVisitorController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Area Personale Visitatore</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9f7ef;
            padding-top: 20px;
        }
        .container {
            max-width: 900px;
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .ticket {
            border: 2px solid #28a745;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #d4edda;
        }
        .ticket p {
            margin: 0;
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
        .section {
            display: none;
            margin-top: 20px;
        }
        .title {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center title">Area Personale Visitatore</h1>
    <button class="btn btn-custom" id="showTickets">Biglietti Acquistati</button>
    <button class="btn btn-custom" id="showModifyTickets">Modifica Biglietti Acquistati</button>
    <button class="btn btn-custom" id="showPurchaseTicket">Acquista Nuovo Biglietto</button>

    <div id="ticketsSection" class="section">
        <h2>Biglietti Acquistati</h2>
        <div class="mb-4">
            <%
                String codiceFiscale = (String) session.getAttribute("codiceFiscale");
                RegisterVisitorController controller = new RegisterVisitorController();
                List<biglietto> userTickets = controller.getUserTickets(codiceFiscale);
                if (userTickets != null && !userTickets.isEmpty()) {
                    for (biglietto ticket : userTickets) {
            %>
            <div class="ticket">
                <p><strong>ID Biglietto:</strong> <%= ticket.getID() %></p>
                <p><strong>Data Acquisto:</strong> <%= ticket.getData_acquisto() %></p>
                <p><strong>Tipologia 1:</strong> <%= ticket.getTipologia1() %></p>
                <p><strong>Tipologia 2:</strong> <%= ticket.getTipologia2() %></p>
                <p><strong>Prezzo:</strong> <%= ticket.getPrezzo() %></p>
            </div>
            <%
                }
            } else {
            %>
            <p>Nessun biglietto acquistato.</p>
            <%
                }
            %>
        </div>
    </div>

    <div id="modifyTicketsSection" class="section">
        <h2>Modifica Biglietti Acquistati</h2>

        <%--<% biglietto ticket = (biglietto) request.getAttribute("ticket"); %>--%>

        <form action="modify-ticket" method="post">
            <div class="form-group">
                <label for="idBiglietto">Inserisci l'ID del biglietto da modificare:</label>
                <input type="text" class="form-control" id="idBiglietto" name="idBiglietto" required>
            </div>
            <button type="submit" class="btn btn-primary">Modifica Biglietto</button>
        </form>
    </div>

    <div id="purchaseTicketSection" class="section">
        <h2>Acquista Nuovo Biglietto</h2>
        <form action="purchaseTicket" method="post">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>
            <div class="form-group">
                <label for="cognome">Cognome:</label>
                <input type="text" class="form-control" id="cognome" name="cognome" required>
            </div>
            <div class="form-group">
                <label for="codiceFiscale">Codice Fiscale:</label>
                <input type="text" class="form-control" id="codiceFiscale" name="codiceFiscale" required>
            </div>
            <div class="form-group">
                <label for="mail">Mail:</label>
                <input type="email" class="form-control" id="mail" name="mail" required>
            </div>
            <div class="form-group">
                <label for="tipologia1">Tipologia di biglietto:</label>
                <select class="form-control" id="tipologia1" name="tipologia1" required>
                    <option value="serale">Serale</option>
                    <option value="giornaliero">Giornaliero</option>
                    <option value="abbonamento">Abbonamento</option>
                </select>
            </div>
            <div class="form-group">
                <label for="tipologia2">Tipo di Prezzo:</label>
                <select class="form-control" id="tipologia2" name="tipologia2" required>
                    <option value="intero">Intero</option>
                    <option value="ridotto">Ridotto</option>
                </select>
            </div>
            <div class="form-group">
                <label for="metodoPagamento">Metodo di Pagamento:</label>
                <select class="form-control" id="metodoPagamento" name="metodoPagamento" required>
                    <option value="cassa">Paga alla cassa</option>
                    <option value="carta">Carta di Credito</option>
                </select>
            </div>
            <div id="datiCarta" style="display: none;">
                <div class="form-group">
                    <label for="numeroCarta">Numero Carta:</label>
                    <input type="text" class="form-control" id="numeroCarta" name="numeroCarta">
                </div>
                <div class="form-group">
                    <label for="scadenzaCarta">Scadenza Carta (MM/YY):</label>
                    <input type="text" class="form-control" id="scadenzaCarta" name="scadenzaCarta">
                </div>
                <div class="form-group">
                    <label for="cvv">CVV:</label>
                    <input type="text" class="form-control" id="cvv" name="cvv">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Acquista Biglietto</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.getElementById('metodoPagamento').addEventListener('change', function () {
        var datiCarta = document.getElementById('datiCarta');
        if (this.value === 'carta') {
            datiCarta.style.display = 'block';
        } else {
            datiCarta.style.display = 'none';
        }
    });

    document.getElementById('showTickets').addEventListener('click', function () {
        toggleSection('ticketsSection');
    });

    document.getElementById('showModifyTickets').addEventListener('click', function () {
        toggleSection('modifyTicketsSection');
    });

    document.getElementById('showPurchaseTicket').addEventListener('click', function () {
        toggleSection('purchaseTicketSection');
    });

    function toggleSection(sectionId) {
        var sections = document.querySelectorAll('.section');
        sections.forEach(function (section) {
            section.style.display = 'none';
        });

        var section = document.getElementById(sectionId);
        section.style.display = 'block';
    }
</script>
</body>
</html>
