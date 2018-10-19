<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>First Table</h1>
<table border="1">
<thead>
<tr><td>Cart Id</td>
<td>Book Id</td>
<td>Book Name</td>
<td>Quantity</td>
<td>Price</td>
</tr>
</thead>

<tbody>
<c:forEach items="${cartList}" var="cart"><tr>
<td>${cart.id}</td>
<td>${cart.bookid}</td>
<td>${cart.bookname}</td>
<td>${cart.quantity}</td>
<td>${cart.price}</td>
</tr>
</c:forEach>
</tbody>
<tfoot>
<!-- <td>Total no of records: 1</td>
 </tfoot>-->
</table>
</body>
</html>