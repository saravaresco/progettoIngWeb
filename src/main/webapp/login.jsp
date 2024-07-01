<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Parco Divertimenti</title>
</head>
<body>
<h1>Login Parco Divertimenti</h1>
<form action="login" method="post">
    <label for="userType">Sei un:</label>
    <select id="userType" name="userType" onchange="showEmployeeFields(this.value)">
        <option value="visitatore">Visitatore</option>
        <option value="dipendente">Dipendente</option>
    </select>

    <div id="employeeFields" style="display:none;">
        <label for="category">Categoria:</label>
        <select id="category" name="category">
            <option value="addetto_giostre">Addetto Giostre</option>
            <option value="addetto_ristorante">Addetto Ristorante</option>
            <option value="manutentore">Manutentore</option>
            <option value="attore">Attore</option>
        </select>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="Login">
    </div>

    <div id="visitorFields">
        <button type="submit" name="action" value="register">Registrati</button>
        <button type="submit" name="action" value="login">Accedi</button>
    </div>
</form>


<script>
    function showEmployeeFields(value) {
        if (value === 'dipendente') {
            document.getElementById('employeeFields').style.display = 'block';
            document.getElementById('visitorFields').style.display = 'none';
        } else {
            document.getElementById('employeeFields').style.display = 'none';
            document.getElementById('visitorFields').style.display = 'block';
        }
    }
</script>
</body>
</html>
