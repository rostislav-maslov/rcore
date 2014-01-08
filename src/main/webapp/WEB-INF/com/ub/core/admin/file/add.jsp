<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
  Created by IntelliJ IDEA.
  User: ramaslov
  Date: 1/8/14
  Time: 12:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Spring MVC file upload example</h2>

<form:form method="POST" commandName="fileUploadView"
           enctype="multipart/form-data">

    <form:errors path="*" cssClass="errorblock" element="div" />

    Please select a file to upload : <input type="file" name="file" />
    <input type="submit" value="upload" />
		<span><form:errors path="file" cssClass="error" />
		</span>

</form:form>
</body>
</html>