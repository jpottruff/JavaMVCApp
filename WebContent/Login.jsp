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
<title>Login</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
 						<%-- Start at this page --%>
 		
 		<h1> Welcome </h1>
 		
 		<h3 class="success">${accountCreatedMessage}</h3>
 		<h3 class="error">${invalidLoginMessage}</h3>
 		
		
		<div class="formGroup" >
		<h3>Login:</h3>
 		<form name="loginForm" action="ControllerServlet" method="post">
 			<table>
 			 <tr>
 			   <td>Username:</td>
 			   <td><input type="text" name="username" required></td>
 			 </tr>
 			 <tr>
 			   <td>Password: </td>
 			   <td><input type="password" name="password" required></td>
 			 </tr>
 			</table>
 			<input name="login" type="submit" value="Login">
 		</form>
 		<form action="ControllerServlet" method="post">
 				<%--fix this later --%>
 			<input type=submit name="newCustomer"value="New Customer" />
 		</form>
 		</div>
</body>	
</html>