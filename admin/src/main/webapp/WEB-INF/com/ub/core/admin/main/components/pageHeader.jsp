<%--@elvariable id="pageHeader" type="com.ub.core.base.views.pageHeader.PageHeader"--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-md-6">
        <ol class="breadcrumb bc-3">
            <c:forEach items="${pageHeader.breadcrumbs.links}" var="breadcrumbsLinks" varStatus="stat">
                <li>
                    <a
                        <c:if test="${not empty breadcrumbsLinks.link}">
                            href="<c:url value="${breadcrumbsLinks.link}"/>"
                        </c:if>
                    >
                        <c:if test="${stat.first}"><i class="fa fa-home"></i></c:if>${breadcrumbsLinks.title}
                    </a>
                </li>
            </c:forEach>
            <li class="active">
                <strong>${pageHeader.breadcrumbs.currentPageTitle}</strong>
            </li>
        </ol>
    </div>
    <div class="col-md-6 text-right">
        <c:forEach items="${pageHeader.addLinks}" var="addLink">
            <a href="<c:url value="${addLink.link}"/>" class="btn btn-${addLink.type.title}">${addLink.title}</a>
        </c:forEach>
        <c:if test="${not empty pageHeader.linkAdd}">
            <a href="<c:url value="${pageHeader.linkAdd}"/>"
               class="btn btn-${pageHeader.typeAdd.title}">${pageHeader.titleAdd}</a>
        </c:if>
    </div>
</div>