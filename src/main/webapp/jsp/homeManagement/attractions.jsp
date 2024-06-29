<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attractions</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="attractions">Attractions</a></li>
        <li><a href="food">Food</a></li>
        <li><a href="events">Events</a></li>
        <li><a href="map">Map</a></li>
        <li><a href="login">Login</a></li>
    </ul>
</nav>
<div id="content">
    <h1>Attractions</h1>
    <ul>
        <c:forEach var="attraction" items="${attrazione}">
            <li>${attrazione.NOME}: ${attrazione.TIPOLOGIA}</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>

