<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MainServlet" method="post">
<input type="hidden" name="action" value="viewRecord"><br>
Customer Name:<input type="text" name="customer"><br><br>
Date:<input type="date" name="date"><br><br>
<button type="submit">View</button></form>
</body>
</html>