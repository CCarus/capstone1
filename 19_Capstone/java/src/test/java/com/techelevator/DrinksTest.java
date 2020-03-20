package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class DrinksTest {
	BigDecimal temp = new BigDecimal(3.05);
	@Test
	public void test_display_return_message_returns_glug_glug_yum() {
		VendingMachineItems drinks = new Drinks();
		assertEquals("Glug Glug, Yum!", drinks.displayReturnMessage());
	}
	@Test
	public void test_display_return_message_does_not_return_chew_chew_yum() {
		VendingMachineItems drinks = new Drinks();
		assertNotEquals("Chew Chew, Yum!", drinks.displayReturnMessage());
	}
	@Test
	public void test_get_type_returns_type_of_item() {
		Drinks testDrinks = new Drinks("A1", "Potato Crisps", temp);
		String expected = "Drinks";
		assertEquals(expected, testDrinks.getType());
	}

}
