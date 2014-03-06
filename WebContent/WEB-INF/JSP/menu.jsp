<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<header>
<nav>
<ul class="navmenu">
<li><a href="<c:url value="/"/>">Genres</a></li>
<c:if test="${not empty mandje}">
<li><a href="<c:url value="/mandje.htm"/>">Mandje</a></li>
</c:if>
<li><a href="<c:url value="/klant.htm"/>">Klant</a></li>
</ul>
</nav>
</header>