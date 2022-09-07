<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Sus
  Date: 06.09.2022
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Author author = (Author) request.getAttribute("author"); %>
<h2>Please input authors data:</h2>
<form action="/authors/update" method="post">
    <input type="hidden" name="id" value="<%=author.getId()%>"/>
    <input type="text" name="name" value="<%=author.getName()%>"/> <br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"/> <br>
    <input type="text" name="email" value="<%=author.getEmail()%>"/> <br>
    <input type="number" name="age" value="<%=author.getAge()%>"/>
    </select> <br>

    <input type="submit" value="Update">


</form>
</body>
</html>
