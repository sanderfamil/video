<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>Videotheek-mandje</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<h1>Mandje</h1>
<c:if test="${not empty filmLijst}">
<c:url value="/mandje.htm" var="url"/>
<form method="post" action="${url}" id="verwijderen">
<table border="1">

<thead>
<tr>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Film</th>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Prijs</th>
<th style="background-color:#0D60FF;"><input type="submit" value="Verwijderen" id="verwijderknop"/></th>
</tr>
</thead>
<tbody>
<c:forEach var="film" items="${filmLijst}">
<tr>
<td>${film.titel}</td>
<td>&euro;<fmt:formatNumber value="${film.prijs}"/></td>
<td style="text-align:center;"><input type="checkbox" name="nummer" value="${film.filmNr}"></td>
</c:forEach>

</tbody>

<tfoot>
<tr>
<td>Totaal</td>
<td>&euro;<fmt:formatNumber value="${totaal}"/></td>
<td></td>
</tr>
</tfoot>
</table>
</form>
</c:if>

</body>
</html>