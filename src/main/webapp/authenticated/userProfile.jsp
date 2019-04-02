<%@ page import="models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>User profile:
    <%
        User user = (User) request.getSession().getAttribute("userObject");
        out.print(user.getUsername());
    %>
</h2>
<br><br>

<p>This is your profile page</p>
<br><br>

<a href="usersList.jsp">User list</a>
<a href="premiumZone.jsp">Only for Premium</a>
<%
    if (user.isAdmin())
        out.print("<a href='grantPremium.jsp'>Grant premium</a>");
%>
</body>
</html>