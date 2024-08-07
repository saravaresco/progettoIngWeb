<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.biglietto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.controller.RegisterVisitorController" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.visitatore" %>
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
        a {
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center title">Area Personale Visitatore</h1>
    <form action="Dispatcher" method="get" id="retrieveTicketsForm">
        <input type="hidden" name="controllerAction" value="RegisterVisitorController.retrieveTickets">
        <button type="submit" class="btn btn-custom" id="showTickets">Biglietti Acquistati</button>
    </form>
    <button class="btn btn-custom" id="showModifyTickets">Modifica Biglietti Acquistati</button>
    <button class="btn btn-custom" id="showPurchaseTicket">Acquista Nuovo Biglietto</button>
    <form action="Dispatcher" method="get" id="viewInfo">
        <input type="hidden" name="controllerAction" value="RegisterVisitorController.showUserInfo">
        <button type="submit" class="btn btn-custom" id="showInfo">Visualizza i tuoi dati</button>
    </form>

    <button class="btn btn-custom" id="modifyInfo">Modifica i tuoi dati</button>


    <div id="ticketsSection" class="section">
        <h2>Biglietti Acquistati</h2>
        <div id="userTickets">
            <% List<biglietto> userTickets = (List<biglietto>) request.getAttribute("userTickets");
                if (userTickets != null && !userTickets.isEmpty()) {
                    for (biglietto ticket : userTickets) { %>
            <div class="ticket">
                <p><strong>ID Biglietto:</strong> <%= ticket.getID() %></p>
                <p><strong>Data Acquisto:</strong> <%= ticket.getData_acquisto() %></p>
                <p><strong>Tipologia 1:</strong> <%= ticket.getTipologia1() %></p>
                <p><strong>Tipologia 2:</strong> <%= ticket.getTipologia2() %></p>
                <p><strong>Prezzo:</strong> <%= ticket.getPrezzo() %></p>
            </div>
            <% }
            } else { %>
            <p>Nessun biglietto acquistato.</p>
            <% } %>
        </div>
    </div>

    <div id="modifyTicketsSection" class="section">
        <h2>Modifica Biglietti Acquistati</h2>

        <%--<% biglietto ticket = (biglietto) request.getAttribute("ticket"); %>--%>

        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="RegisterVisitorController.modifyTicket">
            <div class="form-group">
                <label for="idBiglietto">Inserisci l'ID del biglietto da modificare:</label>
                <input type="text" class="form-control" id="idBiglietto" name="idBiglietto" required>
            </div>
            <button type="submit" class="btn btn-primary">Modifica Biglietto</button>
        </form>
    </div>

    <div id="purchaseTicketSection" class="section">
        <h2>Acquista Nuovo Biglietto</h2>
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="RegisterVisitorController.newTicket">
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
                    <option value="Serale">Serale</option>
                    <option value="Giornaliero">Giornaliero</option>
                    <option value="Abbonamento">Abbonamento</option>
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

    <div id="userInfoSection" class="section">
        <h2>I tuoi dati</h2>
        <div class="user-info">
            <% visitatore visitatore = (visitatore) request.getAttribute("visitatore");
                if (visitatore != null) { %>
            <p><strong>Nome:</strong> <%= visitatore.getNome() %></p>
            <p><strong>Cognome:</strong> <%= visitatore.getCognome() %></p>
            <p><strong>Codice Fiscale:</strong> <%= visitatore.getCodice_fiscale() %></p>
            <p><strong>Data di nascita:</strong> <%= visitatore.getData_nascita() %></p>
            <p><strong>Sesso:</strong> <%= visitatore.getSesso() %></p>
            <p><strong>Username:</strong> <%= visitatore.getUsername() %></p>
            <p><strong>Password:</strong> <%= visitatore.getPassword() %></p>
            <% } else { %>
            <p>Nessun dato disponibile.</p>
            <% } %>
        </div>
    </div>

    <div id="modifyUserInfoSection" class="section">
        <h2>Modifica i tuoi dati</h2>
        <form action="Dispatcher" method="get">
            <input type="hidden" name="controllerAction" value="RegisterVisitorController.modifyUser">
            <button type="submit" class="btn btn-primary">Modifica Dati</button>
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



    document.getElementById('showModifyTickets').addEventListener('click', function () {
        toggleSection('modifyTicketsSection');
    });

    document.getElementById('showPurchaseTicket').addEventListener('click', function () {
        toggleSection('purchaseTicketSection');
    });

    document.getElementById('modifyInfo').addEventListener('click', function () {
        toggleSection('modifyUserInfoSection');
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

<div class="text-center">
    <a href="view.jsp">Ritorna alla pagina principale</a>
</div>

</body>
</html>
