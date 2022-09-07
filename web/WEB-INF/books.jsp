<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books page</title>
</head>
<body>


<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    List<Author> authorList = (List<Author>) request.getAttribute("authors");

%>


<table border="1">
    <tr>
        <td>id</td>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author</th>
        <th>action</th>
    </tr>

    <%
        for (Book book : books) { %>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td>
            <%
                if (book.getAuthor() != null) {
            %>
            <%=book.getAuthor().getId()%>
           <% } else { %>
            <span style="color:#076262;">there is not author</span>
            <% } %>

        </td>
        <td>
            <a href="/books/update?id=<%=book.getId()%>">Update</a>
            <a href="/books/delete?id=<%=book.getId()%>">Delete</a>
        </td>
    </tr>
    <% } %>

</table>
</body>
</html>