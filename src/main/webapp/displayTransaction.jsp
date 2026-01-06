<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.wipro.pos.bean.PosBean"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String message=(String)request.getAttribute("message");
PosBean bean=(PosBean)request.getAttribute("bean");%>

<h1>The details are:</h1>
<%if(bean!=null){ %>
<h3>ID:<%= bean.getTransId()%></h3>
<h3>NAME:<%= bean.getCustomerName()%></h3>
<h3>ITEM:<%= bean.getItemName()%></h3>
<h3>DATE:<%= bean.getTransDate()%></h3>
<h3>QUANTITY:<%= bean.getQuantity()%></h3>
<h3>PRICE:<%= bean.getPrice() %></h3>
<h3>AMOUNT:<%= bean.getTotalAmount()%></h3>
<h3>REMARKS:<%= bean.getRemarks()%></h3><%}else{ %>
<h3><%= message %></h3><%} %>

</body>
</html>