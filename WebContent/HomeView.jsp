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
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<style>
	
	form[name=logoutForm] {
		background-color: #28241F;
	}
	
	input.logoutButton {
		background-color: #DFC554;
		color: #28241F;
	}
	
	input[name=startOrder] {
		margin-left: 19.3em;
		
		
	}
	
	.wrapper {
		width: 100%;
		
	}
	.left {
		display: inline-block;
		width: 40%;
		float: left;
		
	}
	
	.right {
		display: inline-block;
		width: 40%;
		margin-left: 5em; 
	}
	
	.left > form, .right > form {
		width: 100%;
		overflow: none;
	}

</style>

</head>
<body>
	
	<h1>Hello, ${loggedInAs.firstname}</h1>
	<h3>Logged in as: <i>${loggedInAs.username}</i></h3>
	<form name="logoutForm" action="ControllerServlet" method="post">
		<input class="logoutButton" type="submit" name="logout" value="Logout" />
	</form>
	
	<h3 class="success">${accountUpdatedMessage}</h3>
	<h3 class="success">${accountDeletedMessage}</h3>
	<h3 class="success">${orderConfirmedMessage}</h3>
	<h3 class="success">${orderDeletedMessage}</h3>
	
	<h4> Please choose an option:</h4>	
	<div class="wrapper">
	<div class="formGroup left">
	<h3>Customer Options:</h3> 
	<%--TODO make add option once logged in --%>
		<form action="ControllerServlet" method="post">
		<h4> <i>Enter a username and choose an option: </i></h4>
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="customerToSearch" required></td>
				<td><input type=submit name="modifyCustomer" value="Modify Customer" /> </td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type=submit name="viewCustomer" value="View/Delete Customer" /></td>
			</tr>
		
		</table>
 		</form>	
 	</div>
 	
 	<div class="formGroup right">
 		<h3>Order Options:</h3>
 		
 		<form name="viewOrderForm" action="ControllerServlet" method="post">
 		<h4> <i>Enter an order number to view an order or add your own: </i></h4>
 			<table>
 				<tr>
 					<td>Order Number:</td>
 					<td><input type="text" name="orderId" maxlength="4" required /></td>
 					<td><input type=submit name="viewOrder" value="View Order" /></td>
 				</tr>
 			</table>
 		</form>
 		
 		<form name="startOrderForm" action="ControllerServlet" method="post">
 		
 			<table>
 				<tr>
 					<td><input type=submit name="startOrder" value="Add an Order" /></td>
 				</tr>
 			</table>	
 		</form>
 	</div>
 	</div>
</body>
</html>