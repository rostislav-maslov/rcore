<%@ page import="com.ub.core.menu.models.fields.MenuFields" %>
<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="row">
    <div class="col-lg-12" >
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Пользователь</th>
                <th>Выбор</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${searchUserAdminResponse.result}" var="doc">
                <tr>
                    <td>${doc.lastName} ${doc.firstName}</td>
                    <td class="">
                        <button class="btn btn-xs btn-success" onclick="modalUserSearchChoseUser('${doc.id}', '${doc.lastName} ${doc.firstName}');">
                            <i class="icon-remove">Выбрать</i>
                        </button>
                    </td>
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
                <a href="#" onclick="modalUserSearchUpdateContent(${searchUserAdminResponse.prevNum()});return false;">
                    <i class="entypo-left-open-mini"></i>
                </a>
            </li>

            <c:forEach items="${searchUserAdminResponse.paginator()}" var="page">
                <li class="<c:if test="${searchUserAdminResponse.currentPage eq page}">active</c:if>">
                    <a href="#" onclick="modalUserSearchUpdateContent(${page});return false;">${page + 1}</a>
                </li>
            </c:forEach>

            <li><a href="#" onclick="modalUserSearchUpdateContent(${searchUserAdminResponse.nextNum()});return false;">
                <i class="entypo-right-open-mini"></i></a>
            </li>
        </ul>
    </div>
</div>
