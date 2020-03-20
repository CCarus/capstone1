package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class CandyTest {
	BigDecimal temp = new BigDecimal(3.05);

	@Test
	public void test_display_return_message_returns_munch_munch_yum() {
		VendingMachineItems candy = new Candy();
		assertEquals("Munch Munch, Yum!", candy.displayReturnMessage());
	}
	@Test
	public void test_display_return_message_does_not_return_chew_chew_yum() {
		VendingMachineItems candy = new Candy();
		assertNotEquals("Chew Chew, Yum!", candy.displayReturnMessage());
	}
	@Test
	public void test_get_type_returns_type_of_item() {
		Candy testCandy = new Candy("A1", "Potato Crisps", temp);
		String expected = "Candy";
		assertEquals(expected, testCandy.getType());
		
	}

}
