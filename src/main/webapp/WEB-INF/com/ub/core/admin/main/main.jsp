<%@ page import="com.ub.core.menu.models.fields.MenuFields" %>
<%@ page import="com.ub.core.pages.routes.PagesAdminRoutes" %>
<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="/WEB-INF/widgets/contactForm" prefix="cf" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Neon Admin Panel"/>
    <meta name="author" content=""/>

    <title>Neon | Forms</title>

    <link rel="stylesheet" href="<c:url value="/static/a/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/a/css/font-icons/entypo/css/entypo.css"/>">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
    <link rel="stylesheet" href="<c:url value="/static/a/css/neon.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/a/css/custom.css"/>">

    <script src="<c:url value="/static/a/js/jquery-1.10.2.min.js"/>"></script>

    <%--<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>--%>
    <%--<![endif]-->--%>

</head>
<body class="page-body" data-url="">

<div class="page-container">
    <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

    <div class="sidebar-menu">


        <header class="logo-env">

            <!-- logo -->
            <div class="logo">
                <a href="/">
                    <img src="<c:url value="/static/a/images/logo@2x.png"/>" width="95" alt=""/>
                </a>
            </div>

            <!-- logo collapse icon -->

            <div class="sidebar-collapse">
                <a href="#" class="sidebar-collapse-icon with-animation">
                    <!-- add class "with-animation" if you want sidebar to have animation during expanding/collapsing transition -->
                    <i class="entypo-menu"></i>
                </a>
            </div>


            <!-- open/close menu icon (do not remove if you want to enable menu on mobile devices) -->
            <div class="sidebar-mobile-menu visible-xs">
                <a href="#" class="with-animation"><!-- add class "with-animation" to support animation -->
                    <i class="entypo-menu"></i>
                </a>
            </div>

        </header>


        <ul id="main-menu" class="">
            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
            <!-- Search Bar -->
            <%--<li id="search">--%>
            <%--<form method="get" action="">--%>
            <%--<input type="text" name="q" class="search-input" placeholder="Search something..."/>--%>
            <%--<button type="submit">--%>
            <%--<i class="entypo-search"></i>--%>
            <%--</button>--%>
            <%--</form>--%>
            <%--</li>--%>
            <tiles:insertAttribute name="mainMenu"/>
        </ul>

    </div>
    <div class="main-content">
        <div class="row">

            <!-- Profile Info and Notifications -->
            <div class="col-md-6 col-sm-8 clearfix">
            </div>
            <!-- Raw Links -->
            <div class="col-md-6 col-sm-4 clearfix hidden-xs">

                <ul class="list-inline links-list pull-right">
                    <li>
                        <a href="<c:url value="/admin/logout" />">
                            Выйти <i class="entypo-logout right"></i>
                        </a>
                    </li>
                </ul>

            </div>

        </div>

        <hr/>

        <selection id="main_content">
            <tiles:insertAttribute name="content"/>
        </selection>

        <!-- Footer -->
        <footer class="main">


            &copy; 2014 <strong>UBCore</strong> Admin Engine by <a href="http://unitbean.com" target="_blank">LLC
            UnitBean</a>

        </footer>
    </div>


</div>


<tiles:insertAttribute name="contentModals" defaultValue=""/>

<tiles:useAttribute id="contentModalsListItems" name="contentModalsList" classname="java.util.List" />
<c:forEach var="contentModalsListItem" items="${contentModalsListItems}">
    <tiles:insertAttribute value="${contentModalsListItem}" flush="true" />
</c:forEach>
<link rel="stylesheet" href="<c:url value="/static/a/js/wysihtml5/bootstrap-wysihtml5.css"/>">
<link rel="stylesheet" href="<c:url value="/static/a/js/datatables/responsive/css/datatables.responsive.css"/>">
<link rel="stylesheet" href="<c:url value="/static/a/js/select2/select2-bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/static/a/js/select2/select2.css"/>">
<link rel="stylesheet" href="<c:url value="/static/a/js/selectboxit/jquery.selectBoxIt.css"/>">
<link rel="stylesheet" href="<c:url value="/static/a/css/font-icons/font-awesome/css/font-awesome.min.css"/>">


<!-- Bottom Scripts -->
<script src="<c:url value="/static/a/js/gsap/main-gsap.js"/>"></script>
<script src="<c:url value="/static/a/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"/>"></script>
<script src="<c:url value="/static/a/js/menu/autoopenmenu.js" />" type="text/javascript"></script>
<script src="<c:url value="/static/a/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/a/js/joinable.js"/>"></script>
<script src="<c:url value="/static/a/js/resizeable.js"/>"></script>
<script src="<c:url value="/static/a/js/neon-api.js"/>"></script>
<script src="<c:url value="/static/a/js/jquery.nestable.js"/>"></script>
<script src="<c:url value="/static/a/js/bootstrap-switch.min.js"/>"></script>
<script src="<c:url value="/static/a/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/static/a/js/datatables/TableTools.min.js"/>"></script>
<script src="<c:url value="/static/a/js/dataTables.bootstrap.js"/>"></script>
<script src="<c:url value="/static/a/js/datatables/jquery.dataTables.columnFilter.js"/>"></script>
<script src="<c:url value="/static/a/js/datatables/lodash.min.js"/>"></script>
<script src="<c:url value="/static/a/js/datatables/responsive/js/datatables.responsive.js"/>"></script>

<script src="<c:url value="/static/a/js/neon-chat.js"/>"></script>
<script src="<c:url value="/static/a/js/neon-custom.js"/>"></script>
<script src="<c:url value="/static/a/js/neon-demo.js"/>"></script>
<script src="<c:url value="/static/a/js/wysihtml5/wysihtml5-0.4.0pre.min.js"/>"></script>
<script src="<c:url value="/static/a/js/wysihtml5/bootstrap-wysihtml5.js"/>"></script>
<script src="<c:url value="/static/a/js/ckeditor/ckeditor.js"/>"></script>
<script src="<c:url value="/static/a/js/ckeditor/adapters/jquery.js"/>"></script>
<script src="<c:url value="/static/a/js/select2/select2.min.js"/>"></script>
<script src="<c:url value="/static/a/js/bootstrap-tagsinput.min.js"/>"></script>
<script src="<c:url value="/static/a/js/typeahead.min.js"/>"></script>
<script src="<c:url value="/static/a/js/selectboxit/jquery.selectBoxIt.min.js"/>"></script>
<script src="<c:url value="/static/a/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/static/a/js/bootstrap-timepicker.min.js"/>"></script>
<script src="<c:url value="/static/a/js/bootstrap-colorpicker.min.js"/>"></script>
<script src="<c:url value="/static/a/js/daterangepicker/moment.min.js"/>"></script>
<script src="<c:url value="/static/a/js/daterangepicker/daterangepicker.js"/>"></script>
<script src="<c:url value="/static/a/js/selectboxit/jquery.selectBoxIt.min.js"/>"></script>
<script src="<c:url value="/static/a/js/toastr.js"/>"></script>
<tiles:insertAttribute name="footerJs"/>
</body>
</html>
