<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Загрузка файла на сервер</h2>

<form:form method="POST" commandName="fileUploadView"
           enctype="multipart/form-data">

    <form:errors path="*" cssClass="errorblock" element="div"/>

    Выберете файл для загрузки : <input type="file" name="file"/>
    <input type="submit" value="Загрузить"/>
		<span><form:errors path="file" cssClass="error"/>
		</span>

</form:form>
