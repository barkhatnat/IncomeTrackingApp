<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>All users</h2><br>
<table>  <tr> <th>Name</th> <th>Password</th><th>Email</th><th>Operations</th></tr>
<c:forEach  var="user" items="${allUsers}">
  <c:url var = "updateButton" value = "/updateInfo">
    <c:param name="userId" value = "${user.id}"/>
  </c:url>
  <c:url var = "deleteButton" value = "/deleteUser">
    <c:param name="userId" value = "${user.id}"/>
  </c:url>
  <tr>
    <td>${user.username}</td>
    <td>${user.password}</td>
    <td>${user.email}</td>
    <td><input type="button" value="Update" onclick = "window.location.href = '${updateButton}'" />
      <input type="button" value="Delete" onclick = "window.location.href = '${deleteButton}'" />
    </td>
  </tr>
</c:forEach>
</table>
<br>
<input type = button value="Add user" onclick = "window.location.href = 'addNewUser'"/>
</body>
</html>
