<%@ page import="com.ub.core.user.routes.RoleAdminRoutes" %>
<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel minimal minimal-gray">

            <div class="panel-heading">
                <div class="panel-title"><h4>Редактирование информации пользователя </h4></div>
                <div class="panel-options">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#info" data-toggle="tab">Информация</a></li>
                        <li><a href="#logs" data-toggle="tab">История входов</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="panel-body">

            <div class="tab-content">
                <div class="tab-pane active" id="info">
                    <div class="row">
                        <div class="col-md-12">
                            <form:form action="${backUrl}" modelAttribute="userDocHack">
                                <form:errors path="*" cssClass="alert alert-warning" element="div"/>
                                <form:hidden path="id"/>

                                <div class="form-group">
                                    <label for="email">Email </label>
                                    <form:input path="email" cssClass="form-control" id="email"/>
                                </div>
                                <div class="form-group">
                                    <label for="firstName">Имя </label>
                                    <form:input path="firstName" cssClass="form-control" id="firstName"/>
                                </div>
                                <div class="form-group">
                                    <label for="lastName">Фамилия </label>
                                    <form:input path="lastName" cssClass="form-control" id="lastName"/>
                                </div>
                                <br>
                                <div class="form-group">
                                    <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">
                                        Сохранить
                                    </button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Роли</h2>
                            <c:url value="<%= RoleAdminRoutes.ADD%>" var="roleAdd">
                                <c:param name="id" value="${userDocHack.id}"/>
                            </c:url>
                            <a href="${roleAdd}"> Добавить роль </a>
                            <br>

                            <c:forEach items="${userDocHack.roles}" var="r">
                                <form action="<%= RoleAdminRoutes.DELETE%>" method="post">
                                    <input type="hidden" name="user" value="${userDocHack.id}"/>
                                    <input type="hidden" name="role" value="${r.id}"/>

                                    <p>${r.roleTitle}
                                        <button type="submit" class="btn btn-xs">X</button>
                                    </p>
                                </form>

                            </c:forEach>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Изменить пароль пользователя</h2>

                            <form action="<%= UserAdminRoutes.EDIT_PASSWORD%>" method="post">
                                <input name="userId" value="${userDocHack.id}" type="hidden"/>

                                <input name="password" class="form-control" type="password"/>
                                <br>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Сохранить
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
                <div class="tab-pane" id="logs">
                    <h3><i class="fa fa-tasks" aria-hidden="true"></i> История входов пользователя </h3>
                    <table class="table table-bordered" id="table-1">
                        <thead>
                        <tr>
                            <th>№</th>
                            <th>Дата</th>
                            <th>IP</th>
                            <th>Статус</th>
                            <th>Бразуер</th>
                            <th>Операционная система</th>
                            <th>Тип устройства</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userLogs}" var="log" varStatus="status">

                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${log.createdAt}</td>
                                <td>${log.ip}</td>
                                <td>${log.status.title}</td>
                                <td>${log.browser}(${log.browserVersion})</td>
                                <td>${log.operatingSystem}</td>
                                <td>${log.deviceType}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>