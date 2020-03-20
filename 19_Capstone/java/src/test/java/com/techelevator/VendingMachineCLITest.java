package com.techelevator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.techelevator.view.Menu;

public class VendingMachineCLITest {
	
	Menu menuObject = new Menu(System.in, System.out);
	VendingMachineCLI testCLI= new VendingMachineCLI(menuObject);
	List<VendingMachineItems> vendingMachineItemsList = new ArrayList<>();
	
	@Test
	public void test_load_file_name_returns_file_name() {
	String fileName = "vendingmachine.csv";
	boolean expected = true;
	assertEquals(expected,testCLI.loadFileName(fileName));
	String wrongFileName = "vendsMachine.csv";
	assertFalse(testCLI.loadFileName(wrongFileName));
	} 
	
	@Test
	public void test_return_change_returns_correct_amount_of_change() throws IOException {
		
		String expected ="Your remaining balance is $0.55. Dispensing change...Your change is 2 quarters, 0 dimes, 1 nickels, 0 pennies.Your new balance is $0.55.";
		String actual = testCLI.returnChange(new BigDecimal (0.55));
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	@Test
	public void test_load_arrays_loads_correct_array() {
		List<VendingMachineItems> vendingMachineItems = new ArrayList<>();
		testCLI.loadFileName("vendingmachine.csv");
		vendingMachineItems.add(new Chips("A1", "Potato Crisps", new BigDecimal (3.05)));
		vendingMachineItems.add(new Chips("A2", "Stackers", new BigDecimal (1.45)));
		vendingMachineItems.add(new Chips("A3", "Grain Waves", new BigDecimal (2.75)));
		vendingMachineItems.add(new Chips("A4", "Cloud Popcorn", new BigDecimal (3.65)));
		vendingMachineItems.add(new Candy("B1", "Moonpie", new BigDecimal (1.80)));
		vendingMachineItems.add(new Candy("B2", "Cowtales", new BigDecimal (1.50)));
		vendingMachineItems.add(new Candy("B3", "Wonka Bar", new BigDecimal (1.50)));
		vendingMachineItems.add(new Candy("B4", "Crunchie", new BigDecimal (1.75)));
		vendingMachineItems.add(new Drinks("C1", "Cola", new BigDecimal (1.25)));
		vendingMachineItems.add(new Drinks("C2", "Dr. Salt", new BigDecimal (1.50)));
		vendingMachineItems.add(new Drinks("C3", "Mountain Melter", new BigDecimal (1.50)));
		vendingMachineItems.add(new Drinks("C4", "Heavy", new BigDecimal (1.50)));
		vendingMachineItems.add(new Gum("D1", "U-Chews", new BigDecimal (0.85)));
		vendingMachineItems.add(new Gum("D2", "Little League Chew", new BigDecimal (0.95)));
		vendingMachineItems.add(new Gum("D3", "Chiclets", new BigDecimal (0.75)));
		vendingMachineItems.add(new Gum("D4", "Triplemint", new BigDecimal (0.75)));
		List<VendingMachineItems> vendingMachineItemsList = testCLI.loadArrays();
		String expectedString = "";
		String actualString = "";
		for (int x = 0; x < vendingMachineItemsList.size(); x++) {
			expectedString += vendingMachineItemsList.get(x).getSlotLocation()
					+ vendingMachineItemsList.get(x).getProductName();
		}
		for (int x = 0; x < vendingMachineItems.size(); x++) {
			actualString += vendingMachineItems.get(x).getSlotLocation()
					+ vendingMachineItems.get(x).getProductName();
		}
		assertEquals(expectedString, actualString);
		
	}
	@Test
	public void test_write_to_log_file_returns_true() {
		boolean result = testCLI.writeToLogFile("FEED MONEY:", new BigDecimal(1.00), new BigDecimal(2.00));
		assertTrue(result);
	}
	@Test
	public void test_display_vending_machine_items_list_displays_msg() { 
		assertTrue(testCLI.displayVendingMachineItems());
	}
	
	
	
	
	
	
	
	

}
