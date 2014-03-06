<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>
<c:choose>
<c:when test="${not empty genre}">
Videotheek-${genre.naam}
</c:when>
<c:otherwise>
Videotheek-genre niet gevonden
</c:otherwise>
</c:choose>
</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<h1>Reservaties</h1>

<ul class="genremenu">
<c:forEach var="genre" items="${genres}">
<li>
<c:url value="/genre.htm" var="genreURL">
<c:param name="nummer" value="${genre.genreNr}"/>
</c:url>
<a href="${genreURL}"><c:out value="${genre.naam}"/></a>
</li>
</c:forEach>
</ul>
<c:choose>
<c:when test="${not empty genre}">
<ul>
<c:forEach var="film" items="${films}">
<c:url value="film.htm" var="filmURL">
<c:param name="nummer" value="${film.filmNr}"/>
</c:url>
<c:choose>
<c:when test= "${film.gereserveerd == film.voorraad}">
<a href="${filmURL}"><img src="img/${film.filmNr}.jpg" alt="${film.titel}" title="reservatie niet mogelijk" /></a>
</c:when>
<c:otherwise>
<a href="${filmURL}"><img src="img/${film.filmNr}.jpg" alt="${film.titel}" title="reservatie mogelijk" /></a>
</c:otherwise>
</c:choose>

</c:forEach>
</ul>
</c:when>
<c:otherwise>
Genre niet gevonden. 
</c:otherwise>
</c:choose>
</body>
</html>