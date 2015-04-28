<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-md-12">
        <form action="<%= UserAdminRoutes.INIT_USER%>" method="POST">
            <input name="email" type="email" class="form-control"/><br/>
            <input name="password" type="password" class="form-control"/><br/>
            <button class="btn btn-success">создать пользователя</button>
        </form>
    </div>
</div>