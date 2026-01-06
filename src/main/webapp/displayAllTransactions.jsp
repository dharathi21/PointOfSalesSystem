<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.wipro.pos.bean.PosBean,java.util.List,java.util.ArrayList"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String message=(String)request.getAttribute("message");
List<PosBean> list=ArrayList<(PosBean)>request.getAttribute("bean");%>

<h1>The details are:</h1>
<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Item</th>
<th>Date</th>
<th>Quantity</th>
<th>Price</th>
<th>Amount</th>
<th>Remarks</th>
</tr>
<%for(PosBean r:list){ %>
<tr>
<td><%=r.getTransId() %></td>
<td><%=r.getCustomerName() %></td>
<td><%=r.getItemName() %></td>
<td><%=r.getTransDate() %></td>
<td><%= r.getQuantity() %></td>
<td><%= r.getPrice() %></td>
<td><%= r.getTotalAmount() %></td>
<td><%= r.getRemarks() %></td>
</tr>
<%}else{ %>
<h3><%= message %></h3><%} %></table>
</body>
</html>