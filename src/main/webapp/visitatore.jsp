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
            width: 100%;
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
            width: 100%;
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

    <!-- Form per il login del visitatore -->
    <form action="Dispatcher" method="get">
        <input type="hidden" name="controllerAction" value="LoginVisitatore.login">
        <label for="visitorUsername">Username:</label>
        <input type="text" id="visitorUsername" name="username" required>
        <label for="visitorPassword">Password:</label>
        <input type="password" id="visitorPassword" name="password" required>
        <button type="submit">Accedi</button>
    </form>

</div>
</script>
</body>
</html>


