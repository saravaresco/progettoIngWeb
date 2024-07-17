<%--
  Created by IntelliJ IDEA.
  User: sveva
  Date: 16/07/24
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Dati Modificati</title>
    <script src="https://cdn.jsdelivr.net/npm/js-confetti@latest/dist/js-confetti.browser.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            text-align: center;
            margin: 50px;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
        }
        h2 {
            color: #4CAF50;
        }
        p {
            font-size: 18px;
            line-height: 1.6;
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
<h2>Dati aggiornati con successo</h2>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var confettiSettings = {
            target: 'my-canvas'
        };
        var confetti = new JSConfetti(confettiSettings);
        confetti.addConfetti();
    });
</script>
<p>Ti confermiamo che la modifica da te effettuata &#232; terminata con successo, se dovessi riscontrare delle anomalie non esitare a contattarci!.</p>
<a href="register.jsp">Ritorna alla tua pagina personale</a>
<a href="view.jsp">Ritorna alla pagina principale</a>
</body>
</html>
