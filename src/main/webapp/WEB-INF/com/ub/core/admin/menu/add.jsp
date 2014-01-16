<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="row">
    <div class="col-md-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="icon-tasks"></i> Добавление меню</h3>
            </div>
            <div class="widget-content">

                <form:form modelAttribute="menuForm" method="POST">

                    <form:errors path="*" cssClass="alert alert-warning" element="div" />

                    <%--<form:hidden path="id" cssClass="form-control" id="id" />--%>

                    <label for="name">Имя </label>
                    <form:input path="name" cssClass="form-control" id="name" />

                    <label for="url">URL: </label>
                    <form:input path="url" cssClass="form-control" id="url" />

                    <label for="parent">Родительское меню: </label>
                    <form:select path="parent" cssClass="form-control"  id="parent">
                        <%--<form:option value="ADMIN">администратор</form:option>--%>
                        <%--<form:option value="SUPER_ADMIN">супер администратор</form:option>--%>
                        <form:option value="">-</form:option>
                        <c:forEach items="${allMenu}" var="m">
                            <c:if test="${m.id == menuForm.id}">
                                <form:option value="${m.id}" selected="true">${m.name}</form:option>-
                            </c:if>
                            <form:option value="${m.id}">${m.name}</form:option>-
                        </c:forEach>
                    </form:select>

                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Сохранить</button>
                    </div>


                </form:form>

            </div>
        </div>
    </div>
</div>