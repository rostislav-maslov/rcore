<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <form action="<%= UserAdminRoutes.INIT_USER%>" method="POST">
            <div class="form-group">
                <label for="email">Email</label>
                <input name="email" type="email" id="email" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" id="password" class="form-control"/>
            </div>
            <button class="btn btn-success">Создать пользователя</button>
        </form>
    </div>
</div>