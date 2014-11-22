<%@ page import="com.ub.core.picture.routes.PicturesAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Вы собираетесь удалить изображение! </h3>
            </div>
            <div class="widget-content">

                <form action="<%= PicturesAdminRoutes.DELETE%>" method="POST">
                    <input type="hidden" value="${id}" name="id"/>
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Подтвердите удаление!</h2>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Удалить</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>