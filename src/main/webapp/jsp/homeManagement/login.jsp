<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label for="role">Seleziona il tuo ruolo:</label>
    <select name="role" id="role" required>
        <option value="visitatore">Visitatore</option>
        <option value="addetto_giostre">Addetto Giostre</option>
        <option value="addetto_ristorante">Addetto Ristorante</option>
        <option value="attore">Manutentore</option>
        <option value="manutentore">Manutentore</option>
        <!-- Aggiungi altre opzioni di ruolo qui -->
    </select>
    <br><br>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
