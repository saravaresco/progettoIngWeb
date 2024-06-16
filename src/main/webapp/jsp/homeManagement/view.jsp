<%--
  Created by IntelliJ IDEA.
  User: 217347
  Date: 05/06/2024
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" %>
<%@page import="com.parcodivertimenti.parcodivertimenti.model.mo.visitatore" %>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    visitatore loggedVisitatore = (visitatore) request.getAttribute("loggedVisitatore");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Home";
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <%if (loggedOn) {%>
    Benvenuto <%=loggedVisitatore.getNome()%> <%=loggedVisitatore.getCognome()%>!<br/>
    Clicca sulla voce "Rubrica" del men&ugrave; per gestire i tuoi contatti.
    <%} else {%>
    Benvenuto.
    Fai il logon per gestire la tua rubrica.
    <%}%>
</main>
<%@include file="/include/footer.inc"%>
</html>



<%-- è la view principale del sito*/ --%>