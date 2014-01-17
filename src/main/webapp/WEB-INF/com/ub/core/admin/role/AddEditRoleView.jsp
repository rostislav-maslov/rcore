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

                <form:form action="${backUrl}" modelAttribute="addEditRoleView">

                    <form:errors path="*" cssClass="alert alert-warning" element="div" />

                    <label for="roleTitle">Название </label>
                    <form:input path="roleTitle" cssClass="form-control" id="roleTitle" />

                    <label for="roleDescription">Описание </label>
                    <form:textarea path="roleDescription" cssClass="form-control" id="roleDescription" />

                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>


                </form:form>

            </div>
        </div>
    </div>
</div>