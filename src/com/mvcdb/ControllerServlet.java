/*
Name: Jeff Pottruff (991038577)
Class: PROG-32758
Assignment 3
*/



package com.mvcdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	ResultSet rs = null;
	PreparedStatement pst;
	String insertQuery = "";
	
	RequestDispatcher rd; 
	
	String username;
	String password;
	String firstname;
	String lastname;
	String address;
	String city;
	String postalcode;
	
	

    public ControllerServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//start session (is this necessary/good/bad practice?)
		HttpSession session = request.getSession(); 
		
				/** Checks for what option is selected **/
		Boolean home = (request.getParameter("home") != null);
		
		Boolean isNewCustomer = (request.getParameter("newCustomer") != null);
		Boolean addCustomer = (request.getParameter("addCustomer") != null);
		
		Boolean login = (request.getParameter("login") != null);
		Boolean logout = (request.getParameter("logout") != null);
		Boolean modifyCustomer = (request.getParameter("modifyCustomer") != null);
		Boolean updateCustomer = (request.getParameter("updateCustomer") != null);
		Boolean viewCustomer = (request.getParameter("viewCustomer") != null);
		Boolean deleteCustomer = (request.getParameter("deleteCustomer") != null);
		
		Boolean startOrder = (request.getParameter("startOrder") != null);
		Boolean addOrder = (request.getParameter("addOrder") != null);
		Boolean viewOrder = (request.getParameter("viewOrder") != null);
		Boolean editItem = (request.getParameter("editItem") != null);
		Boolean updateOrder = (request.getParameter("updateOrder") != null);
		Boolean deleteOrder = (request.getParameter("deleteOrder") != null);
		
		if (home) {
			
			getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);
		}
				
		if (isNewCustomer) {
			//Redirects to Add Customer if the person clicks the "New Customer" button
			request.setAttribute("isModifying", false); //helps avoid server error - better way to do?
			getServletContext().getRequestDispatcher("/AddCustomerView.jsp").forward(request, response);
			
		}
		
		if (addCustomer) {
			
				//Creating a Bean and setting values to values from AddCustomerView.jsp
				CustomerBean newCustomer = new CustomerBean();
				
				newCustomer.setUsername(request.getParameter("username"));
				newCustomer.setPassword(request.getParameter("password"));
				newCustomer.setFirstname(request.getParameter("firstname"));
				newCustomer.setLastname(request.getParameter("lastname"));
				newCustomer.setAddress(request.getParameter("address"));
				newCustomer.setCity(request.getParameter("city"));
				newCustomer.setPostalcode(request.getParameter("postalcode"));
				
				createAccount(newCustomer);
				
				//Redirect to Confirmation Message
				//put in request instead of session - only needs to exist for one page? (confirm this)
				
				//TODO handle error handling if user exists - still displays confirmation message if account not created
				request.setAttribute("accountCreatedMessage", "Account Created! Please Login.");
				
				getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);

		}
		
		if (login) {
		
			String passwordEntered;
			String usernameEntered;
			
			usernameEntered = request.getParameter("username");
			passwordEntered = request.getParameter("password");
			
					/*Password validation - check in Database*/
			CustomerBean customerProfile = lookupCustomer(usernameEntered);

			if (passwordEntered.equals(customerProfile.getPassword())) {
				
				//set logged in as to the corresponding profile and redirect to HomeView
				//NOTE:changed to session.setAttribute from request.setAttribute - will be logged in as this person until logout
				session.setAttribute("loggedInAs", customerProfile);
				getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);
			} else {
				//Redirects to Login page with fields cleared
				//NOTE: request in stead of session - only needs to exist for redirect
				request.setAttribute("invalidLoginMessage", "Failed to Login, please retry");
				
				getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
			}
	
		}
		
		if (logout) {
			
			//is this the correct way to erase the session?
			if (session != null) {
				session.invalidate();
			}
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
		if (modifyCustomer) {

			username = request.getParameter("customerToSearch");
			
			//creating a bean based on customer 
			//TODO/FIXME add not a user if not exists
			CustomerBean retrievedProfile = lookupCustomer(username);
			
			//value altered based on this in AddCustomer View
			//NOTE: request - only needs to exist for one page
			request.setAttribute("isModifying", true);
			request.setAttribute("customerToModify", retrievedProfile);
			getServletContext().getRequestDispatcher("/AddCustomerView.jsp").forward(request, response);
		}
		
		if (updateCustomer) {
			
			
			//Get Values from AddCustomerView.jsp (values might be changed from previously created bean (ie. retrievedProfile)
			CustomerBean updatedProfile = new CustomerBean();
			
			updatedProfile.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			updatedProfile.setUsername(request.getParameter("username"));
			updatedProfile.setPassword(request.getParameter("password"));
			updatedProfile.setFirstname(request.getParameter("firstname"));
			updatedProfile.setLastname(request.getParameter("lastname"));
			updatedProfile.setAddress(request.getParameter("address"));
			updatedProfile.setCity(request.getParameter("city"));
			updatedProfile.setPostalcode(request.getParameter("postalcode"));
			
			updateCustomerInfo(updatedProfile);
			
			//NOTE: request - only needs to exist for one page
			request.setAttribute("accountUpdatedMessage", "Update Successful!");
			
			getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);
			
		}
		
		if (viewCustomer) {
			
			username = request.getParameter("customerToSearch");
			
			//creating a bean based on customer 
			//TODO/FIXME add not a user if not exists
			CustomerBean retrievedProfile = lookupCustomer(username);
			
			//value altered based on this in AddCustomer View
			session.setAttribute("customerToView", retrievedProfile);
			getServletContext().getRequestDispatcher("/ViewDeleteCustomerView.jsp").forward(request, response);
		}
		
		if (deleteCustomer) {
			
			deleteCustomer((CustomerBean)session.getAttribute("customerToView"));
			//should the customerToView attribute now be set to null/removed/etc?
			request.setAttribute("accountDeletedMessage", "Account has been removed.");
			getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);
			
		}
		
		if (startOrder) {
			//takes user to add order page
			getServletContext().getRequestDispatcher("/AddOrder.jsp").forward(request, response);
		}
		
		if (addOrder) {
			
			//Creating a Bean and setting values to values from AddCustomerView.jsp
			OrderBean newOrder= new OrderBean();
			

			
			newOrder.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			newOrder.setItemName(request.getParameter("itemName"));
			newOrder.setPrice(Double.parseDouble(request.getParameter("price")));
			newOrder.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			//TODO set DATE - modify method too, use TAG LIBRARY

			
			addOrder(newOrder);
			
			//Redirect to Confirmation Message
			//put in request instead of session - only needs to exist for one page? (confirm this)
			
			//TODO handle error handling if order not created
			request.setAttribute("orderConfirmedMessage", "Order Confirmed!");
			getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);

		}
		
		if (viewOrder) {
			
			int orderId;
			
			orderId = Integer.parseInt(request.getParameter("orderId"));
			
			//TODO add error handling if order does not exist
			OrderBean retrievedOrder = lookupOrder(orderId);
			
			
			request.setAttribute("isModifying", false); //helps to avoid server error, better way to do?
			session.setAttribute("orderToView", retrievedOrder);
			getServletContext().getRequestDispatcher("/EditOrderView.jsp").forward(request, response);
		}
		
		if (editItem) {
			
			request.setAttribute("isModifying", true); 
			getServletContext().getRequestDispatcher("/EditOrderView.jsp").forward(request, response);
		}
		
		if (updateOrder) {
			
			
			//Get Values from EditOrderView.jsp (values might be changed from previously created bean (ie. retrievedOrder)
			//Note: only item name, price and quantity can be updated, retaining most of the old order's values
			OrderBean updatedOrder = (OrderBean)session.getAttribute("orderToView");
			
			System.out.println(request.getParameter("itemName"));
			System.out.println(request.getParameter("price"));
			System.out.println(request.getParameter("quantity"));
			
			//set updated values
			updatedOrder.setItemName(request.getParameter("itemName"));
			updatedOrder.setPrice(Double.parseDouble(request.getParameter("price")));
			updatedOrder.setQuantity(Integer.parseInt(request.getParameter("quantity")));

			//updating order info in the database
			updateOrderInfo(updatedOrder);
			
			//looking up values in database so record is an accurate reflection of what is stored
			updatedOrder = lookupOrder(updatedOrder.getOrderId());
			session.setAttribute("orderToView", updatedOrder);
			
			//NOTE: request - only needs to exist for one page
			request.setAttribute("isModifying", false); 
			request.setAttribute("orderUpdatedMessage", "Items Changed!");
			getServletContext().getRequestDispatcher("/EditOrderView.jsp").forward(request, response);
			
			
		}
		
		if (deleteOrder) {
			deleteOrder((OrderBean)session.getAttribute("orderToView"));
			
			//should the customerToView attribute now be set to null/removed/etc?
			request.setAttribute("orderDeletedMessage", "Order has been deleted.");
			getServletContext().getRequestDispatcher("/HomeView.jsp").forward(request, response);
		}
		
	}
			
			
		

	
	
	
	
	// *** Methods *** //
	
	//Connection Methods
	
	private Connection getConnection() {
		final String url = "jdbc:mysql://localhost:3306/mvcdb";
		final String DriverClass = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "macandcheese";
		
		Connection connection = null; 
		
		try {
			Class.forName(DriverClass);
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;

	}
	
	public void closeConnection(Connection conn, Statement st, ResultSet rs) {
		
		try {
			
			if (conn != null) 
				conn.close();
			if (st != null) 
				st.close();
			if (rs != null)
				rs.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	//Customer Methods
	
	public void createAccount(CustomerBean newCustomer) {
		
		try {
			
			//Get Connection
			con = getConnection();
			
			//Add Query
			insertQuery = "INSERT INTO customers (username, password, firstname, lastname, address, city, postalcode) VALUES (?,?,?,?,?,?,?)";
			pst = con.prepareStatement(insertQuery);
			
			pst.setString(1, newCustomer.getUsername());
			pst.setString(2, newCustomer.getPassword());
			pst.setString(3, newCustomer.getFirstname());
			pst.setString(4, newCustomer.getLastname());
			pst.setString(5, newCustomer.getAddress());
			pst.setString(6, newCustomer.getCity());
			pst.setString(7, newCustomer.getPostalcode());
			pst.executeUpdate();
			
			//Close Connection
			closeConnection(con, pst, rs);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateCustomerInfo(CustomerBean updatedProfile) {
		
		try {
			
			//Get Connection
			con = getConnection();
			
			//Updating Profile with values from incoming bean
			insertQuery = "UPDATE customers SET username=?, password=?, firstname=?, lastname=?, address=?, city=?, postalcode=? WHERE customerId=?;";
			pst = con.prepareStatement(insertQuery);
	
			pst.setString(1, updatedProfile.getUsername());
			pst.setString(2, updatedProfile.getPassword());
			pst.setString(3, updatedProfile.getFirstname());
			pst.setString(4, updatedProfile.getLastname());
			pst.setString(5, updatedProfile.getAddress());
			pst.setString(6, updatedProfile.getCity());
			pst.setString(7, updatedProfile.getPostalcode());
			pst.setInt(8, updatedProfile.getCustomerId());
			
			pst.executeUpdate();
			
			//Close Connection
			closeConnection(con, pst, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	public CustomerBean lookupCustomer(String username) {
		CustomerBean customer = new CustomerBean();
		
		try {

			//Get Connection
			con = getConnection();
			
			//Query
			insertQuery = "SELECT * FROM customers WHERE username=?;";
			pst = con.prepareStatement(insertQuery);
			
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				customer.setCustomerId(rs.getInt(1));
				customer.setUsername(rs.getString(2));
				customer.setPassword(rs.getString(3));
				customer.setFirstname(rs.getString(4));
				customer.setLastname(rs.getString(5));
				customer.setAddress(rs.getString(6));
				customer.setCity(rs.getString(7));
				customer.setPostalcode(rs.getString(8));
			}
	
			//Close Connection
			closeConnection(con, pst, rs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return customer;
	}
	
	public void deleteCustomer(CustomerBean customerToDelete) {
			try {
				
				//Get Connection
				con = getConnection();
				
				//Query
				insertQuery = "DELETE FROM customers WHERE customerId=?;";
				pst = con.prepareStatement(insertQuery);
				
				pst.setInt(1, customerToDelete.getCustomerId());
				pst.executeUpdate();
				
				//Close Connection
				closeConnection(con, pst, rs);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	//Order Methods
	
	public void addOrder(OrderBean newOrder) {
		
		try {
			//Get Connection
			con = getConnection();
			
			//Add Query
			insertQuery = "INSERT INTO orders (customerId, itemName, price, quantity) VALUES (?,?,?,?)";
			pst = con.prepareStatement(insertQuery);
			
			pst.setInt(1, newOrder.getCustomerId());
			pst.setString(2, newOrder.getItemName());
			pst.setDouble(3, newOrder.getPrice());
			pst.setInt(4, newOrder.getQuantity());
			pst.executeUpdate();
			
			//Close Connection
			closeConnection(con, pst, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public OrderBean lookupOrder(int orderId) {
		OrderBean order = new OrderBean();
				
			try {
	
				//Get Connection
				con = getConnection();
				
				//Query
				insertQuery = "SELECT * FROM orders WHERE orderId=?;";
				pst = con.prepareStatement(insertQuery);
				
				pst.setInt(1, orderId);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					order.setOrderId(rs.getInt(1));
					order.setCustomerId(rs.getInt(2));
					order.setItemName(rs.getString(3));
					order.setPrice(rs.getDouble(4));
					order.setQuantity(rs.getInt(5));
					order.setOrderDate(rs.getDate(6));
				}
		
				//Close Connection
				closeConnection(con, pst, rs);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
				
				return order;
	}
	
	public void updateOrderInfo(OrderBean updatedOrder) {
		try {
			
			//Get Connection
			con = getConnection();
			
			//Updating Order with values from incoming bean - only need to update the 3 (possibly) changed fields
			insertQuery = "UPDATE orders SET itemName=?, price=?, quantity=? WHERE orderId=?;";
			pst = con.prepareStatement(insertQuery);
			
			pst.setString(1, updatedOrder.getItemName());
			pst.setDouble(2, updatedOrder.getPrice());
			pst.setInt(3, updatedOrder.getQuantity());
			pst.setInt(4, updatedOrder.getOrderId());
			
			pst.executeUpdate();
			
			//Close Connection
			closeConnection(con, pst, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOrder(OrderBean orderToDelete) {
		try {
			//Get Connection
			con = getConnection();
			
			//Query
			insertQuery = "DELETE FROM orders WHERE orderId=?;";
			pst = con.prepareStatement(insertQuery);
			
			pst.setInt(1, orderToDelete.getOrderId());
			pst.executeUpdate();
			
			//Close Connection
			closeConnection(con, pst, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	



}
