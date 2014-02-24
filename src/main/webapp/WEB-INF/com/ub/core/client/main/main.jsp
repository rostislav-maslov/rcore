<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="${view.htmlLang}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="<c:url value="/static/c/ico/favicon.png"/>">

    <title>${view.title}</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/c/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<c:url value="/static/c/css/bootstrap-theme.min.css"/>" rel="stylesheet">

    <tiles:insertAttribute name="main.header.css"/>

    <!--[if lt IE 9]><script src="<c:url value="/static/c/js/ie8-responsive-file-warning.js"/>"></script><![endif]-->

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <tiles:insertAttribute name="main.header.js"/>
</head>

<body>
<tiles:insertAttribute name="main.content"/>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="<c:url value="/static/c/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/c/js/holder.js"/>"></script>
<tiles:insertAttribute name="main.body.js"/>

${yandexMetricaScript}
</body>
</html>
