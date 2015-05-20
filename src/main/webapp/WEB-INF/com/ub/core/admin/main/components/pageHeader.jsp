<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-md-6">
        <ol class="breadcrumb bc-3">
            <c:forEach items="${pageHeader.breadcrumbs.links}" var="breadcrumbsLinks" varStatus="stat">
                <li>
                    <a href="<c:url value="${breadcrumbsLinks.link}"/>">
                        <c:if test="${stat.first}"><i class="entypo-home"></i></c:if>${breadcrumbsLinks.title}
                    </a>
                </li>
            </c:forEach>
            <li class="active">
                <strong>${pageHeader.breadcrumbs.currentPageTitle}</strong>
            </li>
        </ol>
    </div>
    <div class="col-md-6 text-right">
        <a href="<c:url value="${pageHeader.linkAdd}"/>" class="btn btn-default">${pageHeader.titleAdd}</a>
    </div>
</div>