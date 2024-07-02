<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 17/06/2024
  Time: 19:01
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
      font-family: 'Garamond', serif;
      background-color: #f8f8f8;
      margin: 0;
      padding: 0;
      color: #333;
    }
    header {
      background-color: #b22222;
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
      border-bottom: 2px solid #b22222;
      padding-bottom: 10px;
      margin-bottom: 15px;
      color: #b22222;
    }
    .menu-item {
      /*margin-bottom: 10px;*/
      font-size: 1.2em;
      padding: 5px 0;
    }
    .menu-item span {
      float: right;
      color: #b22222;
    }
    ul {
      list-style-type: none;
      padding: 0;
    }
    footer {
      background-color: #b22222;
      color: white;
      text-align: center;
      padding: 10px 0;
      margin-top: 30px;
    }
    .back-button {
      display: block;
      width: 200px;
      margin: 20px auto;
      padding: 10px 20px;
      background-color: #b22222;
      color: white;
      text-align: center;
      text-decoration: none;
      border-radius: 5px;
      font-size: 1.2em;
    }
    .back-button:hover {
      background-color: #a32020;
    }
  </style>
</head>
<body>
<header>
  <h1>Ristorante "La Pergola" - Menù</h1>
</header>

<div class="container">


  <div class="menu-section">
    <h3>Antipasti</h3>
    <ul>
      <li class="menu-item">Affettati misti €12 </li>
      <li class="menu-item">Bruschetta €8</li>
      <li class="menu-item">Caprese €10</li>

    </ul>
  </div>

  <div class="menu-section">
    <h3>Primi</h3>
    <ul>
      <li class="menu-item">Pasta al pomodoro e basilico €11</li>
      <li class="menu-item">Amatriciana €14 </li>
      <li class="menu-item">Carbonara €14</li>
      <li class="menu-item">Cacio e Pepe €14</li>
      <li class="menu-item">Lasagne €15</li>
      <li class="menu-item">Trofie al pesto €13</li>
    </ul>
  </div>

  <div class="menu-section">
    <h3>Secondi</h3>
    <ul>
      <li class="menu-item">Pollo ai ferri €12</li>
      <li class="menu-item">Tagliata di Manzo €16</li>
      <li class="menu-item">Cotoletta alla Milanese €15</li>
      <li class="menu-item">Salsiccia alla griglia €14</li>
    </ul>
  </div>

  <div class="menu-section">
    <h3>Contorni</h3>
    <ul>
      <li class="menu-item">Patatine fritte €5</li>
      <li class="menu-item">Patate al forno €6</li>
      <li class="menu-item">Insalata mista €6</li>
      <li class="menu-item">Verdure grigliate €8</li>
    </ul>
  </div>

  <div class="menu-section">
    <h3>Pizze</h3>
    <ul>
      <li class="menu-item">Margherita €6</li>
      <li class="menu-item">Salamino Piccante €7</li>
      <li class="menu-item">Capricciosa €8</li>
      <li class="menu-item">Quattro formaggi €8</li>
      <li class="menu-item">Prosciutta crudo €9</li>
      <li class="menu-item">Tonno e cipolla €8</li>
      <li class="menu-item">Prosciutto cotto e funghi €8</li>
    </ul>
  </div>

  <div class="menu-section">
    <h3>Dolci</h3>
    <ul>
      <li class="menu-item">Tiramisù €6</li>
      <li class="menu-item">Millefoglie €6</li>
      <li class="menu-item">Salame al cioccolato €6</li>
      <li class="menu-item">Gelato della casa €5</li>
      <li class="menu-item">Tortino al cioccolato €6</li>
    </ul>
  </div>

  <div class="menu-section">
    <h3>Bevande</h3>
    <ul>
      <li class="menu-item">Coca-cola €3</li>
      <li class="menu-item">Thè alla pesca o al limone €3</li>
      <li class="menu-item">Birra alla spina €4</li>
      <li class="menu-item">Calice di vino rosso o bianco €6</li>
      <li class="menu-item">Acqua naturale o frizzante €2</li>
    </ul>
  </div>

  <a href="puntiRistoro.jsp" class="back-button">Indietro</a>

</div>

<footer>
  <p>&copy; 2024 Ristorante "La Pergola". Tutti i diritti riservati.</p>
</footer>

</body>
</html>

