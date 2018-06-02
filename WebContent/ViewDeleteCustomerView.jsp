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
<title>View/Delete Customer</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<h1>Info for ${customerToView.username} <i>(Id: ${customerToView.customerId})</i></h1>
	<div class="formGroup">
		<h3>Customer Info:</h3>
	 		<input type="hidden" name="customerId" value="${customerToModify.customerId}">
	 		
 			<table> 
 			 <tr>
 			   <td>Customer Id: </td>
 			   <td>${customerToView.customerId}</td>
 			 </tr>
 			 <tr>
 			   <td>Username: </td>
 			   <td>${customerToView.username}</td>
 			 </tr>
 			 <tr>
 			   <td>Password: </td>
 			   <td>${customerToView.password}</td>
 			 </tr>
 			 <tr>
 			   <td>First Name:</td>
 			   <td>${customerToView.firstname}</td>
 			 </tr>
 			 <tr>
 			   <td>Last Name:</td>
 			   <td>${customerToView.lastname}</td>
 			 </tr>
 			 <tr>
 			   <td>Address:</td>
 			   <td>${customerToView.address}</td>
 			 </tr>
 			 <tr>
 			   <td>City:</td>
 			   <td>${customerToView.city}</td>
 			 </tr>
 			 <tr>
 			   <td>Postal Code:</td>
 			   <td>${customerToView.postalcode}</td>
 			 </tr>
 			</table>
 		<form name="customerInfo" action="ControllerServlet" method="post">
			<input type="submit" name="deleteCustomer" value="Delete Customer" />
			<input type="submit" name="home" value="Home Page" />
 		</form>
	</div>	
		

</body>
</html>