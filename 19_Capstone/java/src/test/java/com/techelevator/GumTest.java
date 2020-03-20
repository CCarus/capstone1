package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class GumTest {
	BigDecimal temp = new BigDecimal(3.05);
	@Test
	public void test_display_return_message_returns_chew_chew_yum() {
		VendingMachineItems gum = new Gum();
		assertEquals("Chew Chew, Yum!", gum.displayReturnMessage());
	}
	@Test
	public void test_display_return_message_does_not_return_munch_munch_yum() {
		VendingMachineItems gum = new Gum();
		assertNotEquals("Munch Munch, Yum!", gum.displayReturnMessage());
	}
	@Test
	public void test_get_type_returns_type_of_item() {
		Gum testGum = new Gum("A1", "Potato Crisps", temp);
		String expected = "Gum";
		assertEquals(expected, testGum.getType());
	}
	
}
