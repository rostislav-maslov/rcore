<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="row">
    <div class="col-md-6">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> </h3>
            </div>
            <div class="widget-content">

                <form:form action="${backUrl}" modelAttribute="addEditUserView">

                    <form:errors path="*" cssClass="alert alert-warning" element="div" />

                    <label for="email">Email </label>
                    <form:input path="email" cssClass="form-control" id="email" />

                    <label for="password">Пароль </label>
                    <form:password path="password" cssClass="form-control" id="password" />

                    <label for="role">Роль: </label>
                    <form:select path="role" cssClass="form-control"  id="role">
                        <%--<form:option value="ADMIN">администратор</form:option>--%>
                        <%--<form:option value="SUPER_ADMIN">супер администратор</form:option>--%>
                         <c:forEach items="${roles}" var="role">
                            <form:option value="${role.id}">${role.roleTitle}</form:option>-
                         </c:forEach>
                    </form:select>

                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>


                </form:form>

            </div>
        </div>
    </div>
</div>