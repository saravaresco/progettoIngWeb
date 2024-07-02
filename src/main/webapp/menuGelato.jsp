<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 22:07
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

        }
        header {
            background-color: #FFB6C1; /* Rosa chiaro */
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
            margin-bottom: 30px;
        }
        .menu-section h3 {
            /*border-bottom: 1px solid #ccc;
            padding-bottom: 5px;*/
            font-size: 1.8em;
            border-bottom: 2px solid #FFB6C1; /* Rosa chiaro */
            padding-bottom: 10px;
            margin-bottom: 15px;
            color: #FFB6C1; /* Rosa chiaro */
        }
        .menu-item {
            /*margin-bottom: 10px;*/
            font-size: 1.2em;
            padding: 5px 0;
            list-style-type: none;
        }
        .menu-item::before {
            content: '•';
            color: #FFB6C1; /* Rosa chiaro */
            margin-right: 10px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        .back-button {
            display: inline-block;
            background-color: #FFB6C1; /* Rosa chiaro */
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .back-button:hover {
            background-color: #FF98B4; /* Tonalità più scura di rosa */
        }
        footer {
            background-color: #FFB6C1; /* Rosa chiaro */
            color: white;
            text-align: center;
            padding: 10px 0;
            width: 100%;
            position: absolute;
            bottom: 0;
        }
    </style>
</head>
<body>
<header>
    <h1>Gelato Mania - Menù</h1>
</header>

<div class="container">
    <div class="menu-section">
        <h3>Gelati</h3>
        <ul>
            <li class="menu-item">Cono o coppetta 2 gusti <span style="float: right;">€3</span></li>
            <li class="menu-item">Cono o coppetta 3 gusti <span style="float: right;">€4</span></li>
            <li class="menu-item">Cono o coppetta 4 gusti <span style="float: right;">€5</span></li>

        </ul>
    </div>

    <div class="menu-section">
        <h3>Granite</h3>
        <ul>
            <li class="menu-item">Granita piccola <span style="float: right;">€3</span></li>
            <li class="menu-item">Granita media <span style="float: right;">€4.50</span></li>
            <li class="menu-item">Granita Grande <span style="float: right;">€6</span></li>
        </ul>
    </div>

    <div class="menu-section">
        <h3>Ghiacciolo</h3>
        <ul>
            <li class="menu-item">Piccolo <span style="float: right;">€2</span></li>
            <li class="menu-item">Grande <span style="float: right;">€4</span></li>
        </ul>
    </div>
</div>


    <a href="puntiRistoro.jsp" class="back-button">Indietro</a>

<footer>
    <p>&copy; 2024 Gelato Mania. Tutti i diritti riservati.</p>
</footer>

</body>
</html>
