<%@ page import="com.ub.core.base.menu.MenuBoost" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach items="<%= MenuBoost.allMenu()%>" var="menu">
    <li>
        <a href="<c:if test="${fn:length(menu.child) eq 0}">${menu.url}</c:if>">
            <i class="${menu.icon}"></i>
            <span>${menu.name}</span>
        </a>
        <c:if test="${fn:length(menu.child) > 0}">
            <ul>
                <c:forEach items="${menu.child}" var="subMenu">
                    <c:set var="menu" value="${subMenu}" scope="request"/>
                    <jsp:include page="subMenu.jsp"/>
                </c:forEach>
            </ul>
        </c:if>
    </li>
</c:forEach>
