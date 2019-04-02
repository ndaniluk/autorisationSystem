<%@ page import="static repositories.DbConnection.userDB" %>
<%@ page import="models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Users list</h2>
<br><br>

<table>
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Premium status</th>
    </tr>
    <%

        for (User user : userDB) {
            out.print("<tr>");
                out.print("<td>");
                    out.print(user.getUsername());
                out.print("</td>");
                out.print("<td>");
                    out.print(user.getEmail());
                out.print("</td>");
                out.print("<td>");
                    out.print(user.isPremium());
                out.print("</td>");
            out.print("</tr>");
        }
    %>
</table>
</body>
</html>