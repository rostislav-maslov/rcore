<%@ page import="com.ub.core.seo.robotsCore.routes.RobotsCoreAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<div class="row">
    <div class="col-lg-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Редактировать </h3>
            </div>
            <div class="widget-content">

                <form action="<%= RobotsCoreAdminRoutes.EDIT%>" method="POST">
                    <div class="row">
                        <div class="col-lg-12">
                            <label for="robots">robots.txt</label>
                            <textarea rows="20" name="robots" class="form-control" id="robots" >${robotsCoreDoc.robots}</textarea>
                        </div>
                    </div>

                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>