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
        .attraction {
            margin: 20px;
            display: inline-block;
            width: 45%;
            vertical-align: top;
            text-align: center;
        }
        .attraction img {
            width: 300px; /* Larghezza fissa */
            height: 200px; /* Altezza fissa */
            object-fit: cover; /* Mantiene le proporzioni e riempie il contenitore */
        }
        .attraction p {
            text-align: center;
            padding: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Attrazioni del Parco</h1>
</header>



<div class="content">
    <div class="attraction">
        <img src="images/attrazione1.jpg" alt="Montagne russe">
        <h3>Montagne Russe</h3>
        <p>Questa attrazione è perfetta per chi cerca adrenalina e divertimento.</p>
    </div>
    <div class="attraction">
        <img src="images/attrazione3.jpg" alt="Giostre per i più piccoli">
        <h3>Giosta cavalli</h3>
        <p>Un'esperienza unica per tutta la famiglia, con tante attività da fare insieme.</p>
    </div>
    <div class="attraction">
        <img src="images/attrazione2.jpg" alt="Attrazioni acquatiche">
        <h3>Attrazioni acquatiche</h3>
        <p>Per chi vuole rinfrescarsi nelle giornate più calde.</p>
    </div>
    <div class="attraction">
        <img src="images/navePirati.jpg" alt="Nave dei Pirati">
        <h3>Nave dei pirati</h3>
        <p>Sei pronto a scovare il tesoro dei pirati? All'arrembaggio!</p>
    </div>
    <div class="attraction">
        <img src="images/casaOrrori.jpg" alt="Casa deglo Orrori">
        <h3>Casa degli orrori</h3>
        <p>Preparati a tremare nella nostra spaventosa casa stregata. Sei abbastanza coraggioso?</p>
    </div>
    <div class="attraction">
        <img src="images/giostraPanoramica.jpg" alt="Ruota panoramica">
        <h3>Ruota Panoramica</h3>
        <p>Un classico senza tempo. Goditi un giro rilassante sulla nostra affascinante giostra.</p>
    </div>
</div>
</body>
</html>
