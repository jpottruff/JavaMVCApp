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
<title>Customer Info</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
									
		<% String header; %>
		<% boolean isModifying = (((Boolean) request.getAttribute("isModifying")).booleanValue()); %>
		
				<%-- changing header based on what the user is doing --%>
				<%-- trying to avoid creation of multiple pages that are more or less the same --%>
		
		<% if (isModifying) {
			header = "Modify Customer";
		} else {
			header = "Add an Account";
		}
		%>
		
		<h1><%= header %></h1>
		
		
		<div class="formGroup">
		<h3>Customer Info:</h3>
				<%-- note: maxlength corresponds to fields defined in the table --%>
 		<form name="customerForm" action="ControllerServlet" method="post">
 			<table>
 				
 				<%--if modifying customer need to display the customerID here --%>
 			 <%if (isModifying) { %>
 			 <tr>
 			   <td>Customer Id: <input type="hidden" name="customerId" value="${customerToModify.customerId}"></td>
 			   <td>${customerToModify.customerId}</td>
 			 </tr>
 			 <%}%>

 			 <tr>
 			   <td>Username: </td>
 			   <td><input type="text" name="username" maxlength="30" value="${customerToModify.username}" required></td>
 			 </tr>
 			 <tr>
 			   <td>Password: </td>
 			   <td><input type="password" name="password" maxlength="30" value="${customerToModify.password}" required></td>
 			 </tr>
 			 <tr>
 			   <td>First Name:</td>
 			   <td><input type="text" name="firstname" maxlength="30" value="${customerToModify.firstname}" required></td>
 			 </tr>
 			 <tr>
 			   <td>Last Name:</td>
 			   <td><input type="text" name="lastname" maxlength="30" value="${customerToModify.lastname}" required></td>
 			 </tr>
 			 <tr>
 			   <td>Address:</td>
 			   <td><input type="text" name="address" placeholder="## Fake St." value="${customerToModify.address}" maxlength="75" required></td>
 			 </tr>
 			 <tr>
 			   <td>City:</td>
 			   <td><input type="text" name="city" maxlength="30" value="${customerToModify.city}" required></td>
 			 </tr>
 			  			 <tr>
 			   <td>Postal Code:</td>
 			   <td><input type="text" name="postalcode" maxlength="6" placeholder="M1M2B2" value="${customerToModify.postalcode}"  required></td>
 			 </tr>
 			</table>
 			<%if (isModifying) { %>
 			<input name="updateCustomer" type="submit" value="Update Customer Profile">
 			</form>
 			<form action="ControllerServlet" method="post">
 				<input type="submit" name="home" value="Home Page" />
 			<%} else { %>
 			<input name="addCustomer" type="submit" value="Add Customer">
 			<%}%>
 			
 		</form>
	</div>
</body>
</html>