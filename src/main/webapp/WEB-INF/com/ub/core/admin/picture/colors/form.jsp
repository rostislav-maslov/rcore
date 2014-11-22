<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<form:errors path="*" cssClass="alert alert-warning" element="div" />
<form:hidden path="id"/>


<div class="row">
    <div class="col-lg-12">
        <label for="title">Заголовок</label>
        <form:input path="title" cssClass="form-control" id="title"/>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <label for="hex">Цвет</label>
        <form:input path="hex" cssClass="form-control colorpicker colorpicker-element" data-format="hex" id="hex"/>
        <div class="input-group-addon">
            <i class="color-preview" style="background-color: rgb(90, 61, 61);"></i>
        </div>
    </div>
</div>

