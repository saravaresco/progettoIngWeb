<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 22:18
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
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        header {
            background-color: #006400;
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
            /*margin-bottom: 20px;*/
            margin-bottom: 30px;
        }
        .menu-section h3 {
            /*border-bottom: 1px solid #ccc;
            padding-bottom: 5px;*/
            font-size: 1.8em;
            border-bottom: 2px solid #006400;
            padding-bottom: 10px;
            margin-bottom: 15px;
            color: #006400;
        }
        .menu-item {
            /*margin-bottom: 10px;*/
            font-size: 1.2em;
            padding: 5px 0;
            list-style-type: none;
        }
        .menu-item::before {
            content: '•';
            color: #006400;
            margin-right: 10px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        footer {
            background-color: #006400;
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
            background-color: #006400;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2em;
        }
        .back-button:hover {
            background-color: #004d00;
        }
    </style>
</head>
<body>
<header>
    <h1>Drink Haven - Menù</h1>
</header>


<div class="container">
    <div class="menu-section">
        <h3>Bibite in lattina </h3>
        <ul>
            <li class="menu-item">Coca cola <span style="float: right;">€3</span> </li>
            <li class="menu-item">Thè alla pesca o al limone <span style="float: right;">€3</span></li>
            <li class="menu-item">Fanta <span style="float: right;">€3</span></li>
            <li class="menu-item">Sprite <span style="float: right;">€3</span></li>

        </ul>
    </div>

    <div class="menu-section">
        <h3>Birre</h3>
        <ul>
            <%--<li class="menu-item">Biona 0.3 €4</li>--%>
            <li class="menu-item">Bionda 0.3 <span style="float: right;">€4</span></li>
                <li class="menu-item">Bionda 0.5 <span style="float: right;">€6</span></li>
                <li class="menu-item">Rossa 0.3 <span style="float: right;">€4</span></li>
                <li class="menu-item">Rossa 0.5 <span style="float: right;">€6</span></li>
                <li class="menu-item">Radler <span style="float: right;">€5</span></li>
        </ul>
    </div>

<a href="puntiRistoro.jsp" class="back-button">Indietro</a>
</div>

<footer>
    <p>&copy; 2024 Drink Haven. Tutti i diritti riservati.</p>
</footer>

</body>
</html>
