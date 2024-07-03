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
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }
        header {
            /*text-align: center;
            margin: 20px 0;*/
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px 0;
        }
        header h1 {
            font-size: 2em;
            margin: 0;
        }
        nav {
            text-align: center;
            background-color: #333;
        }
        nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            /*display: inline;
            margin-right: 20px;*/
            display: inline-block;
        }
        nav ul li a {
            /*color: white;
            text-decoration: none;
            font-weight: bold;*/
            display: block;
            padding: 10px 20px;
            color: white;
            text-decoration: none;
            /*font-weight: bold;*/
        }
        nav ul li a:hover {
            background-color: #575757;
        }
        .content {
            /*text-align: center;
            margin-top: 20px;*/
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 20px;
        }

        .puntiRistoro {
            /*margin: 20px;
            display: inline-block;
            width: 45%;
            vertical-align: top;
            text-align: center;*/
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin: 20px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            overflow: hidden;
            transition: transform 0.2s;
        }
        .puntiRistoro:hover {
            transform: scale(1.05);
        }
        .puntiRistoro img {
            width: 300px; /* Larghezza fissa */
            height: 200px; /* Altezza fissa */
            object-fit: cover; /* Mantiene le proporzioni e riempie il contenitore */
        }
        .puntiRistoro h2 {
            background-color: #4CAF50;
            color: white;
            margin: 0;
            padding: 15px;
            font-size: 1.2em;
        }
        .puntiRistoro p {
            /*text-align: center;
            padding: 10px;*/
            padding: 15px;
            text-align: justify;
        }
        .puntiRistoro form {
            padding: 15px;
            text-align: center;
        }
        .puntiRistoro button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            font-size: 1em;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .puntiRistoro button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<header>
    <h1>Punti di ristoro</h1>
</header>

<nav>
    <ul>
        <li><a href="view.jsp"><strong>Home</strong></a></li>
        <li><a href="attrazioni.jsp"><strong>Attrazioni</strong></a></li>
        <li><a href="eventi.jsp"><strong>Eventi</strong></a></li>
        <li><a href="map.jsp"><strong>Mappa</strong></a></li>
        <li><a href="login.jsp"><strong>Login</strong></a></li>
    </ul>
</nav>

<div class="content">
    <div class="puntiRistoro">
        <h2>La Pergola - cucina italiana</h2>
        <img src="jsp/homeManagement/images/pizzeria.jpg" alt="Ristorante italiano">
        <p>Il ristorante "La Pergola" vi saprà degustare con i piatti tipici della cucina italiana.</p>
        <form action="menuItaliano.jsp" method="get">
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
        <form action="menuHamburger.jsp" method="get">
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
        <form action="menuGelato.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Drink Haven</h2>
        <img src="jsp/homeManagement/images/bibite.jpg" alt="Chiosco bibite">
        <p>Accaldato? Nessun problema, da Drink Haven potrai trovare qualsiasi tipo di bevanda per combattere anche le giornate più calde</p>
        <form action="menuBibite.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Waffle World</h2>
        <img src="jsp/homeManagement/images/waffle.jpg" alt="Waffle">
        <p>Concediti una dolce pausa gustando uno delizioni Waffle di Waffle World.</p>
        <form action="menuWaffle.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

<div class="content">
    <div class="puntiRistoro">
        <h2>Fry Frenzy</h2>
        <img src="jsp/homeManagement/images/fritti.jpg" alt="Friggitoria">
        <p>Fame improvvisa? Da noi potrai trovare molte sfiziosità per una pausa tra una giostra e l'altra</p>
        <form action="menuFritto.jsp" method="get">
            <input type="hidden">
            <button type="submit">Consultare il menù</button>
        </form>
    </div>
</div>

</body>
</html>
