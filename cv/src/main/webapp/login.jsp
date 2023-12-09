<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <form action="" method="post">
        <div>
            <label for="username">Username: </label>
            <input type="text" name="username">
        </div>
        <div>
            <label for="password">Password: </label>
            <input type="text" name="password">
        </div>
        <% 
        String message = (String) request.getAttribute("message");
        if(message != null) { 
        %>
        <div>
            Error: <%=message%>
        </div>
        <% } %>
        <input type="submit">
    </form>
</body>
</html>