<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.parcodivertimenti.parcodivertimenti.model.mo.ripara" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manutenzione Attrazione</title>
</head>
<body>
<h1>Manutenzione Attrazione</h1>

<%
    ResultSet attrazione = (ResultSet) request.getAttribute("attrazione");
    List<ripara> interventi = (List<ripara>) request.getAttribute("interventi");
%>

<%
    if (attrazione != null) {
%>
<h2>Dati Tecnici</h2>
<p>Codice: <%= attrazione.getInt("CODICE") %></p>
<p>Nome: <%= attrazione.getString("NOME") %></p>
<p>Tipologia: <%= attrazione.getString("TIPOLOGIA") %></p>


<h2>Interventi Effettuati</h2>
<ul>
    <%
        if (interventi != null && !interventi.isEmpty()) {
            for (ripara intervento : interventi) {
    %>
    <li><%= intervento.getDescrizione() %> (Manutentore: <%= intervento.getCf_manutentore() %>)</li>
    <%
        }
    } else {
    %>
    <li>Nessun intervento effettuato.</li>
    <%
        }
    %>
</ul>

<h2>Nuovo Intervento</h2>
<form action="manutentore.jsp" method="post">
    <input type="hidden" name="codice_attrazione" value="<%= attrazione.getInt("CODICE") %>" />
    <p>
        <label for="cf_manutentore">Codice Fiscale Manutentore:</label>
        <input type="text" name="cf_manutentore" id="cf_manutentore" required />
    </p>
    <p>
        <label for="descrizione">Descrizione:</label>
        <textarea name="descrizione" id="descrizione" required></textarea>
    </p>
    <p>
        <input type="submit" value="Inserisci Intervento" />
    </p>
</form>
<%
} else {
%>
<p>Attrazione non trovata.</p>
<%
    }
%>
</body>
</html>
