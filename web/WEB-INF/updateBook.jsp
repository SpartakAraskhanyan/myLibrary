<%@ page import="model.Book" %>
<%@ page import="model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Sus
  Date: 06.09.2022
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% Book book = (Book) request.getAttribute("book"); %>

<%
List<Author> authors = (List<Author>) request.getAttribute("authors");

%>

<h2>Please update books data:</h2>
<form action="/books/update" method="post">
  <input type="hidden" name="id" value="<%=book.getId()%>"/>
  <input type="text" name="title" value="<%=book.getTitle()%>"/> <br>
  <input type="text" name="description" value="<%=book.getDescription()%>"/> <br>
  <input type="number" name="price" value="<%=book.getPrice()%>"/> <br>
  <select name="author_id" >

      <% for (Author author : authors) { %>
      <% if (author.equals(book.getAuthor())) {%>
      <option selected value="<%=author.getId()%>"> <%=author.getName()%> <%=author.getSurname()%> </option>
       <% } else { %>
      <option value="<%=author.getId()%>"> <%=author.getName()%> <%=author.getSurname()%> </option>
      <% } %>
      <% } %>

  </select> <br>


  <input type="submit" value="Update">


</form>

</body>
</html>
