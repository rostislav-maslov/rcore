<%@ page import="com.ub.core.user.views.UserListView" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="widget widget-blue">
<div class="widget-title">
    <div class="widget-controls">
        <div class="dropdown" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings">
            <a href="#" data-toggle="dropdown" class="widget-control widget-control-settings"><i class="icon-cog"></i></a>
            <ul class="dropdown-menu dropdown-menu-small" role="menu" aria-labelledby="dropdownMenu1">
                <li class="dropdown-header">Set Widget Color</li>
                <li><a data-widget-color="blue" class="set-widget-color" href="#">Blue</a></li>
                <li><a data-widget-color="red" class="set-widget-color" href="#">Red</a></li>
                <li><a data-widget-color="green" class="set-widget-color" href="#">Green</a></li>
                <li><a data-widget-color="orange" class="set-widget-color" href="#">Orange</a></li>
                <li><a data-widget-color="purple" class="set-widget-color" href="#">Purple</a></li>
            </ul>
        </div>
        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="icon-refresh"></i></a>
        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="icon-minus-sign"></i></a>
        <a href="#" class="widget-control widget-control-remove" data-toggle="tooltip" data-placement="top" title="" data-original-title="Remove"><i class="icon-remove-sign"></i></a>
    </div>
    <h3><i class="icon-table"></i> Users Table</h3>
</div>
<div class="widget-content">
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th><div class="checkbox"><input type="checkbox"></div></th>
                <th><%= UserListView.TITLE_EMAIL %></th>
                <th><%= UserListView.TITLE_STATUS %></th>
                <th><%= UserListView.TITLE_ROLE %></th>
                <th><%= UserListView.TITLE_CONFIGURATION %></th>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${userList}" var="user">
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>${user.email}</td>
                <c:if test="${user.userDoc.status == true}">
                <td><span class="label label-success">Active</span></td>
                </c:if>
                <c:if test="${user.userDoc.status == false}">
                    <td><span class="label label-danger">Disable</span></td>
                </c:if>
                <td class="text-right">
                <c:forEach items="${user.userDoc.roleDocList}" var="roles">
                    &nbsp ${roles}
                </c:forEach>
                </td>

                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="<c:url value="/admin/user/delete/${user.email}" />" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            </c:forEach>


            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>2</td>
                <td>Petey Cruiser</td>
                <td>Argentina</td>
                <td class="text-right">$123.25</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>3</td>
                <td>Anna Sthesia</td>
                <td>Australia</td>
                <td class="text-right">$523.45</td>
                <td><span class="label label-danger">Closed</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr class="success">
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>4</td>
                <td>Paul Molive</td>
                <td>Mexico</td>
                <td class="text-right">$4664.43</td>
                <td><span class="label label-warning">Pending</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>5</td>
                <td>Anna Mull</td>
                <td>Chech Republic</td>
                <td class="text-right">$3.54</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>6</td>
                <td>Gail Forcewind</td>
                <td>South Korea</td>
                <td class="text-right">$754.23</td>
                <td><span class="label label-danger">Closed</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>7</td>
                <td>Paige Turner</td>
                <td>China</td>
                <td class="text-right">$745.23</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr class="warning">
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>8</td>
                <td>Bob Frapples</td>
                <td>Uruguay</td>
                <td class="text-right">$75.54</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>9</td>
                <td>Walter Melon</td>
                <td>Serbia</td>
                <td class="text-right">$85.94</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>10</td>
                <td>Nick R. Bocker</td>
                <td>Sweden</td>
                <td class="text-right">$33.23</td>
                <td><span class="label label-warning">Pending</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>11</td>
                <td>Barb Ackue</td>
                <td>New Zealand</td>
                <td class="text-right">$25.94</td>
                <td><span class="label label-danger">Closed</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>12</td>
                <td>Buck Kinnear</td>
                <td>Brazil</td>
                <td class="text-right">$25.94</td>
                <td><span class="label label-warning">Pending</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>13</td>
                <td>Greta Life</td>
                <td>Russia</td>
                <td class="text-right">$25.94</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr class="danger">
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>14</td>
                <td>Ira Membrit</td>
                <td>United Kingdom</td>
                <td class="text-right">$25.94</td>
                <td><span class="label label-success">Active</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            <tr>
                <td><div class="checkbox"><input type="checkbox"></div></td>
                <td>15</td>
                <td>Shonda Leer</td>
                <td>Croatia</td>
                <td class="text-right">$25.94</td>
                <td><span class="label label-danger">Closed</span></td>
                <td class="text-right">
                    <a href="#" class="btn btn-default btn-xs">edit</a>
                    <a href="#" class="btn btn-danger btn-xs remove-tr"><i class="icon-remove"></i></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>