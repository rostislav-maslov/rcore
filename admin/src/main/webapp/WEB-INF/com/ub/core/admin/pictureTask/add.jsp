<%@ page import="com.ub.core.pictureTask.routes.PictureTaskAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="/WEB-INF/com/ub/core/admin/main/components/pageHeader.jsp"/>
<div class="row">
    <div class="col-lg-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="fa fa-tasks" aria-hidden="true"></i> Добавить</h3>
            </div>
            <div class="widget-content">
                <form:form action="<%= PictureTaskAdminRoutes.ADD%>" modelAttribute="pictureTaskDoc" method="POST">
                    <c:set value="${pictureTaskDoc}" var="pictureTaskDoc" scope="request"/>
                    <jsp:include page="form.jsp"/>
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