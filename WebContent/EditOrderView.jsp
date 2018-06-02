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
<title>Modify Order</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

	<% boolean isModifying = (((Boolean) request.getAttribute("isModifying")).booleanValue()); %>

	<h1> Order Information </h1>
	<h3 class="success">${orderUpdatedMessage}</h3>
	<div class="formGroup">
	<h3>Order Details:</h3>
	<% if (isModifying) { %>
		<form name="modifyOrderForm" action="ControllerServlet" method="post">
	<% } %>
		<table> 
		 <tr>
		   <td>Order Id: </td>
		   <td>${orderToView.orderId}</td>
		 </tr>
		 <tr>
		   <td>Customer Id: </td>
		   <td>${orderToView.customerId}</td>
		 </tr>
		 <tr>
		   <td>Item Name: </td>
		   <td> <% if (isModifying) { %>
		   			<input type="text" name="itemName" value="${orderToView.itemName}" required/>
		   		<%} else { %>
		   			${orderToView.itemName}
		   		<%} %>
		   </td>
		 </tr>
		 <tr>
		   <td>Price: </td>
		   <td><% if (isModifying) { %>
		   			<input type="text" name="price" value="${orderToView.price}" required/>
		   		<%} else { %>
		   			${orderToView.price}
		   		<%} %>
		   
		   </td>
		 </tr>
		 <tr>
		   <td>Quantity: </td>
		   <td><% if (isModifying) { %>
		   			<input type="text" name="quantity" value="${orderToView.quantity}" required/>
		   		<%} else { %>
		   			${orderToView.quantity}
		   		<%} %>
		   </td>
		 </tr>
		  <tr>
		   <td>Date Ordered: </td>
		   <td>${orderToView.orderDate}</td>
		 </tr>
		</table>
		
		<%if (isModifying) { %>
			<input type="submit" name="updateOrder" value="Confirm Changes" />
			<input type="submit" name="home" value="Home Page" />
		</form>
		<% } else { %>
		<form name="modifyOrderForm" action="ControllerServlet" method="post">
			<input type="submit" name="editItem" value="Edit Item" />
		</form>
		<form name="deleteOrderForm" action="ControllerServlet" method="post">
			<input type="submit" name="deleteOrder" value="Delete Order" />
			<input type="submit" name="home" value="Home Page" />
 		</form>
 		<% }%>
	</div>
</body>
</html>