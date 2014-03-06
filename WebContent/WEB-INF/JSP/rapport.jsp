<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>Videotheek-rapport</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<h1>Rapport</h1>
<c:choose>

<c:when test="${not empty nietGereserveerd}">
Volgende films zijn niet gereserveerd:
<ul>
<c:forEach var="film" items="${nietGereserveerd}">
<li>${film.titel}</li>
</c:forEach> 
</ul>
<c:url value="/rapport.htm" var="url"/>
<form method="post" action="${url}">
<input type="submit" value="OK"/>
</form> 
</c:when>
<c:otherwise>
De reservatie is OK.
</c:otherwise>
</c:choose>



</body>
</html>