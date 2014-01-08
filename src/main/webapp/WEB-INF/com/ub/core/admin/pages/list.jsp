<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="widget widget-blue">
<div class="widget-title">
    <h3><i class="icon-table"></i> Список страниц</h3>
</div>
<div class="widget-content">
<div class="table-responsive">
<table class="table table-bordered table-hover">
<thead>
<tr>
    <%--<th>#</th>--%>
    <th>Заголовок</th>
    <th>URL</th>
    <th>Статус</th>
    <th>Действия</th>

</tr>
</thead>
<tbody>

<c:forEach items="${pageList}" var="page">
    <tr>
        <td>${page.title}</td>
        <td>${page.id}</td>
        <td>${page.status}</td>

            <c:url value="<%= PagesAdminRoutes.ADD%>" var="editUrl">
                <c:param name="url"   value="${page.id}" />
            </c:url>
            <c:url value="<%= PagesAdminRoutes.DELETE%>" var="deleteUrl">
                <c:param name="id"   value="${page.id}" />
            </c:url>
        <td class="text-right">
            <a href="${editUrl}" class="btn btn-default btn-xs">Редактировать</a>
            <a href="${deleteUrl}" class="btn btn-danger btn-xs ">
                <i class="icon-remove"></i>
            </a>
        </td>
    </tr>
</c:forEach>



</tbody>
</table>
</div>
</div>
</div>