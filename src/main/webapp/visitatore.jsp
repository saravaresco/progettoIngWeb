<%--<!DOCTYPE html>
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
    <div class="role-options">
        <button onclick="selectRole('visitor')">Visitatore</button>
        <button onclick="selectRole('employee')">Dipendente</button>
    </div>

    <!-- Form per il login del visitatore -->
    <form id="visitorLoginForm" class="login-form" action="login2" method="get">
        <input type="hidden" name="action" value="visitor">
        <label for="visitorUsername">Username:</label>
        <input type="text" id="visitorUsername" name="username" required>
        <label for="visitorPassword">Password:</label>
        <input type="password" id="visitorPassword" name="password" required>
        <button type="submit">Accedi</button>
    </form>

    <!-- Form per la registrazione del visitatore -->
    <form id="visitorRegisterForm" class="login-form" action="newVisitor.jsp">
        <p>Se sei nuovo, registrati:</p>
        <button type="submit">Registrati</button>
    </form>

    <!-- Form per il login del dipendente -->
    <form id="employeeLoginForm" class="login-form" action="login" method="post">
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
--%>

<%@page session="false"%>
<%@page import="com.parcodivertimenti.parcodivertimenti.model.mo.visitatore"%>


<%int i = 0;
    boolean loggedOn = true;
    visitatore loggedUser = (visitatore) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Rubrica";

    String selectedInitial = (String) request.getAttribute("selectedInitial");
    visitatore contact = (visitatore) request.getAttribute("contact");
    String action=(contact != null) ? "modify" : "insert";
%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        .field {
            margin: 5px 0;
        }

        label {
            float: left;
            width: 56px;
            font-size: 0.8em;
            margin-right: 10px;
            padding-top: 3px;
            text-align: left;
        }

        input[type="text"], input[type="tel"], input[type="email"] {
            border: none;
            border-radius: 4px;
            padding: 3px;
            background-color: #e8eeef;
            color:#8a97a0;
            box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
        }

        input[type="text"]:focus, input[type="tel"]:focus, input[type="email"]:focus {
            background: #d2d9dd;
            outline-color: #a3271f;
        }

    </style>
    <script language="javascript">

        var status="<%=action%>";

        function submitContact() {
            var f;
            f = document.insModForm;
            f.selectedInitial.value = f.surname.value.substring(0, 1).toUpperCase();
            f.controllerAction.value = "AddressBookManagement."+status;
        }

        function goback() {
            document.backForm.submit();
        }

        function mainOnLoadHandler() {
            document.insModForm.addEventListener("submit", submitContact);
            document.insModForm.backButton.addEventListener("click", goback);
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <section id="pageTitle">
        <h1>
            Rubrica: <%=(action.equals("modify")) ? "Modifica Contatto" : "Nuovo Contatto"%>
        </h1>
    </section>

    <section id="insModFormSection">
        <form name="insModForm" action="Dispatcher" method="post">

            <div class="field clearfix">
                <label for="firstname">Nome</label>
                <input type="text" id="firstname" name="firstname"
                       value="<%=(action.equals("modify")) ? contact.getNome() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label for="surname">Cognome</label>
                <input type="text" id="surname" name="surname"
                       value="<%=(action.equals("modify")) ? contact.getCognome() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label>Sesso</label>
                <input type="radio" name="sex" value="M"
                        <%=(action.equals("insert") || (action.equals("modify") && contact.getSesso().equals("M"))) ? "checked" : ""%>
                /> M
                <input type="radio" name="sex" value="F"
                        <%=(action.equals("modify") && contact.getSesso().equals("F")) ? "checked" : ""%>
                /> F
            </div>
            <div class="field clearfix">
                <label for="address">Codice Fiscale</label>
                <input type="text" id="address" name="address"
                       value="<%=(action.equals("modify")) ? contact.getCodice_fiscale() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label for="city">Data Nascita</label>
                <input type="text" id="city" name="city"
                       value="<%=(action.equals("modify")) ? contact.getData_nascita() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label for="phone">Username</label>
                <input type="tel" id="phone" name="phone"
                       value="<%=(action.equals("modify")) ? contact.getUsername() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label for="email">password</label>
                <input type="email" id="email" name="email"
                       value="<%=(action.equals("modify")) ? contact.getPassword() : ""%>"
                       required size="20" maxlength="50"/>
            </div>
            <div class="field clearfix">
                <label>&#160;</label>
                <input type="submit" class="button" value="Invia"/>
                <input type="button" name="backButton" class="button" value="Annulla"/>
            </div>

            <%if (action.equals("modify")) {%>
            <input type="hidden" name="contactId" value="<%=contact.getCodice_fiscale()%>"/>
            <%}%>
            <input type="hidden" name="selectedInitial"/>
            <input type="hidden" name="controllerAction"/>

        </form>
    </section>

    <form name="backForm" method="post" action="Dispatcher">
        <%--input type="hidden" name="selectedInitial" value="<%=selectedInitial%>"/--%>
        <input type="hidden" name="controllerAction" value="AddressBookManagement.view"/>
    </form>

</main>
<%@include file="/include/footer.inc"%>
</body>

</html>

