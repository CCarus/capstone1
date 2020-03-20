package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.techelevator.view.Menu;

import junit.framework.Assert;

public class VendingMachineItemsTest { 
	VendingMachineItems testItem = new Gum();
	BigDecimal temp = new BigDecimal(3.05);
	Menu menuObject = new Menu(System.in, System.out);
	
	@Test
	public void test_get_quantity_returns_quantity() {
		String expected = "5";
		assertEquals(expected, testItem.getQuantity());
		
	}
	
	@Test
	public void test_subtract_quantity_returns_quantity_string() {
	String four = "4";
	testItem.subtractQuantity();
	assertEquals(four, testItem.getQuantity());
	}
	@Test
	public void test_set_quantity_soldout_sets_quantity_to_sold_out() {
		String expected = "SOLD OUT";
		testItem.setQuantitySoldOut();
		assertEquals(expected, testItem.getQuantity());
	}
	@Test
	public void test_get_cost_returns_cost_of_item() {
		Chips testChips = new Chips("A1", "Potato Crisps", temp);
		BigDecimal expected = temp;
		assertEquals(expected, testChips.getCost());
	}
	@Test
	public void test_get_type_returns_type_of_item() {
		Chips testChips = new Chips("A1", "Potato Crisps", temp);
		String expected = "Chips";
		assertEquals(expected, testChips.getType());
	}
	@Test
	public void test_get_slot_location_returns_slot_location() {
		Chips testChips = new Chips("A1", "Potato Crisps", temp);
		String expected = "A1";
		assertEquals(expected, testChips.getSlotLocation());
	}
	@Test
	public void test_get_product_name_returns_product_name() {
		Chips testChips = new Chips("A1", "Potato Crisps", temp);
		String expected = "Potato Crisps";
		assertEquals(expected, testChips.getProductName());
	}
	

	
	
}
