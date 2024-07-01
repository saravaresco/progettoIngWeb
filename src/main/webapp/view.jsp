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
        .menu {
            overflow: hidden;
            background-color: #333;
        }
        .menu a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .menu a:hover {
            background-color: #ddd;
            color: black;
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
    <h1>Nome del Parco Divertimenti</h1>
</div>
<div class="menu">
    <a href="attrazioni.jsp">Attrazioni</a>
    <%--<a href="eventi.jsp">Eventi</a>--%>
    <a href="puntiRistoro.jsp">Punti Ristoro</a>
    <%--<a href="mappa.jsp">Mappa</a>--%>
    <%--<a href="login2.jsp">Login</a>--%>
    <a href="eventi.jsp">Eventi</a>
    <a href="mappa">Mappa</a>
    <a href="login.jsp">Login</a>
</div>
<div class="main-content">
    <h2>Benvenuti al nostro Parco Divertimenti!</h2>
    <img src="jsp/homeManagement/images/foto_parco.jpg">
</div>
</body>
</html>




<%-- è la view principale del sito*/ --%>