<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 16/06/2024
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attrazioni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        header {
            text-align: center;
            margin: 20px 0;
            background-color: #4CAF50;
            color: white;
        }
        nav {
            text-align: center;
            background-color: #333;
            padding: 10px 0;
        }
        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            padding: 10px;
            transition: background-color 0.3s;
        }
        nav ul li a:hover {
            background-color: #555;
            border-radius: 5px;
        }
        .content {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 20px auto;
            max-width: 1200px;
            padding: 20px;
            /*text-align: center;
            margin-top: 20px;*/
        }
        .attraction {
            margin: 20px;
            flex: 1 1 calc(50% - 40px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
            /*margin: 20px;
            display: inline-block;
            width: 45%;
            vertical-align: top;
            text-align: center;*/
        }
        .attraction:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .attraction img {
            width: 300px; /* Larghezza fissa */
            height: 200px; /* Altezza fissa */
            object-fit: cover; /* Mantiene le proporzioni e riempie il contenitore */
        }
        .attraction h3 {
            margin: 0;
            padding: 15px;
            background-color: #4CAF50;
            color: white;
        }
        .attraction p {
            padding: 15px;
            margin: 0;
            color: #333;
            /*text-align: center;
            padding: 10px;*/
        }
    </style>
</head>
<body>
<header>
    <h1>Attrazioni del Parco</h1>
</header>

<nav>
    <ul>
        <li><a href="view.jsp">Home</a></li>
        <li><a href="puntiRistoro.jsp">Punti Ristoro</a></li>
        <li><a href="eventi.jsp">Eventi</a></li>
        <li><a href="map.jsp">Mappa</a></li>
        <li><a href="login.jsp">Login</a></li>
    </ul>
</nav>

<div class="content">
    <div class="attraction">
        <img src="jsp/homeManagement/images/attrazione1.jpg" alt="Montagne russe">
        <h3>Trenino Meraviglie</h3>
        <p>Questa attrazione è perfetta per chi cerca adrenalina e divertimento.</p>
    </div>
    <div class="attraction">
        <img src="jsp/homeManagement/images/attrazione3.jpg" alt="Giostre per i più piccoli">
        <h3>Giostra cavalli</h3>
        <p>Un'esperienza unica per tutta la famiglia, con tante attività da fare insieme.</p>
    </div>
    <div class="attraction">
        <img src="jsp/homeManagement/images/attrazione2.jpg" alt="Attrazioni acquatiche">
        <h3>Fiume rapido</h3>
        <p>Per chi vuole rinfrescarsi nelle giornate più calde.</p>
    </div>
    <div class="attraction">
        <img src="jsp/homeManagement/images/navePirati.jpg" alt="Nave dei Pirati">
        <h3>Nave dei Pirati</h3>
        <p>Sei pronto a scovare il tesoro dei pirati? All'arrembaggio!</p>
    </div>
    <div class="attraction">
        <img src="jsp/homeManagement/images/casaOrrori.jpg" alt="Casa deglo Orrori">
        <h3>Casa degli orrori</h3>
        <p>Preparati a tremare nella nostra spaventosa casa stregata. Sei abbastanza coraggioso?</p>
    </div>
    <div class="attraction">
        <img src="jsp/homeManagement/images/giostraPanoramica.jpg" alt="Ruota panoramica">
        <h3>Ruota Panoramica</h3>
        <p>Un classico senza tempo. Goditi un giro rilassante sulla nostra affascinante giostra.</p>
    </div>
</div>
</body>
</html>
