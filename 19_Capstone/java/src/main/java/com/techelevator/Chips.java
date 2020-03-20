package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingMachineItems {
	
	public Chips () {
		super.quantity = "5";
	} 
	
	public Chips(String slotLocation, String productName, BigDecimal cost) {
		super(slotLocation, productName, cost, "Chips");
	}

	public String displayReturnMessage() {
		return "Crunch Crunch, Yum!";
	}





}
