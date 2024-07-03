<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 21:59
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
            background-color: #3498db; /* Rosa chiaro */
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
        }
        .menu-section {
            margin-bottom: 20px;
        }
        .menu-section h3 {
            border-bottom: 1px solid #ccc;
            padding-bottom: 5px;
            color: #e74c3c;
        }
        .menu-item {
            margin-bottom: 10px;
        }
        .menu-item span {
            float: right;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        .back-button {
            display: block;
            background-color: #e74c3c;
            color: white;
            width: 200px;
            margin: 20px auto;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2em;
        }
        .back-button:hover {
            background-color: #c0392b; /* Tonalità più scura di rosso */
        }
        footer {
            background-color: #3498db; /* Blu */
            color: white;
            text-align: center;
            padding: 20px 0;
            width: 100%;

        }
    </style>
</head>
<body>
<header>
    <h1>Burger Kingdom - Menù</h1>
</header>

<div class="container">
    <div class="menu-section">
        <h3>Sfiziosità</h3>
        <ul>
            <li class="menu-item">Nuggets di pollo <span style="float: right;">€10</span></li>
            <li class="menu-item">Stick di mozzarella <span style="float: right;">€7</span></li>
            <li class="menu-item">Anelli di cipolla <span style="float: right;">€7</span></li>
            <li class="menu-item">Olive all'ascolana <span style="float: right;">€7</span></li>
            <li class="menu-item">Alette di pollo <span style="float: right;">€10</span></li>
            <li class="menu-item">Patatine <span style="float: right;">€5</span></li>

        </ul>
    </div>

    <div class="menu-section">
        <h3>Burger</h3>
        <ul>
            <li class="menu-item">Classic Burger <span style="float: right;">€12</span></li>
            <li class="menu-item">CheeseBurger <span style="float: right;">€14</span></li>
            <li class="menu-item">BaconBurger <span style="float: right;">€15</span></li>
            <li class="menu-item">VeggieBurger <span style="float: right;">€14</span></li>
            <li class="menu-item">ChickenBurger <span style="float: right;">€14</span></li>
        </ul>
    </div>


    <div class="menu-section">
        <h3>Bevande</h3>
        <ul>
            <li class="menu-item">Coca-cola <span style="float: right;">€3</span></li>
            <li class="menu-item">Thè alla pesca o al limone <span style="float: right;">€3</span></li>
            <li class="menu-item">Birra alla spina <span style="float: right;">€4</span></li>
            <li class="menu-item">Calice di vino rosso o bianco <span style="float: right;">€6</span></li>
            <li class="menu-item">Acqua naturale o frizzante <span style="float: right;">€2</span></li>
        </ul>
    </div>
    <a href="puntiRistoro.jsp" class="back-button">Indietro</a>
</div>


<footer>
    <p>&copy; 2024 Burger Kingdom. Tutti i diritti riservati.</p>
</footer>

</body>
</html>
