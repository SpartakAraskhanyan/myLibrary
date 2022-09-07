<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>


<h2>Please input authors data:</h2>
<form action="/authors/add" method="post">
    <input type="text" name="name" placeholder="please input name"/> <br>
    <input type="text" name="surname" placeholder="please input surname"/> <br>
    <input type="text" name="email" placeholder="please input email"/> <br>
    <input type="number" name="age" placeholder="please input age"/>
    </select> <br>
    <input type="submit" value="Register">


</form>
</body>
</html>
