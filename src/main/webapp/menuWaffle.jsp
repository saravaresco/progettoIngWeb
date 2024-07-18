<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 22:38
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
            background-color: #e0e0e0;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        h1 {
            text-align: center;
            color: #ffffff;
            background-color: #004080;
            padding: 20px;
            margin: 0;
        }
        .menu-container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            flex-grow: 1;
            margin: 20px;
        }
        .menu-section {
            /*margin-bottom: 20px;*/
            background-color: #f2f2f2;
            border: 1px solid #ccc;
            border-radius: 8px;
            margin: 20px auto;
            padding: 20px;
            width: 400px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .menu-section h3 {
            /*border-bottom: 1px solid #ccc;
            padding-bottom: 5px;*/
            border-bottom: 2px solid #004080;
            color: #004080;
            padding-bottom: 5px;
            margin-bottom: 15px;
        }
        .menu-item {
            margin-bottom: 10px;
            color: #333333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li:before {
            content: "🍴";
            margin-right: 10px;
            color: #004080;
        }
        footer {
            background-color: #004080;
            color: white;
            text-align: center;
            padding: 10px 0;
            /*margin-top: 30px;*/
            width: 100%;
        }
        .back-button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #004080;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2em;
        }
        .back-button:hover {
            background-color: #004080;
        }
    </style>
</head>
<body>
<h1>Waffle World - Menù</h1>
<div class="menu-container">
    <div class="menu-section">
        <h3>Base</h3>
        <ul>
            <li class="menu-item">Classica </li>
            <li class="menu-item">al cioccolato</li>
            <li class="menu-item">senza lattosio </li>

        </ul>
    </div>

    <div class="menu-section">
        <h3>Topping</h3>
        <ul>
            <li class="menu-item">Sciroppo d'acero</li>
            <li class="menu-item">Cioccolato </li>
            <li class="menu-item">Crema alla vaniglia</li>
            <li class="menu-item">Salsa al caramello</li>
        </ul>
    </div>

    <div class="menu-section">
        <h3>Altri Extra</h3>
        <ul>
            <li class="menu-item">Pallina di gelato</li>
            <li class="menu-item">Fragole</li>
            <li class="menu-item">Smarties</li>
            <li class="menu-item">Granella di nocciola</li>
            <li class="menu-item">Panna</li>
        </ul>
    </div>


</div>
<a href="puntiRistoro.jsp" class="back-button">Indietro</a>
<footer>
    <p>&copy; 2024 Waffle World. Tutti i diritti riservati.</p>
</footer>

</body>
</html>
