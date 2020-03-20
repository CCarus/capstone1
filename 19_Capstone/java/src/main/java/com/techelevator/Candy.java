package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItems {
	
	public Candy() {
		super.quantity = "5";
	}

	public Candy(String slotLocation, String productName, BigDecimal cost) {
		super(slotLocation, productName, cost, "Candy");
	}

	public String displayReturnMessage() {
		return "Munch Munch, Yum!";
	}
}
