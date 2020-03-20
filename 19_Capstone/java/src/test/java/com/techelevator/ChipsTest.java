package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ChipsTest {

	@Test
	public void test_display_return_message_returns_crunch_crunch_yum() {
		VendingMachineItems chips = new Chips();
		assertEquals("Crunch Crunch, Yum!", chips.displayReturnMessage());
	}
	@Test
	public void test_display_return_message_does_not_return_chew_chew_yum() {
		VendingMachineItems chips = new Chips();
		assertNotEquals("Chew Chew, Yum!", chips.displayReturnMessage());
	}
	BigDecimal temp = new BigDecimal(3.05);
	@Test
	public void test_get_type_returns_type_of_item() {
		Chips testChips = new Chips("A1", "Potato Crisps", temp);
		String expected = "Chips";
		assertEquals(expected, testChips.getType());
	}
	

}
