<%@ page import="com.ub.core.pictureTask.routes.PictureTaskAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<style>
    .curcor-pointer:hover {
        cursor: pointer;
        background-color: whitesmoke;
    }
</style>
<div class="row" style="margin-top: 10px">
    <div class="col-lg-12">
        <table class="table table-bordered" id="table-1">
            <thead>
            <tr>
                <th>Picture ID</th><th>exc</th><th>Status</th><th>Width</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${searchPictureTaskAdminResponse.result}" var="doc">

                <tr class="curcor-pointer modal-pictureTask-line" data-id="${doc.id}" data-title="${doc.title}">
                    <td>${doc.pictureId}</td><td>${doc.exc}</td><td>${doc.status}</td><td>${doc.width}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 text-center">
        <ul class="pagination pagination-sm">

            <li>
                <a href="#" class="modal-pictureTask-goto" data-query="${searchPictureTaskAdminResponse.query}"
                   data-number="${searchPictureTaskAdminResponse.prevNum()}">
                    <i class="entypo-left-open-mini"></i></a>
            </li>
            <c:forEach items="${searchPictureTaskAdminResponse.paginator()}" var="page">
                <li class="<c:if test="${searchPictureTaskAdminResponse.currentPage eq page}">active</c:if>">
                    <a href="#" class="modal-pictureTask-goto" data-query="${searchPictureTaskAdminResponse.query}"
                       data-number="${page}">${page + 1}</a>
                </li>
            </c:forEach>
            <li>
                <a href="#" class="modal-pictureTask-goto" data-query="${searchPictureTaskAdminResponse.query}"
                   data-number="${searchPictureTaskAdminResponse.nextNum()}"><i class="entypo-right-open-mini"></i></a>
            </li>
        </ul>
    </div>
</div>