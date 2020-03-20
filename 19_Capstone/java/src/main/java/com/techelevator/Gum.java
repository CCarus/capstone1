package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItems {
	public Gum() {
		super.quantity = "5";
	}	

	public Gum(String slotLocation, String productName, BigDecimal cost) {
		super(slotLocation, productName, cost, "Gum");
	}

public String displayReturnMessage() {
	return "Chew Chew, Yum!";
	
}
}
