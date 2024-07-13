<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Parco Divertimenti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            font-size: 28px;
            margin-bottom: 30px;
            color: #333;
        }
        .role-options {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-bottom: 30px;
        }
        .role-options button {
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #fff;
            transition: background-color 0.3s;
        }
        .role-options button:hover {
            background-color: #45a049;
        }
        .login-form {
            display: none;
            margin-top: 30px;
        }
        .login-form label, .login-form input {
            display: block;
            margin-bottom: 15px;
            width: 100%; /* Fatto in modo che occupino tutta la larghezza disponibile */
            box-sizing: border-box;
        }
        .login-form button {
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #fff;
            transition: background-color 0.3s;
            width: 100%; /* Fatto in modo che occupino tutta la larghezza disponibile */
            box-sizing: border-box;
        }
        .login-form button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Login Parco Divertimenti</h1>
    <div class="role-options">
        <button onclick="selectRole('visitor')">Visitatore</button>
        <button onclick="selectRole('employee')">Dipendente</button>
    </div>

    <!-- Form per il login del visitatore -->
    <form id="visitorLoginForm" class="login-form" action="visitatore.jsp" >
        <input type="hidden" name="action" value="visitor">
        <p>Se sei gi&#224; registrato accedi alla tua area personale:</p>
        <button type="submit">Accedi</button>
    </form>

    <!-- Form per la registrazione del visitatore -->
    <form id="visitorRegisterForm" class="login-form" action="newVisitor.jsp">
        <p>Se sei nuovo, registrati:</p>
        <button type="submit">Registrati</button>
    </form>

    <!-- Form per il login del dipendente -->
    <form id="employeeLoginForm" class="login-form" action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="LoginDipendenteController.login">
        <input type="hidden" name="action" value="employee">
        <label for="employeeRole">Ruolo:</label>
        <select id="employeeRole" name="category">
            <option value="manutentore">Manutentore</option>
            <option value="addetto_giostre">Addetto Giostre</option>
            <option value="addetto_ristorante">Addetto Ristorante</option>
            <option value="attore">Attore</option>
        </select>
        <label for="employeeUsername">Username:</label>
        <input type="text" id="employeeUsername" name="username" required>
        <label for="employeePassword">Password:</label>
        <input type="password" id="employeePassword" name="password" required>
        <button type="submit">Accedi</button>
    </form>
</div>

<script>
    function selectRole(role) {
        if (role === 'visitor') {
            document.getElementById('visitorLoginForm').style.display = 'block';
            document.getElementById('visitorRegisterForm').style.display = 'block';
            document.getElementById('employeeLoginForm').style.display = 'none';
        } else if (role === 'employee') {
            document.getElementById('visitorLoginForm').style.display = 'none';
            document.getElementById('visitorRegisterForm').style.display = 'none';
            document.getElementById('employeeLoginForm').style.display = 'block';
        }
    }
</script>
</body>
</html>
