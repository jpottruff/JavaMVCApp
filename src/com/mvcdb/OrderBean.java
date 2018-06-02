/*
Name: Jeff Pottruff (991038577)
Class: PROG-32758
Assignment 3
*/


package com.mvcdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OrderBean implements Serializable {
	
	private int orderId;
	private int customerId;
	private String itemName;
	private double price;
	private int quantity;
	private Date orderDate;
	
	public OrderBean() { }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	

}
