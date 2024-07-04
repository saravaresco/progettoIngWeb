<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 05/06/2024
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Parco Divertimenti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 10px 0;
            text-align: center;
        }
        header h1 {
            font-size: 2em;
            margin: 0;
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
        .main-content {
            padding: 20px;
            text-align: center;
        }
        .main-content img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Fantasia Park</h1>
</div>


<nav>
    <ul>
        <li><a href="attrazioni.jsp">Attrazioni</a></li>
        <li><a href="puntiRistoro.jsp">Punti Ristoro</a></li>
        <li><a href="eventi.jsp">Eventi</a></li>
        <li><a href="map.jsp">Mappa</a></li>
        <li><a href="login.jsp">Login</a></li>
    </ul>
</nav>

<div class="main-content">
    <h2>Benvenuti al nostro Parco Divertimenti!</h2>
    <img src="parcho.jpg"/>
</div>
<%@include file="/include/footer.inc"%>
</body>
</html>




<%-- è la view principale del sito*/ --%>