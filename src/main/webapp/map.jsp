<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 24/06/2024
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mappa</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
          integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
          crossorigin=""/>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
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
        .main-content img {
            max-width: 100%;
            height: auto;
        }
        #map {
            width: 100%;
            height: 600px;
        }
    </style>
</head>
<body>

<header>
    <h1>Mappa del Parco</h1>
</header>

<nav>
    <ul>
        <li><a href="view.jsp">Home</a></li>
        <li><a href="attrazioni.jsp">Attrazioni</a></li>
        <li><a href="puntiRistoro.jsp">Punti Ristoro</a></li>
        <li><a href="eventi.jsp">Eventi</a></li>
        <li><a href="login.jsp">Login</a></li>
    </ul>
</nav>

    <div id="map"></div>
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
            integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
            crossorigin=""></script>
    <script>
        // Le dimensioni della tua immagine (larghezza e altezza in pixel)
        const imageWidth = 1024;
        const imageHeight = 768;

        // Le coordinate in cui verrà posizionata l'immagine nella mappa
        let southWest = L.latLng(0, 0), northEast = L.latLng(imageHeight, imageWidth);
        let bounds = L.latLngBounds(southWest, northEast);

        // Inizializza la mappa
        var map = L.map('map', {
            crs: L.CRS.Simple,
            minZoom: -1,
            maxZoom: 4
        });

        // Aggiungi l'immagine alla mappa
        L.imageOverlay('Parco.jpg', bounds).addTo(map);

        // Adatta la vista ai limiti dell'immagine
        map.fitBounds(bounds);

        // Funzione per aggiungere un marker
        function addMarker(x, y, popupText){
            var latLng = L.latLng(y, x);
            L.marker(latLng).addTo(map).bindPopup(popupText);
        }

        var gelato = L.popup().setContent('<a href="menuGelato.jsp">Gelato Mania</a>');
        var menuIta = L.popup().setContent('<a href="menuItaliano.jsp">La Pergola</a>');
        var waffle = L.popup().setContent('<a href="menuWaffle.jsp">Waffle World</a>');
        var fry = L.popup().setContent('<a href="menuFritto.jsp">Fry Frenzy</a>');
        var drink = L.popup().setContent('<a href="menuBibite.jsp">Drink Haven</a>');
        var burger = L.popup().setContent('<a href="menuHamburger.jsp">Burger Kingdom</a>');
        var attr1 = L.popup().setContent('<a href="attrazioni.jsp">Giostra Cavalli</a>');
        var attr2 = L.popup().setContent('<a href="attrazioni.jsp">Fiume Rapido</a>');
        var attr3 = L.popup().setContent('<a href="attrazioni.jsp">Trenino Meraviglia</a>');
        var attr4 = L.popup().setContent('<a href="attrazioni.jsp">Casa Orrori</a>');
        var attr5 = L.popup().setContent('<a href="attrazioni.jsp">Fulmine</a>');
        var attr6 = L.popup().setContent('<a href="attrazioni.jsp">Nave Pirati</a>');
        var luogo1 = L.popup().setContent('<a href="eventi.jsp">Teatro Meraviglia</a>');
        var luogo2 = L.popup().setContent('<a href="eventi.jsp">Arena</a>');
        var luogo3 = L.popup().setContent('<a href="eventi.jsp">Piazza Centrale</a>');

        // marker punti di ristoro
        addMarker(430, 454, gelato);
        addMarker(501, 574, menuIta);
        addMarker(620, 481, waffle);
        addMarker(213, 342, fry);
        addMarker(369, 230, drink);
        addMarker(750, 204, burger);
        // marker attrazioni
        addMarker(308, 564, attr1);
        addMarker(754, 574, attr2);
        addMarker(218, 443, attr3);
        addMarker(219, 167, attr4);
        addMarker(550, 133, attr5);
        addMarker(780, 309, attr6);
        // marker luoghi spettacoli
        addMarker(567, 257, luogo1);
        addMarker(830, 485, luogo2);
        addMarker(479, 382, luogo3);
    </script>
<%@include file="/include/footer.inc"%>
</body>
</html>
