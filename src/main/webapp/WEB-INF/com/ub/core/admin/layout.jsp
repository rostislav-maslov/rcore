<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>﻿
<%--<%@ taglib uri="/WEB-INF/widgets/contactForm" prefix="cf" %>--%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">



    <link rel='stylesheet' href='<c:url value="/static/a/b7fdc65bf39ca5aa7c146814f889de3a.css" />'>

    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300,400,700|Open+Sans:400,700,300|Roboto:100,300,400,700|Roboto+Condensed:300,400,700' rel='stylesheet' type='text/css'>

    <link href="<c:url value="/static/a/assets/favicon.ico" />" rel="shortcut icon">
    <link href="<c:url value="/static/a/assets/apple-touch-icon.png" />" rel="apple-touch-icon">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    @javascript html5shiv respond.min
    <![endif]-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

    <title>Административная панель</title>

</head>

<body>

<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-42863888-3', 'pinsupreme.com');
    ga('send', 'pageview');

</script>
<div class="all-wrapper fixed-header left-menu">
    <div class="page-header">
        <div class="header-links hidden-xs">
            <div class="top-search-w pull-right">
                <input type="text" class="top-search" placeholder="Search"/>
            </div>
            <div class="dropdown hidden-sm hidden-xs">
                <a href="#" data-toggle="dropdown" class="header-link"><i class="icon-bolt"></i> User Alerts <span class="badge alert-animated">5</span></a>
            </div>
            <div class="dropdown hidden-sm hidden-xs">
                <a href="#" data-toggle="dropdown" class="header-link"><i class="icon-cog"></i> Settings</a>
            </div>

            <div class="dropdown">
                <a href="#" class="header-link clearfix" data-toggle="dropdown">
                    <div class="avatar">
                        <img src="assets/images/avatar-small.jpg" alt="">
                    </div>
                    <div class="user-name-w">
                        Lionel Messi <i class="icon-caret-down"></i>
                    </div>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </div>
        </div>
        <a class="logo hidden-xs" href="includes/sidebars/_sidebar_dashboard.html"><i class="icon-rocket"></i></a>
        <a class="menu-toggler" href="#"><i class="icon-reorder"></i></a>
        <h1>Dashboard</h1>
    </div>
    <div class="side">
        <div class="sidebar-wrapper">
            <ul>
                <li class='current'>
                    <a class='current' href="index.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Пользователи">
                        <i class="icon-user"></i>
                    </a>
                </li>
                <%--<li>--%>
                    <%--<a href="charts.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Charts">--%>
                        <%--<i class="icon-bar-chart"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="forms.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="AAA">--%>
                        <%--<i class="icon-file-text-alt"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="elements.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="UI Elements">--%>
                        <%--<span class="badge"></span>--%>
                        <%--<i class="icon-code-fork"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="table.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tables">--%>
                        <%--<i class="icon-th"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="grid.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Layouts">--%>
                        <%--<i class="icon-font"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="calendar.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Calendar">--%>
                        <%--<span class="badge">5</span>--%>
                        <%--<i class="icon-calendar"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="page_search.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Extra Pages">--%>
                        <%--<i class="icon-signin"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
            </ul>
        </div>
        <div class="sub-sidebar-wrapper">
            <ul class="nav">
                <li><a href="<c:url value="/admin/user/list" />">Список пользователей</a></li>
                <li><a href="<c:url value="/admin/user/add" />">Добавить пользователя</a></li>
                <%--<li><a href="#widget_tasks_list">Tasks List</a></li>--%>
                <%--<li><a href="#widget_real_time_chart">Real Time Chart</a></li>--%>
                <%--<li><a href="#widget_server_activity">Server Activity</a></li>--%>
                <%--<li><a href="#widget_calendar">Calendar</a></li>--%>
                <%--<li><a href="#widget_tabs">Tabs</a></li>--%>
            </ul>
        </div>
    </div>
    <div class="main-content">

        <tiles:insertAttribute  name="content" />

    </div>
    <div class="page-footer">
        © 2013 UnitBean CMS Admin.
    </div>
</div>



<script src='<c:url value="/static/a/c81813dd5f2238060c9ddecda9683907.js" />'></script>

<script src='<c:url value="/static/a/3315666c34de7c122079bfa9bb9bfa9f.js" />'></script>

<!-- @include _footer   -->