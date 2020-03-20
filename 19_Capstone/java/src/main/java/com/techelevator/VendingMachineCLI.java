package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {
 
	private Menu menu;
	private File vendingMachineItemsFile;
	private BigDecimal total = new BigDecimal(0.00);
	private String[] fullLine = null;
	private String purchaseID = null;
	private int lineCounter = 0; 

	private List<VendingMachineItems> vendingMachineItemsList = new ArrayList<>();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MenuConstants.MAIN_MENU_OPTIONS);

			if (choice.equals(MenuConstants.MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(displayVendingMachineItems());
			} else if (choice.equals(MenuConstants.MAIN_MENU_OPTION_PURCHASE)) {
				runPurchase();
				// do purchase
			} else if (choice.equals(MenuConstants.MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.loadFileName("vendingmachine.csv");
		cli.vendingMachineItemsList = cli.loadArrays();
		cli.run();
	}

	public boolean loadFileName(String fileName) {
		
		vendingMachineItemsFile = new File(fileName);
		if (!fileName.equals("vendingmachine.csv")) {
			System.out.println("Wrong File Name");
			return false;
		}
		if (vendingMachineItemsFile.exists() == false) { // checks for the existence of a file
			System.out.println(fileName + " does not exist");
			System.exit(0); // Ends the program
		} else if (vendingMachineItemsFile.isFile() == false) {
			System.out.println(fileName + " is not a file");
			System.exit(0); // Ends the program
		}
		return true;

	}

	public boolean displayVendingMachineItems() {
		boolean worked = false;
		System.out.println("Slot ID          Name of Product            Price           Type            Quantity Remaining\n");
		System.out.println("==============================================================================================\n");
		for (int x = 0; x < lineCounter; x++) {
			System.out.println(String.format("%4s| %25s %12s %1s %15s %18s\n",
					vendingMachineItemsList.get(x).getSlotLocation().toString(),
					vendingMachineItemsList.get(x).getProductName().toString(), "$",
					vendingMachineItemsList.get(x).getCost().toString(),
					vendingMachineItemsList.get(x).getType().toString(),
					vendingMachineItemsList.get(x).getQuantity().toString()));
		}
		worked = true;
		return worked;
	}

	public List<VendingMachineItems> loadArrays() {
		List<VendingMachineItems> vendingMachineItemsList = new ArrayList<>();
		BufferedReader readFile = null;
		try {
			readFile = new BufferedReader(new FileReader(vendingMachineItemsFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String read;
		lineCounter = 0;
		try {
			while ((read = readFile.readLine()) != null) {
				fullLine = read.split("\\|");
				lineCounter++;
				for (int x = 0; x < fullLine.length; x = x + 4) {
					BigDecimal temp = new BigDecimal(fullLine[x + 2]);
					switch (fullLine[3]) {
					case "Candy":
						vendingMachineItemsList.add(new Candy(fullLine[x], fullLine[x + 1], temp));
						break;
					case "Chip":
						vendingMachineItemsList.add(new Chips(fullLine[x], fullLine[x + 1], temp));
						break;
					case "Drink":
						vendingMachineItemsList.add(new Drinks(fullLine[x], fullLine[x + 1], temp));
						break;
					case "Gum":
						vendingMachineItemsList.add(new Gum(fullLine[x], fullLine[x + 1], temp));
						break;
					}
				}
			}
			return vendingMachineItemsList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void runPurchase() throws IOException {
		boolean isTrue = true;
		Menu purchaseMenu = new Menu(System.in, System.out);
		Scanner input = new Scanner(System.in);
		while (isTrue) {
			System.out.println("Your total balance is: $" + total.toString());
			String purchaseChoice = (String) purchaseMenu.getChoiceFromOptions(MenuConstants.PURCHASE_MENU_OPTIONS);

			if (purchaseChoice.equals(MenuConstants.PURCHASE_MENU_OPTION_FEED_MONEY)) {
				total = runFeedMoney();
			} else if (purchaseChoice.equals(MenuConstants.PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				System.out.println(displayVendingMachineItems());
				System.out.println("Please enter the Slot ID of the item you want to purchase: ");
				purchaseID = input.nextLine();
				buyItem();

			} else if (purchaseChoice.equals(MenuConstants.PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				System.out.println(returnChange(total));
				isTrue = false;
			}
		}
	}


	public BigDecimal runFeedMoney() throws IOException {
		boolean isTrue = true;
		Menu feedMoneyMenu = new Menu(System.in, System.out);
		BigDecimal one = new BigDecimal(1.00);
		BigDecimal two = new BigDecimal(2.00);
		BigDecimal five = new BigDecimal(5.00);
		BigDecimal ten = new BigDecimal(10.00);
		
		while (isTrue) {
			String feedMoneyChoice = (String) feedMoneyMenu.getChoiceFromOptions(MenuConstants.FEED_MONEY_MENU_OPTIONS);

			if (feedMoneyChoice.equals(MenuConstants.FEED_MONEY_MENU_OPTION_1)) {
				BigDecimal totalBefore = total;
				total = total.add(one);
				BigDecimal totalAfter = total;
				if (!writeToLogFile("FEED MONEY:", totalBefore, totalAfter)) {
					System.out.println("Unable to write to file!");
				}
				isTrue = false;

			} else if (feedMoneyChoice.equals(MenuConstants.FEED_MONEY_MENU_OPTION_2)) {
				BigDecimal totalBefore = total;
				total = total.add(two);
				BigDecimal totalAfter = total;
				if (!writeToLogFile("FEED MONEY:", totalBefore, totalAfter)) {
					System.out.println("Unable to write to file!");
				}
				isTrue = false;

			} else if (feedMoneyChoice.equals(MenuConstants.FEED_MONEY_MENU_OPTION_5)) {
				BigDecimal totalBefore = total;
				total = total.add(five);
				BigDecimal totalAfter = total;
				if (!writeToLogFile("FEED MONEY:", totalBefore, totalAfter)) {
					System.out.println("Unable to write to file!");
				}
				isTrue = false;

			} else if (feedMoneyChoice.equals(MenuConstants.FEED_MONEY_MENU_OPTION_10)) {
				BigDecimal totalBefore = total;
				total = total.add(ten);
				BigDecimal totalAfter = total;
				if (!writeToLogFile("FEED MONEY:", totalBefore, totalAfter)) {
					System.out.println("Unable to write to file!");
				}
				isTrue = false;
			}
		}
		return total; 

	}

	public boolean buyItem() throws IOException {
		boolean doesExist = false;
		boolean isSoldOut = false;
		boolean sold = false;
		for (int x = 0; x < lineCounter; x++) {
			if (purchaseID.equalsIgnoreCase(vendingMachineItemsList.get(x).getSlotLocation())) {
				doesExist = true;
				if (!vendingMachineItemsList.get(x).getQuantity().equals("0")
						&& !vendingMachineItemsList.get(x).getQuantity().equals("SOLD OUT")) {

					if (total.compareTo(vendingMachineItemsList.get(x).getCost()) > 0) {
						String writeType = vendingMachineItemsList.get(x).getProductName() + " "
								+ vendingMachineItemsList.get(x).getSlotLocation() + ":";
						BigDecimal totalBefore = total;
						total = total.subtract(vendingMachineItemsList.get(x).getCost());
						BigDecimal totalAfter = total;
						if (!writeToLogFile(writeType, totalBefore, totalAfter)) {
							System.out.println("Unable to write to file!");
						}
						vendingMachineItemsList.get(x).subtractQuantity();

						System.out.println(vendingMachineItemsList.get(x).displayReturnMessage());
						sold = true;
					}
					else {
						System.out.println("You do not have enough money for this item.");
					}
				}
				if (vendingMachineItemsList.get(x).getQuantity().equals("SOLD OUT")) {
					isSoldOut = true;
				}
				if (vendingMachineItemsList.get(x).getQuantity().compareTo("1") < 0) {
					vendingMachineItemsList.get(x).setQuantitySoldOut();
				}
			}
		}
		if (doesExist != true) {
			System.out.println("You did not input a valid SlotID");
		}
		if (isSoldOut == true) {
			System.out.println("Your item is sold out. Please choose another item.");
		}
		return sold;
	}

	public String returnChange(BigDecimal total) throws IOException {
		String output = "";
		total = total.setScale(2, RoundingMode.FLOOR);
		output+= "Your remaining balance is $" + total + ". Dispensing change...";
		double totalDouble = total.doubleValue();
		int centsTotalInt = (int) (totalDouble * 100);
		int quarters = centsTotalInt / 25;
		centsTotalInt %= 25;
		int dimes = centsTotalInt / 10;
		centsTotalInt %= 10;
		int nickels = centsTotalInt / 5;
		centsTotalInt %= 5;
		int pennies = centsTotalInt / 1;
		centsTotalInt %= 1;
		output += "Your change is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, "
				+ pennies + " pennies.";
		BigDecimal totalBefore = this.total;
		this.total = total.subtract(total);
		this.total = total.setScale(2,RoundingMode.CEILING);
		BigDecimal totalAfter = this.total;
		if (!writeToLogFile("GIVE CHANGE:", totalBefore, totalAfter)) {
			System.out.println("Unable to write to file!");
		}
		output += "Your new balance is $" + this.total + ".";
		return output;
	}

	public boolean writeToLogFile(String writeType, BigDecimal totalBefore, BigDecimal totalAfter) {
		boolean written = true;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("Log.txt", true);
		} catch (IOException e) {
			written = false;
		}
		PrintWriter writer = null;
		writer = new PrintWriter(fileWriter);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
		String dateString = dateFormat.format(new Date()).toString();
		writer.println(dateString + " " + writeType + " $" + totalBefore.setScale(2, BigDecimal.ROUND_HALF_UP) + " $"
				+ totalAfter.setScale(2, BigDecimal.ROUND_HALF_UP));
		writer.close();
		return written;
		
	}

}
