<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ page import="com.ub.core.user.models.UserStatusEnum" %>
<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
﻿
<div class="widget widget-blue">
    <div class="widget-title">
        <div class="widget-controls">
            <div class="dropdown" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings">
                <a href="#" data-toggle="dropdown" class="widget-control widget-control-settings"><i
                        class="icon-cog"></i></a>
                <ul class="dropdown-menu dropdown-menu-small" role="menu" aria-labelledby="dropdownMenu1">
                    <li class="dropdown-header">Set Widget Color</li>
                    <li><a data-widget-color="blue" class="set-widget-color" href="#">Blue</a></li>
                    <li><a data-widget-color="red" class="set-widget-color" href="#">Red</a></li>
                    <li><a data-widget-color="green" class="set-widget-color" href="#">Green</a></li>
                    <li><a data-widget-color="orange" class="set-widget-color" href="#">Orange</a></li>
                    <li><a data-widget-color="purple" class="set-widget-color" href="#">Purple</a></li>
                </ul>
            </div>
            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top"
               title="" data-original-title="Refresh"><i class="icon-refresh"></i></a>
            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top"
               title="" data-original-title="Minimize"><i class="icon-minus-sign"></i></a>
            <a href="#" class="widget-control widget-control-remove" data-toggle="tooltip" data-placement="top" title=""
               data-original-title="Remove"><i class="icon-remove-sign"></i></a>
        </div>
        <h3><i class="icon-table"></i> Пользователи</h3>
    </div>
    <div class="widget-content">
        <p>Всего - ${userList.size()}</p>

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

                <c:forEach items="${userList}" var="user">
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
                            <a href="${editUsr}" class="btn btn-default btn-xs">Редактировать</a>
                            <c:url value="/admin/user/delete" var="deleteUsr">
                                <c:param name="id" value="${user.id}"/>
                            </c:url>
                            <a href="${deleteUsr}" class="btn"><i class="icon-remove"></i></a>

                            <form action="<%= UserAdminRoutes.BLOCK%>" method="POST">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <button type="submit" class="btn btn-default btn-xs">
                                    Заблокировать
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
</script>