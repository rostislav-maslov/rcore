<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Добавление страницу</h3>
            </div>
            <div class="widget-content">

                <form:form action="<%= PagesAdminRoutes.ADD%>" modelAttribute="pageView" method="POST">

                    <form:errors path="*" cssClass="alert alert-warning" element="div" />

                    <label for="id">Url </label>
                    <form:input path="id" cssClass="form-control" id="id" />
                    <label for="title">Заголовок </label>
                    <form:input path="title" cssClass="form-control" id="title" />
                    <label for="content">Контент </label>
                    <form:textarea path="content" cssClass="form-control" id="content" />
                    <label for="tagsLine">Все теги </label>
                    <form:input path="tagsLine" cssClass="form-control" id="tagsLine" />
                    <label for="status">Статус: </label>
                    <form:select path="status" cssClass="form-control"  id="status">
                        <c:forEach items="${statuses}" var="m">
                            <c:if test="${m == pageForm.status}">
                                <form:option value="${m}" selected="true">${m}</form:option>
                            </c:if>
                            <c:if test="${m != pageForm.status}">
                                <form:option value="${m}">${m}</form:option>
                            </c:if>
                        </c:forEach>
                    </form:select>


                    <%--protected Date startPublishing;--%>
                    <%--protected Date finishPublishing;--%>
                    <label for="createdByAlias">Создан кем: </label>
                    <form:input path="createdByAlias" cssClass="form-control" id="createdByAlias" />
                    <label for="metaDescription">Meta Description: </label>
                    <form:input path="metaDescription" cssClass="form-control" id="metaDescription" />
                    <label for="metaKeywords">Meta Keywords: </label>
                    <form:input path="metaKeywords" cssClass="form-control" id="metaKeywords" />
                    <label for="author">Автор </label>
                    <form:input path="author" cssClass="form-control" id="author" />
                    <label for="contentRights">Права на контент: </label>
                    <form:input path="contentRights" cssClass="form-control" id="contentRights" />
                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>