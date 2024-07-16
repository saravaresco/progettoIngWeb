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
            max-width: 400px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }
        input[type="text"], input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 12px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #fff;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color:#218838;
        }
        p {
            margin-top: 20px;
            color: #555;
        }
        a {
            color: #007BFF;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Login Parco Divertimenti</h1>

    <!-- Form per il login del visitatore -->
    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="LoginVisitatore.login">
        <label for="visitorUsername">Username:</label>
        <input type="text" id="visitorUsername" name="username" required>
        <label for="visitorPassword">Password:</label>
        <input type="password" id="visitorPassword" name="password" required>
        <button type="submit">Accedi</button>
    </form>

    <!-- Link per la password dimenticata -->
    <p><a href="forgotPassword.jsp">Password dimenticata?</a></p>
</div>
</body>
</html>
