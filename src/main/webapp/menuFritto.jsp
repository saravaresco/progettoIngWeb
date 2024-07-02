<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Menù del Ristorante</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        header {
            background-color: #FFC107;
            color: white;
            text-align: center;
            padding: 20px 0;
            margin-bottom: 30px;
        }
        header h1 {
            font-size: 2.5em;
            margin: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            flex: 1;
        }
        .menu-section {
            margin-bottom: 30px;
        }
        .menu-section h3 {
            /*border-bottom: 1px solid #ccc;
            padding-bottom: 5px;*/
            font-size: 1.8em;
            border-bottom: 2px solid #FFC107;
            padding-bottom: 10px;
            margin-bottom: 15px;
            color: #FFC107;
        }
        .menu-item {
            /*margin-bottom: 10px;*/
            font-size: 1.2em;
            padding: 5px 0;
            list-style-type: none;
        }
        .menu-item::before {
            content: '•';
            color: #FFC107;
            margin-right: 10px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        footer {
            background-color: #FFC107;
            color: white;
            text-align: center;
            padding: 10px 0;
           /* margin-top: 30px;*/
            width: 100%;
        }
        .back-button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #FFC107;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2em;
        }
        .back-button:hover {
            background-color: #FFC107;
        }
    </style>
</head>
<body>
<header>
    <h1>Fry Frenzy - Menù</h1>
</header>

<div class="container">
    <div class="menu-section">
        <h3>Patatine</h3>
        <ul>
            <li class="menu-item">Classiche <span style="float: right;">€4</span></li>
            <li class="menu-item">con Cheddar <span style="float: right;">€5</span></li>
            <li class="menu-item">con Bacon <span style="float: right;">€5</span></li>
            <li class="menu-item">con Bacon e Cheddar <span style="float: right;">€6</span></li>

        </ul>
    </div>

    <div class="menu-section">
        <h3>Altri stuzzichini</h3>
        <ul>
            <li class="menu-item">Olive all'ascolana <span style="float: right;">€5</span></li>
            <li class="menu-item">Stick di mozzarella <span style="float: right;">€5</span></li>
            <li class="menu-item">Anelli di cipolla <span style="float: right;">€5</span></li>
        </ul>
    </div>

    <a href="puntiRistoro.jsp" class="back-button">Indietro</a>
</div>


<footer>
    <p>&copy; 2024 Fry Frenzy. Tutti i diritti riservati.</p>
</footer>

</body>
</html>
