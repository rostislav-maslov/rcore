<%@ page import="com.ub.core.picture.routes.PicturesAdminRoutes" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Загрузка файла на сервер</h3>
            </div>
            <div class="widget-content">
                <form method="POST" enctype="multipart/form-data" action="<%= PicturesAdminRoutes.ADD%>">
                    <label for="file">Выберете файл для загрузки :</label>
                    <input type="file" name="file" id="file"/>

                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">
                            Сохранить
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
