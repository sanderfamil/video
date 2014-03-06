<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>
<c:choose>
<c:when test="${not empty film}">
Videotheek-${film.titel}
</c:when>
<c:otherwise>
Videotheek-film niet gevonden
</c:otherwise>
</c:choose>
</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<c:choose>
<c:when test="${not empty film}">
<h1><c:out value="${film.titel}"/></h1>
<img src="img/${film.filmNr}.jpg" alt="${film.titel}"/><br>
Prijs<br>
&euro;<fmt:formatNumber value="${film.prijs}"/>
<br>
Voorraad<br>
${film.voorraad}<br>
Gereserveerd<br>
${film.gereserveerd}<br>
Beschikbaar<br>
${film.voorraad-film.gereserveerd}<br>
<c:url value="/film.htm" var="url">
<c:param name="filmnummer" value="${film.filmNr}"/>
</c:url>

<c:choose>
<c:when test="${inmandje}">
<h3>Film al in mandje.</h3>
</c:when>
<c:otherwise >
<c:if test="${film.voorraad>film.gereserveerd}">
<form method="post" action="${url}" id="toevoegen">
<input type="submit" value="In mandje" id="toevoegknop"/>
</form>
</c:if>
</c:otherwise>
</c:choose>
<script>
document.getElementById("toevoegen").onsubmit=function(){
	document.getElementById("toevoegknop").disabled=true;
};

</script>
</c:when>
<c:otherwise>
<h1>Film niet gevonden.</h1>
</c:otherwise>
</c:choose>
</body>
</html>