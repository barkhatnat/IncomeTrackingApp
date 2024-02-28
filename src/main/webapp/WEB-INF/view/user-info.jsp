<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mvideo
  Date: 26.02.2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Use info</h2>
<form:form action="saveUser" modelAttribute = "user">
    <form:hidden path="id"/>
    Username <form:input path="username"/>
    <br>
    Password <form:input path="password"/>
    <br>
    Email <form:input path="email"/>
    <br>
    <input type="submit" value = "OK">

</form:form>
</body>
</html>
