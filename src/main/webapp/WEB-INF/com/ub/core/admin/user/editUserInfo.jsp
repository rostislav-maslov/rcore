<%@ page import="com.ub.core.user.routes.RoleAdminRoutes" %>
<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
﻿
<div class="row">
    <div class="col-md-6">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i></h3>
            </div>
            <div class="widget-content">

                <form:form action="${backUrl}" modelAttribute="userDoc">

                    <form:errors path="*" cssClass="alert alert-warning" element="div"/>
                    <form:hidden path="id"/>

                    <label for="email">Email </label>
                    <form:input path="email" cssClass="form-control" id="email"/>

                    <label for="firstName">Имя </label>
                    <form:input path="firstName" cssClass="form-control" id="firstName"/>


                    <label for="lastName">Фамилия </label>
                    <form:input path="lastName" cssClass="form-control" id="lastName"/>


                    <br>

                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить
                        </button>
                    </div>


                </form:form>

            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <h2>Роли</h2>
        <c:url value="<%= RoleAdminRoutes.ADD%>" var="roleAdd">
            <c:param name="id" value="${userDoc.id}"></c:param>
        </c:url>
        <a href="${roleAdd}"> Добавить роль </a>
        <br>

        <c:forEach items="${userDoc.roles}" var="r">
            <form action="<%= RoleAdminRoutes.DELETE%>" method="post">
                <input type="hidden" name="user" value="${userDoc.id}"/>
                <input type="hidden" name="role" value="${r.id}"/>

                <p>${r.roleTitle} <button type="submit" class="btn btn-xs">X</button>
                </p>
            </form>

        </c:forEach>

    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <h2>Изменить пароль пользователя</h2>

        <form action="<%= UserAdminRoutes.EDIT_PASSWORD%>" method="post">
            <input name="userId" value="${userDoc.id}" type="hidden"/>

            <input name="password" class="form-control" type="password"/>
            <br>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить
                </button>
            </div>
        </form>

    </div>
</div>