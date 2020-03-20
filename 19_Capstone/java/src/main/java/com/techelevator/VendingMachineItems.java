package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItems {
	private BigDecimal cost = new BigDecimal(0);
	private String type;
	private String slotLocation;
	private String productName;
	protected String quantity;
	
	public VendingMachineItems () {
		this.quantity = "5";
	}
	
	public VendingMachineItems(String slotLocation, String productName, BigDecimal cost, String type) {
		this.slotLocation = slotLocation;
		this.productName = productName;
		this.cost = cost;
		this.type = type;
		this.quantity = "5";
		
	}

	public abstract String displayReturnMessage();
	
	public String getQuantity() {
		return quantity;
	}
	
	public String subtractQuantity() {
		Integer quantity1 = Integer.parseInt(quantity);
		quantity = Integer.toString(quantity1 - 1);
		return quantity;
	}

	public void setQuantitySoldOut() {
		String soldOut = "SOLD OUT"; 
		this.quantity= soldOut;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public String getType() {
		return type;
	}

	public String getSlotLocation() {
		return slotLocation;
	}

	public String getProductName() {
		return productName;
	}
	
	
}
