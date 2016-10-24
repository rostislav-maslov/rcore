<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<li>
    <a href="${menu.url}">
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