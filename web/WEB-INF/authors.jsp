<%@ page import="java.util.List" %>

<%@ page import="model.Author" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors page</title>
</head>
<body>

<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>

    </tr>

    <%
        for (Author author : authors) { %>

    <tr>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td>
            <a href="/authors/update?id=<%=author.getId()%>">Update</a>
            <a href="/authors/delete?id=<%=author.getId()%>">Delete</a>
        </td>


    </tr>

    <% } %>


</table>
</body>
</html>
