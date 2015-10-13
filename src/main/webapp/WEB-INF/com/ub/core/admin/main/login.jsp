<%@ page import="com.ub.core.file.FileRoutes" %>
<%@ page import="com.ub.core.user.routes.UserLoginRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<s:htmlEscape defaultHtmlEscape="true"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Neon Admin Panel"/>
    <meta name="author" content=""/>

    <title>Neon | Login</title>

    <link rel="stylesheet" href="<c:url value="/static/a/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/a/css/font-icons/entypo/css/entypo.css"/>">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
    <link rel="stylesheet" href="<c:url value="/static/a/css/neon.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/a/css/custom.css"/>">

    <script src="<c:url value="/static/a/js/jquery-1.10.2.min.js"/>"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body class="page-body login-page login-form-fall" data-url="http://neon.dev">

<div class="login-container">

    <div class="login-header login-caret">

        <div class="login-content">

            <a href="/admin" class="logo">
                <img src="<c:url value="/static/a/images/logo.png"/>" width="95" alt=""/>
            </a>

            <%--<p class="description">Dear user, log in to access the admin area!</p>--%>

            <!-- progress bar indicator -->
            <%--<div class="login-progressbar-indicator">--%>
            <%--<h3>43%</h3>--%>
            <%--<span>logging in...</span>--%>
            <%--</div>--%>
        </div>

    </div>

    <%--<div class="login-progressbar">--%>
    <%--<div></div>--%>
    <%--</div>--%>

    <%--<div class="login-form">--%>

    <div class="login-content">

        <form method="post" role="form" action="<%= UserLoginRoutes.LOGIN%>" id="form_login">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    Your login attempt was not successful, try again.<br/>
                </div>
            </c:if>
            <div class="form-group">

                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="entypo-user"></i>
                    </div>

                    <input type="text" class="form-control" name='email' id="username" placeholder="Email"
                           autocomplete="off"/>
                </div>

            </div>

            <div class="form-group">

                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="entypo-key"></i>
                    </div>

                    <input type="password" class="form-control" name='password' id="password" placeholder="Password"
                           autocomplete="off"/>
                </div>

            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block btn-login">
                    Login In
                    <i class="entypo-login"></i>
                </button>
            </div>

        </form>


    </div>

    <%--</div>--%>

</div>


</body>
</html>