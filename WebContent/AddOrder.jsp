<%--
Name: Jeff Pottruff (991038577)
Class: PROG-32758
Assignment 3
 --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add an Order</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
		<h1>Add an order</h1>
		<div class="formGroup">
			<h3>Order Details:</h3>
	 		<form name="orderForm"" action="ControllerServlet" method="post">
	 			<table>
	 			 <tr>
	 			   <td>Customer ID: <input type="hidden" name="customerId" value="${loggedInAs.customerId}"></td>
	 			   <td>${loggedInAs.customerId}</td>
	 			 </tr>
	 			 <tr>
	 			   <td>Item Name:</td>
	 			   <td><input type="text" name="itemName" maxlength="30" required /></td>
	 			 </tr>
	 			 <tr>
	 			   <td>Price</td>
	 			   <td><input type="text" name="price" required></td>
	 			 </tr>
	 			 <tr>
	 			   <td>Quantity</td>
	 			   <td><input type="number" name="quantity" maxlength="3" required /></td>
	 			 </tr>
	 			 <tr>
	 			 	<td></td>
	 			 	<%--FIXME use tag library for date --%>
	 			   <td><input type="hidden" name="date" value="<%=(new java.util.Date()).toLocaleString()%>" /></td>
	 			 </tr>
	 			</table>
	 			<input type="submit" name="addOrder" value="Add Order">
 			</form>
 			<form action="ControllerServlet" method="post">
 				<input type="submit" name="home" value="Home Page" />
 			</form>
 		</div>
</body>
</html>