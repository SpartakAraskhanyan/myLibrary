<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>

<%

    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>

<h2>Please input books data:</h2>
<form action="/books/add" method="post">
    <input type="text" name="title" placeholder="please input title"/> <br>
    <input type="text" name="description" placeholder="please input description"/> <br>
    <input type="number" name="price" placeholder="please input price"/>
     <p>please input author</p>
    <select name="author_id">

        <ul>
            <% for (Author author : authors) { %>
            <option value="<%=author.getId()%>"> <%=author.getName()%> <%=author.getSurname()%> </option>
                    <% } %>
        </ul>


    </select> <br>


    <input type="submit" value="Register">


</form>

</body>
</html>
