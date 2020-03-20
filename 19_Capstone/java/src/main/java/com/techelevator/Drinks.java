package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends VendingMachineItems {
	
	public Drinks() {
		super.quantity = "5";
	}

	public Drinks(String slotLocation, String productName, BigDecimal cost) {
		super(slotLocation, productName, cost, "Drinks");
	}

	public String displayReturnMessage() {
		return "Glug Glug, Yum!";
	}

}
