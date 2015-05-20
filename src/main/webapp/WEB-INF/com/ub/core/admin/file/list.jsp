<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="widget widget-blue">
<div class="widget-title">
    <h3><i class="icon-table"></i> Список загруженных файлов</h3>
</div>
<div class="widget-content">
<div class="table-responsive">
<table class="table table-bordered table-hover">
<thead>
<tr>
    <%--<th>#</th>--%>
    <th>Имя файла</th>
    <th>URL</th>
    <th>URL attach</th>
    <th>id</th>
    <th>Действия</th>

</tr>
</thead>
<tbody>

<c:forEach items="${files}" var="f">
    <tr>
        <%--<td>1</td>--%>
        <td>${f.name}</td>
        <td><a href="/files/${f.id}">/files/${f.id}</a></td>
        <td><a href="/files-attach/${f.id}">/files-attach/${f.id}</a></td>
        <td>${f.id}</td>

            <c:url value="<%= FileRoutes.DELETE%>" var="deleteUrl">
                <c:param name="id"   value="${f.id}" />
            </c:url>
        <td class="text-right">
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