<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" >
    <%--<input type="hidden" value="upload"/>--%>
		<span><form:errors path="file" cssClass="error"/>
		</span>
    <div class="error-page-wrapper">
        <div class="picture-w">
            <i class="icon-exclamation-sign"></i>
        </div>
        <h1>Подтвердите удаление страницы</h1>
        <h3>Вы собираетесь далить страницу
            <br/>${name}
            <br/>Восстановить запись будет невозможно</h3>
        <input type="submit" class="btn btn-primary btn-lg" value="Удалить"/>
    </div>
</form:form>
