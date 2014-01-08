<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
﻿

<div class="container">

    <!-- Static navbar -->
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <c:forEach items="${menuViews}" var="m">
                    <c:if test="${empty m.child}">
                        <li><a href="<c:url value="${m.url}"/>">${m.name}</a></li>
                    </c:if>

                    <c:if test="${not empty m.child}">
                        <li class="dropdown">
                            <a href="<c:url value="${m.url}"/>" class="dropdown-toggle" data-toggle="dropdown">
                                    ${m.name} <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <c:forEach items="${m.child}" var="c">
                                    <li><a href="<c:url value="${c.url}"/>">${c.name}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="./">Default</a></li>
                <li><a href="../navbar-static-top/">Static top</a></li>
                <li><a href="../navbar-fixed-top/">Fixed top</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>Пример меню</h1>

        <p>
            Отредактировать меню можно в админе - <a href="<c:url value="/admin/menu/list"/>">Список меню</a>
        </p>

        <p>
            <a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>
        </p>
    </div>

</div>
<!-- /container -->

