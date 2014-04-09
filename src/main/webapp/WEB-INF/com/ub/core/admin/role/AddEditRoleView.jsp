<%@ page import="com.ub.core.user.routes.RoleAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="row">
    <div class="col-md-6">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i></h3>
            </div>
            <div class="widget-content">

                <form action="<%= RoleAdminRoutes.ADD%>" method="post">

                    <form:errors path="*" cssClass="alert alert-warning" element="div" />

                    <label for="role">Выбрать роль: </label>
                    <select name="role" id="role" class="form-control">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.roleTitle}</option>
                        </c:forEach>
                    </select>

                    <input name="user" value="${userId}" type="hidden"/>

                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>


                </form>

            </div>
        </div>
    </div>
</div>