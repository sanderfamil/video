<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head lang="nl">
<title>Videotheek-welkom</title>
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



</body>
</html>