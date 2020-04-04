<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
﻿
<div class="widget widget-blue">
    <div class="widget-title">
        <h3><i class="fa fa-table"></i> Список загруженных файлов</h3>
    </div>
    <p>Всего - ${files.all}</p>
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

                <c:forEach items="${files.result}" var="f">
                    <tr>
                        <td>${f.name}</td>
                        <td><a href="/files/${f.id}">/files/${f.id}</a></td>
                        <td><a href="/files-attach/${f.id}">/files-attach/${f.id}</a></td>
                        <td>${f.id}</td>

                        <c:url value="<%= FileRoutes.DELETE%>" var="deleteUrl">
                            <c:param name="id" value="${f.id}"/>
                        </c:url>
                        <td class="text-right">
                            <a href="${deleteUrl}" class="btn btn-danger btn-xs">
                                <i class="fa fa-times-circle"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12 text-center">
        <ul class="pagination pagination-sm">
            <c:url value="<%= FileRoutes.LIST%>" var="urlPrev">
                <c:param name="query" value="${files.query}"/>
                <c:param name="currentPage" value="${files.prevNum()}"/>
            </c:url>
            <li><a href="${urlPrev}"><i class="entypo-left-open-mini"></i></a></li>
            <c:forEach items="${files.paginator()}" var="page">
                <c:url value="<%= FileRoutes.LIST%>" var="urlPage">
                    <c:param name="query" value="${files.query}"/>
                    <c:param name="currentPage" value="${page}"/>
                </c:url>
                <li class="<c:if test="${files.currentPage eq page}">active</c:if>">
                    <a href="${urlPage}">${page + 1}</a>
                </li>
            </c:forEach>
            <c:url value="<%= FileRoutes.LIST%>" var="urlNext">
                <c:param name="query" value="${files.query}"/>
                <c:param name="currentPage" value="${files.nextNum()}"/>
            </c:url>
            <li><a href="${urlNext}"><i class="entypo-right-open-mini"></i></a></li>
        </ul>
    </div>
</div>