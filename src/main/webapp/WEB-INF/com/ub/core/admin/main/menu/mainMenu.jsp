<%@ page import="com.ub.core.menu.models.fields.MenuFields" %>
<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ page import="com.ub.core.base.menu.MenuBoost" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:forEach items="<%= MenuBoost.allMenu()%>" var="menu">
    <li>
    <a href="">
        <i class="entypo-gauge"></i>
        <span>${menu.name}</span>
    </a>
    <ul>
    <c:forEach items="${menu.subMenus}" var="subMenu">
        <li>
        <a href="${subMenu.url}">
            <span>${subMenu.name}</span>
        </a>
        <ul>
        <c:forEach items="${subMenu.subMenus}" var="menuLevel2">
            <li>
                <a href="${menuLevel2.url}">
                    <span>${menuLevel2.name}</span>
                </a>
            </li>
        </c:forEach>
            </ul>
        </li>
    </c:forEach>
    </ul>
    </li>
</c:forEach>
