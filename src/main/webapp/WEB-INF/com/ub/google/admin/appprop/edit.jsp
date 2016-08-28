<%@ page import="com.ub.google.routes.GooglePlusRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div class="row">
  <div class="col-md-12">
    <div class="widget widget-green" id="widget_profit_chart">
      <div class="widget-title">
        <h3><i class="fa fa-tasks" aria-hidden="true"></i> Изменение настроек Google plus </h3>
      </div>
      <div class="widget-content">
        <form:form method="POST" action="<%= GooglePlusRoutes.EDIT%>" modelAttribute="appPropertiesGoogleDoc">

          <form:errors path="*" cssClass="alert alert-warning" element="div"/>
          <form:hidden path="id"/>

          <div class="row">
            <div class="col-md-12">
              <label for="app_key">Api key</label>
              <form:input path="app_key" cssClass="form-control" id="app_key"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <label for="client_id">Client id</label>
              <form:input path="client_id" cssClass="form-control" id="client_id"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <label for="client_secret">Client secret</label>
              <form:input path="client_secret" cssClass="form-control" id="client_secret"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <label for="redirect_uri">Redirect uri</label>
              <form:input path="redirect_uri" cssClass="form-control" id="redirect_uri"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <label for="response_type">Response type</label>
              <form:input path="response_type" cssClass="form-control" id="response_type"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <label for="application_name">Application name</label>
              <form:input path="application_name" cssClass="form-control" id="application_name"/>
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
