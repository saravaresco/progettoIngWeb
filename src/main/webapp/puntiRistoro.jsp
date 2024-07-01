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
        <img src="jsp/homeManagement/images/pizzeria.jpg" alt="Ristorante italiano">
        <p>Il ristorante "La Pergola" vi saprà degustare con i piatti tipici della cucina italiana.</p>
        <form action="jsp/homeManagement/menuItaliano.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Burger Kingdom</h2>
        <img src="jsp/homeManagement/images/burger.jpg" alt="Chiosco gelati">
        <p>Burger Kingdom vi saprà deliziare con degli ottimi hambuerg in stile americano. Ottimo per chi non vuole perdere nemmeno un'istante del divertimento offerto dal parco</p>
        <form action="jsp/homeManagement/menuHamburger.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Gelato Mania</h2>
        <img src="jsp/homeManagement/images/gelato.jpg" alt="Ristorante americano">
        <p>Per una pausa rinfrescante Gelato Mania è il posto perfetto! Freschi e gustosi gelati artigianali saranno un'ottima merenda in qualsiasi momento della giornata</p>
        <form action="jsp/homeManagement/menuGelato.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Drink Haven</h2>
        <img src="jsp/homeManagement/images/bibite.jpg" alt="Chiosco bibite">
        <p></p>
        <form action="jsp/homeManagement/menuBibite.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Waffle World</h2>
        <img src="jsp/homeManagement/images/waffle.jpg" alt="Waffle">
        <p>Cocediti una dolce pausa gustando uno delizioni Waffle di Waffle World.</p>
        <form action="jsp/homeManagement/menuWaffle.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Fry Frenzy</h2>
        <img src="jsp/homeManagement/images/fritti.jpg" alt="Friggitoria">
        <p></p>
        <form action="jsp/homeManagement/menuFritto.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

</body>
</html>
