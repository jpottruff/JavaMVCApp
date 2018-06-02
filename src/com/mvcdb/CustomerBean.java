/*
Name: Jeff Pottruff (991038577)
Class: PROG-32758
Assignment 3
*/


package com.mvcdb;

import java.io.Serializable;

public class CustomerBean implements Serializable {
	
	private int customerId;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String postalcode;
	
	
	public CustomerBean() { }
	
	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostalcode() {
		return postalcode;
	}


	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}



	

}
