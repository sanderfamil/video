<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="nl">
<head>
<title>Videotheek-klant</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="styles/default.css">
</head>
<body>
<c:import url="/WEB-INF/JSP/menu.jsp"/>
<c:url value="/klant.htm" var="url"/>
<h1>Klant</h1>

<form method="get" action="${url}" id="zoeken">
<label>
Familienaam bevat:
<input name="achternaam" value="${param.achternaam}"autofocus>
</label>
<input type="submit" name="zoeken" value="Zoeken">
</form>
${fouten}
<c:if test="${not empty klanten}">

<table border="1">
<thead>
<tr>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Naam</th>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Straat-Huisnummer</th>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Postcode</th>
<th style="text-align:center; background-color:#0D60FF; color:#ffffff;">Gemeente</th>
</tr>
</thead>
<tbody>
<c:forEach var="klant" items="${klanten}">
<c:url value="bevestigen.htm" var="klantURL">
<c:param name="nummer" value="${klant.klantnr}"/>
</c:url>
<tr>
<td>
<c:choose>
<c:when test="${not empty mandje}">
<a href="${klantURL}">${klant.voornaam} ${klant.naam}</a>
</c:when>
<c:otherwise>
${klant.voornaam} ${klant.naam}
</c:otherwise>
</c:choose>
</td>
<td>${klant.straat}</td>
<td>${klant.postcode}</td>
<td>${klant.gemeente}</td>
</c:forEach>

</tbody>
</table>
</c:if>

</body>
</html>