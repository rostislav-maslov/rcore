<%@ page import="com.ub.odnoklassniki.routes.AppPropertiesOkRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="fa fa-tasks" aria-hidden="true"></i> Изменение настроек Odnoklassniki </h3>
            </div>
            <div class="widget-content">
                <form:form method="POST" action="<%= AppPropertiesOkRoutes.EDIT%>" modelAttribute="appPropertiesOkDoc">

                    <form:errors path="*" cssClass="alert alert-warning" element="div"/>
                    <form:hidden path="id"/>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="CLIENT_ID">CLIENT_ID</label>
                                <form:input path="CLIENT_ID" cssClass="form-control" id="CLIENT_ID"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="PUBLIC_KEY">PUBLIC_KEY</label>
                                <form:input path="PUBLIC_KEY" cssClass="form-control" id="PUBLIC_KEY"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="REDIRECT_URI">REDIRECT_URI</label>
                                <form:input path="REDIRECT_URI" cssClass="form-control" id="REDIRECT_URI"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="RESPONSE_TYPE">RESPONSE_TYPE</label>
                                <form:input path="RESPONSE_TYPE" cssClass="form-control" id="RESPONSE_TYPE"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="SECRET_KEY">SECRET_KEY</label>
                                <form:input path="SECRET_KEY" cssClass="form-control" id="SECRET_KEY"/>
                            </div>
                        </div>
                    </div>

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
