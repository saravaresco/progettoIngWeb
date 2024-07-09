<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 03/07/2024
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione Visitatore</title>
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
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        form {
            text-align: left; /* Allineato il testo a sinistra */
        }
        label {
            display: block;
            margin-bottom: 8px;
            text-align: left;
        }
        input[type=text], input[type=password], input[type=date], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        /*input[type=radio] {
            margin-right: 8px;
            margin-top: 3px;
        }
        button {
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #fff;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }*/
        input[type=radio] {
            display: none; /* Nascondo il radio button */
        }
        .radio-label {
            display: inline-block;
            cursor: pointer;
            position: relative;
            padding-left: 25px; /* Spazio per il simulato pallino */
            margin-right: 15px;
            line-height: 28px;
        }
        .radio-label:before {
            content: '';
            position: absolute;
            left: 0;
            top: 50%;
            transform: translateY(-50%);
            width: 16px;
            height: 16px;
            border-radius: 50%;
            border: 1px solid #ccc;
            background-color: #fff;
            transition: background-color 0.3s, border-color 0.3s;
        }
        input[type=radio]:checked + .radio-label:before {
            background-color: #4CAF50;
            border-color: #4CAF50;
        }
        button {
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #fff;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Registrazione Visitatore</h1>
    <form action="newVisitor" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" required><br>

        <label for="codiceFiscale">Codice Fiscale:</label>
        <input type="text" id="codiceFiscale" name="codiceFiscale" required><br>

        <label for="dataNascita">Data di Nascita:</label>
        <input type="date" id="dataNascita" name="dataNascita" required><br>

        <label>Sesso:</label>
        <div>
            <input type="radio" id="sessoM" name="sesso" value="M" required>
            <label class="radio-label" for="sessoM">Maschio</label>

            <input type="radio" id="sessoF" name="sesso" value="F" required>
            <label class="radio-label" for="sessoF">Femmina</label>
        </div>


        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Registrati</button>
    </form>
</div>
</body>
</html>

