<%@ page import="com.ub.core.menu.models.fields.MenuFields" %>
<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ page import="com.ub.vk.routes.AppPropertiesRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Изменение настроек Vk </h3>
            </div>
            <div class="widget-content">
                <form:form method="POST" action="<%= AppPropertiesRoutes.EDIT%>" modelAttribute="appPropertiesVkDoc">

                    <form:errors path="*" cssClass="alert alert-warning" element="div"/>
                    <form:hidden path="id"/>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="APP_ID">APP_ID</label>
                            <form:input path="APP_ID" cssClass="form-control" id="APP_ID"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="PERMISSIONS">PERMISSIONS</label>
                            <form:input path="PERMISSIONS" cssClass="form-control" id="PERMISSIONS"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="REDIRECT_URI">REDIRECT_URI</label>
                            <form:input path="REDIRECT_URI" cssClass="form-control" id="REDIRECT_URI"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="response_type">response_type</label>
                            <form:input path="response_type" cssClass="form-control" id="response_type"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="APP_SECRET">APP_SECRET</label>
                            <form:input path="APP_SECRET" cssClass="form-control" id="APP_SECRET"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="API_VERSION">API_VERSION</label>
                            <form:input path="API_VERSION" cssClass="form-control" id="API_VERSION"/>
                        </div>
                    </div>

                    <br>

                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
