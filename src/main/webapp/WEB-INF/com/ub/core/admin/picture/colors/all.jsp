<%@ page import="com.ub.core.picture.routes.PictureColorAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <form action="<%= PictureColorAdminRoutes.ALL%>" method="GET">
        <div class="col-lg-5">
            <div class="input-group">
                <input type="text" class="form-control input-sm" id="query" name="query"
                       value="${searchAdminResponse.query}" placeholder="Поиск"/>

                <div class="input-group-btn">
                    <button type="submit" class="btn btn-sm btn-default"><i class="entypo-search">Поиск </i></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="widget widget-blue">
    <div class="widget-title">
        <h3><i class="icon-table"></i> Результаты поиска: </h3>
    </div>
    <div class="widget-content">
        <div class="table-responsive">
            <table class="table table-bordered" id="table-1">
                <thead>
                <tr>

                    <th>Заголовок</th>
                    <th>Цвет</th>

                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${searchAdminResponse.result}" var="doc">

                    <tr>
                        <td>${doc.title}</td>
                        <td>${doc.hex}</td>

                        <td class="">
                            <c:url value="<%= PictureColorAdminRoutes.EDIT%>" var="editUrl">
                                <c:param name="id" value="${doc.id}"/>
                            </c:url>
                            <a href="${editUrl}" class="btn btn-default btn-xs">
                                <i class="icon-pencil">Редактировать</i>
                            </a>
                            <c:url value="<%= PictureColorAdminRoutes.DELETE%>" var="urlDelete">
                                <c:param name="id" value="${doc.id}"></c:param>
                            </c:url>
                            <a href="${urlDelete}" type="submit" class="btn btn-xs btn-danger  ">
                                <i class="icon-remove">Удалить</i>
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
            <c:url value="<%= PictureColorAdminRoutes.ALL%>" var="urlPrev">
                <c:param name="query" value="${searchAdminResponse.query}"/>
                <c:param name="currentPage" value="${searchAdminResponse.prevNum()}"/>
            </c:url>
            <li><a href="${urlPrev}"><i class="entypo-left-open-mini"></i></a></li>

            <c:forEach items="${searchAdminResponse.paginator()}" var="page">
                <c:url value="<%= PictureColorAdminRoutes.ALL%>" var="urlPage">
                    <c:param name="query" value="${searchAdminResponse.query}"/>
                    <c:param name="currentPage" value="${page}"/>
                </c:url>
                <li class="<c:if test="${searchAdminResponse.currentPage eq page}">active</c:if>">
                    <a href="${urlPage}">${page + 1}</a>
                </li>
            </c:forEach>
            <c:url value="<%= PictureColorAdminRoutes.ALL%>" var="urlNext">
                <c:param name="query" value="${searchAdminResponse.query}"/>
                <c:param name="currentPage" value="${searchAdminResponse.nextNum()}"/>
            </c:url>
            <li><a href="${urlNext}"><i class="entypo-right-open-mini"></i></a></li>
        </ul>
    </div>
</div>
