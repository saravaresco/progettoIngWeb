<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Punti di ristoro</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        header {
            text-align: center;
            margin: 20px 0;
        }
        nav {
            text-align: center;
            background-color: #333;
        }
        nav ul {
            list-style: none;
            padding: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        nav ul li a:hover {
            text-decoration: underline;
        }
        .content {
            text-align: center;
            margin-top: 20px;
        }
        .puntiRistoro {
            margin: 20px;
            display: inline-block;
            width: 45%;
            vertical-align: top;
            text-align: center;
        }
        .puntiRistoro img {
            width: 300px; /* Larghezza fissa */
            height: 200px; /* Altezza fissa */
            object-fit: cover; /* Mantiene le proporzioni e riempie il contenitore */
        }
        .puntiRistoro p {
            text-align: center;
            padding: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Punti di ristoro</h1>
</header>



<div class="content">
    <div class="puntiRistoro">
        <h2>La Pergola - cucina italiana</h2>
        <img src="images/pizzeria.jpg" alt="Ristorante italiano">
        <p>Il ristorante "La Pergola" vi saprà degustare con i piatti tipici della cucina italiana.</p>
        <form action="menuItaliano.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>

</div>
</body>
</html>
