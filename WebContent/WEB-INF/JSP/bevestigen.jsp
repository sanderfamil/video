<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>Videotheek-bevestigen</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<h1>Bevestigen</h1>
${aantalfilms} film(s) voor ${klant.voornaam} ${klant.naam}
<c:url value="bevestigen.htm" var="url">
<c:param name="klantnummer" value="${klant.klantnr}"/>
</c:url> 
<form method="post" action="${url}">
<input type="submit" value="Bevestigen">
</form>
</body>
</html>