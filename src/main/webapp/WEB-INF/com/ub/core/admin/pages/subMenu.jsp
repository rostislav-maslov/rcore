<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav">
    <li><a href="<c:url value="<%= PagesAdminRoutes.ALL%>"/>">Все</a></li>
    <li><a href="<c:url value="<%= PagesAdminRoutes.ADD%>"/>">Добавить</a></li>
</ul>