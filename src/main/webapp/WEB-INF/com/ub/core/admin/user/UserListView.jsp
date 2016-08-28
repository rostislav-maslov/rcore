<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ page import="com.ub.core.user.models.UserStatusEnum" %>
<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
﻿
<div class="row">
    <form action="${url}" method="GET">
        <div class="col-lg-5">
            <div class="input-group">
                <input type="text" class="form-control input-sm" id="query" name="query"
                       value="${searchUserAdminResponse.query}" placeholder="Поиск"/>
                <div class="input-group-btn">
                    <button type="submit" class="btn btn-sm btn-default"><i class="entypo-search">Поиск </i></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="widget widget-blue">
    <div class="widget-title">
        <h3><i class="fa fa-table" aria-hidden="true"></i> Пользователи</h3>
    </div>
    <div class="widget-content">
        <p>Всего - ${searchUserAdminResponse.all}</p>

        <div class="table-responsive">
            <table class="table table-bordered datatable" id="table-1">
                <thead>
                <tr>
                    <th>
                        Email
                    </th>
                    <th>
                        Имя
                    </th>
                    <th>
                        Статус
                    </th>
                    <th>
                        Роли
                    </th>
                    <th>
                        Действия
                    </th>

                </tr>
                </thead>
                <tbody>

                <c:forEach items="${searchUserAdminResponse.result}" var="user">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName} ${user.lastName}</td>
                        <c:if test="${user.userStatus == active}">
                            <td>
                                <span class="label label-success">Активный</span>
                            </td>
                        </c:if>
                        <c:if test="${user.userStatus == block}">
                            <td>
                                <span class="label label-danger">Не активный</span>
                            </td>
                        </c:if>
                        <td class="text-right">
                            <c:forEach items="${user.roles}" var="role">
                                ${role.roleTitle}<br/>
                            </c:forEach>
                        </td>

                        <td class="text-right">
                            <c:url value="/admin/user/edit" var="editUsr">
                                <c:param name="id" value="${user.id}"/>
                            </c:url>
                            <a href="${editUsr}" class="btn btn-default btn-xs">
                                <i class="fa fa-pencil" aria-hidden="true"></i> Редактировать
                            </a>
                            <c:url value="/admin/user/delete" var="deleteUsr">
                                <c:param name="id" value="${user.id}"/>
                            </c:url>
                            <a href="${deleteUsr}" class="btn btn-xs btn-danger">
                                <i class="fa fa-times-circle" aria-hidden="true"></i> Удалить
                            </a>

                            <form action="<%= UserAdminRoutes.BLOCK%>" method="POST">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <button type="submit" class="btn btn-default btn-xs">
                                    <i class="fa fa-ban" aria-hidden="true"></i> Заблокировать
                                </button>
                            </form>

                            <form action="<%= UserAdminRoutes.ACTIVE%>" method="POST">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <button type="submit" class="btn btn-default btn-xs">
                                    Активировать
                                </button>
                            </form>

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
            <c:url value="${url}" var="urlPrev">
                <c:param name="query" value="${searchUserAdminResponse.query}"/>
                <c:param name="currentPage" value="${searchUserAdminResponse.prevNum()}"/>
            </c:url>
            <li><a href="${urlPrev}"><i class="entypo-left-open-mini"></i></a></li>

            <c:forEach items="${searchUserAdminResponse.paginator()}" var="page">
                <c:url value="${url}" var="urlPage">
                    <c:param name="query" value="${searchUserAdminResponse.query}"/>
                    <c:param name="currentPage" value="${page}"/>
                </c:url>
                <li class="<c:if test="${searchUserAdminResponse.currentPage eq page}">active</c:if>">
                    <a href="${urlPage}">${page + 1}</a>
                </li>
            </c:forEach>
            <c:url value="${url}" var="urlNext">
                <c:param name="query" value="${searchUserAdminResponse.query}"/>
                <c:param name="currentPage" value="${searchUserAdminResponse.nextNum()}"/>
            </c:url>
            <li><a href="${urlNext}"><i class="entypo-right-open-mini"></i></a></li>
        </ul>
    </div>
</div>

<%--
<script type="text/javascript">
    var responsiveHelper;
    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    var tableContainer;

    jQuery(document).ready(function ($) {
        tableContainer = $("#table-1");

        tableContainer.dataTable({
            "sPaginationType": "bootstrap",
            "aLengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            "bStateSave": true,


            // Responsive Settings
            bAutoWidth: false,
            fnPreDrawCallback: function () {
                // Initialize the responsive datatables helper once.
                if (!responsiveHelper) {
                    responsiveHelper = new ResponsiveDatatablesHelper(tableContainer, breakpointDefinition);
                }
            },
            fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                responsiveHelper.createExpandIcon(nRow);
            },
            fnDrawCallback: function (oSettings) {
                responsiveHelper.respond();
            }
        });

        $(".dataTables_wrapper select").select2({
            minimumResultsForSearch: -1
        });
    });
</script>--%>
