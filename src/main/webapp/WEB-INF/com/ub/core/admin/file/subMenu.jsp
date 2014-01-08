<%@ page import="com.ub.core.file.FileRoutes" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav">
    <li><a href="<c:url value="<%= FileRoutes.LIST%>"/>">Все</a></li>
    <li><a href="<c:url value="<%= FileRoutes.ADD%>"/>">Добавить</a></li>
</ul>