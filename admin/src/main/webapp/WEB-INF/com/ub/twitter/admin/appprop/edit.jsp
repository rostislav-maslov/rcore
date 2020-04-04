<%@ page import="com.ub.vk.routes.AppPropertiesRoutes" %>
<%@ page import="com.ub.twitter.routes.TwitterRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="fa fa-tasks" aria-hidden="true"></i> Изменение настроек twitter </h3>
            </div>
            <div class="widget-content">
                <form:form method="POST" action="<%= TwitterRoutes.EDIT%>" modelAttribute="twitterAppPropertiesDoc">

                    <form:errors path="*" cssClass="alert alert-warning" element="div"/>
                    <form:hidden path="id"/>


                    <div class="row">
                        <div class="col-md-12">
                            <label for="yourApiKey">your api key</label>
                            <form:input path="yourApiKey" cssClass="form-control" id="yourApiKey"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <label for="yourApiSecret">your api secret</label>
                            <form:input type="password" path="yourApiSecret" cssClass="form-control" id="yourApiSecret"/>
                        </div>
                    </div>

                    <br>

                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">
                            <s:message code="ubcore.admin.buttonSave"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
