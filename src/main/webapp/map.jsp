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
        #map {
            width: 100%;
            height: 600px;
        }
    </style>
</head>
<body>
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

        // marker punti di ristoro
        addMarker(430, 454, "Gelato Mania");
        addMarker(501, 574, "La Pergola");
        addMarker(620, 481, "Waffle World");
        addMarker(213, 342, "Fry Frenzy");
        addMarker(369, 230, "Drink Haven");
        addMarker(750, 204,"Burger Kingdom");
        // marker attrazioni
        addMarker(308, 564, "Giostra cavalli");
        addMarker(754, 574, "Fiume Rapido");
        addMarker(218, 443, "Trenino Meraviglie");
        addMarker(219, 167, "Casa orrori");
        addMarker(550, 133, "Fulmine");
        addMarker(780, 309, "Nave Pirati");
        // marker luoghi spettacoli
        addMarker(567, 257, "Teatro Meraviglia");
        addMarker(830, 485, "Arena");
        addMarker(479, 382, "Piazza Centrale");
    </script>
</body>
</html>
