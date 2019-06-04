package controller;

import java.util.Scanner;

import controller.SupermarketWarehouseStaffController.STATE;
import model.SupermarketSystem;
import view.SupermarketManagerView;
import view.SupermarketView;
import warehouse.Product;
import warehouse.Supplier;

public class SupermarketManagerController extends SupermarketController {

	public static enum STATE {
		START, MOSTSOLD, SUPPLIER, PRICECHANGE, BULKDISCOUNT, STOCK
	};

	public final static String mostsold = "1";
	public final static String supplier = "2";
	public final static String pricechange = "3";
	public final static String bulkdiscount = "4";
	public final static String stock = "5";

	private STATE state;
	private SupermarketManagerView view;

	public SupermarketManagerController(SupermarketSystem supermarket, SupermarketView view, String id) {
		super(supermarket, view);
		this.view = (SupermarketManagerView)view;
		state = STATE.START;
	}

	public void setStateStart() {
		state = STATE.START;
	}
	
	public STATE getState() {
		return state;
	}

	// getUserInput() calls this function
	public boolean processInput(String userinput) {
		boolean validInput = false;

		// logout
		if (0 == userinput.toLowerCase().compareTo(logout)) {
			logout();
			return true;
		}

		// continue process input
		switch (state) {
		case START:
			validInput = processStart(userinput);
			break;
		case MOSTSOLD:
			validInput = processMostSold(userinput);
			break;
		case SUPPLIER:
			validInput = processSupplier(userinput);
			break;
		case PRICECHANGE:
			validInput = processPriceChange(userinput);
			break;
		case BULKDISCOUNT:
			validInput = processBulkDiscount(userinput);
			break;
		case STOCK:
			validInput = processProductStock(userinput);
			break;
		}

		return validInput;
	}

	private boolean processProductStock(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			System.out.printf("Product %s : Stock level: %s, Replenish Level: %s, Automatic restock quantity: %s \n",
					supermarket.getProduct(userinput).getName(), supermarket.getStockLevel(userinput),
					supermarket.getProduct(userinput).getReplenishLevel(),
					supermarket.getProduct(userinput).getRestockQty());

			System.out.println("\tRestock product         :      1");
			System.out.println("\tSet Replenish Level     :      2");
			System.out.println("\tSet restock quantity    :      3");
			System.out.println("\tGo back to menu         :      4");

			Scanner stockScanner = new Scanner(System.in);
			String input = stockScanner.nextLine();
			switch (input) {
			case "1":
				System.out.println("Enter the amount you want to restock: ");
				String stockqty = stockScanner.nextLine();
				try {
					int stockQty = Integer.parseInt(stockqty);
					supermarket.restock(userinput, stockQty);
					validInput = true;
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "2":
				System.out.println("Enter the replenish level: ");
				String stockrep = stockScanner.nextLine();
				try {
					int stockRep = Integer.parseInt(stockrep);
					supermarket.getProduct(userinput).setReplenishLevel(stockRep);
					validInput = true;
					
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "3":
				System.out.println("Enter the automatic restock level: ");
				String stockrestock = stockScanner.nextLine();
				try {
					int stockRestock = Integer.parseInt(stockrestock);
					supermarket.getProduct(userinput).setRestockQty(stockRestock);
					validInput = true;
					
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "4":
				state = STATE.START;
				validInput = true;
				break;

			}

		} else {
			System.out.println("Product not found!");
		}
		return validInput;
	}

	private boolean processBulkDiscount(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			System.out.println("Current bulk discount on product " + supermarket.getProduct(userinput).getName());
			if (!supermarket.hasBulkDiscount(userinput)) {
				System.out.println("None");
			} else {
				supermarket.getBulkDiscounts(userinput)
						.forEach((k, v) -> System.out.println("Quantity: " + k + ", Discount percentage: " + v*100 + "%"));
			}

			System.out.println("\tAdd/replace bulk sale   :      1");
			System.out.println("\tRemove bulk sale        :      2");
			System.out.println("\tGo back to menu         :      3");

			Scanner bulkScanner = new Scanner(System.in);
			String input = bulkScanner.nextLine();
			switch (input) {
			case "1":
				System.out.println("Enter the quantity for the bulk sale you want to add: ");
				String bulkqtyadd = bulkScanner.nextLine();
				System.out.println("Enter the percentage for the bulk sale you want to add: ");
				String bulkperadd = bulkScanner.nextLine();
				try {
					int bulkQtyAdd = Integer.parseInt(bulkqtyadd);
					double bulkPerAdd = Double.parseDouble(bulkperadd) / 100;
					supermarket.addBulkDiscount(userinput, bulkQtyAdd, bulkPerAdd);
					validInput = true;

				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "2":
				System.out.println("Enter the quantity for the bulk sale you want to remove(0 to remove all: ");
				String bulkqtyremove = bulkScanner.nextLine();
				try {
					int bulkQtyRemove = Integer.parseInt(bulkqtyremove);
					if(bulkQtyRemove == 0 ) {
						supermarket.removeAllBulkDiscount(userinput);
					}
					else if (supermarket.getBulkDiscounts(userinput).containsKey(bulkQtyRemove)) {
						supermarket.removeBulkDiscount(userinput, bulkQtyRemove);

						validInput = true;
					} else {
						System.out.println("Incorrect value!");
					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "3":
				state = STATE.START;
				validInput = true;
				break;
			}
		} else {
			System.out.println("Product not found!");
		}
		return validInput;
	}

	private boolean processPriceChange(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			System.out.printf("Product %s price: $ %.2f \n", supermarket.getProduct(userinput).getName(),
					supermarket.getProduct(userinput).getPrice());
			if(supermarket.hasPromotionDiscount(userinput)) {
				System.out.printf("Product current discounted price: $ %.2f \n", supermarket.getPromotionalDiscount(userinput));
			}
			System.out.println("\tChange product price    :      1");
			System.out.println("\tSet promotional discount:      2");
			System.out.println("\tGo back to menu         :      3");
			@SuppressWarnings("resource")
			Scanner priceScanner = new Scanner(System.in);
			String input = priceScanner.nextLine();
			switch (input) {
			case "1":
				System.out.println("Enter new price: ");
				String priceInput = priceScanner.nextLine();

				try {
					double price = Double.parseDouble(priceInput);
					supermarket.getProduct(userinput).setPrice(price);
					state = STATE.START;
					validInput = true;

				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
				break;
			case "2":
				System.out.println("Enter discounted price (O to remove promotion): ");
				String discountinput = priceScanner.nextLine();
				try {
					double discount = Double.parseDouble(discountinput);
					if(discount == 0) {
						supermarket.removePromotionDiscount(userinput);
						state = STATE.START;
						validInput = true;
					}
					else if (discount > 0 && discount < supermarket.getProduct(userinput).getPrice()) {
						supermarket.addPromotionDiscount(userinput, discount);
						state = STATE.START;
						validInput = true;
					}
					else {
						System.out.println("Invalid input");
					}
					
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			case "3":
				state = STATE.START;
				validInput = true;
				break;
			}

		} else {
			System.out.println("Product not found!");
		}
		return validInput;
	}

	private boolean processSupplier(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			if (supermarket.getSuppliers().containsKey(userinput)) {
				System.out.println("Supplier ID: " + supermarket.getSuppliers().get(userinput).getId());
				System.out.println("Supplier Name: " + supermarket.getSuppliers().get(userinput).getName());

			} else {
				System.out.println("This item doesn't have supplier details");
			}

			System.out.println("\tUpdate supplier detail  :      1");
			System.out.println("\tGo back to menu         :      2");

			@SuppressWarnings("resource")
			Scanner supplierScanner = new Scanner(System.in);
			String supplierInput = supplierScanner.nextLine();
			switch (supplierInput) {
			case "1":
				System.out.println("Enter supplier ID: ");
				String supplierID = supplierScanner.nextLine();

				System.out.println("Enter supplier name: ");
				String supplierName = supplierScanner.nextLine();

				supermarket.getSuppliers().put(userinput, new Supplier(supplierID, supplierName));
				state = STATE.START;
				validInput = true;
				break;
			case "2":
				state = STATE.START;
				validInput = true;
				break;
			}
		} else {
			System.out.println("Product not found!");
		}
		return validInput;
	}

	private boolean processMostSold(String userinput) {
		boolean validinput = false;
		try {

			int inputno = Integer.parseInt(userinput);

			if (inputno >= 0 && inputno <= supermarket.getProductList().size()) {
				System.out.print("------------------------------------------------------------------------------\n");
				System.out.print(String.format("%-40s %-5s %-7s %-4s\n", "", "Qty", "Sale", "Percentage"));
				int counter = inputno;
				if (inputno == 0) {
					counter = supermarket.getProductList().size();
				}

				for (int i = 0; i < counter; i++) {
					String name = supermarket.getMostSoldItems()[i].getProductName();
					double sale = supermarket.getMostSoldItems()[i].getSubtotal();
					int qty = 0;
					if (supermarket.getSaleQuantity().containsKey(supermarket.getMostSoldItems()[i].getProductId())) {
						qty = supermarket.getSaleQuantity().get(supermarket.getMostSoldItems()[i].getProductId());
					}

					double percentage = 0;
					if (supermarket.getSalePercentage().containsKey(supermarket.getMostSoldItems()[i].getProductId())) {
						percentage = supermarket.getSalePercentage()
								.get(supermarket.getMostSoldItems()[i].getProductId());
					}

					System.out.print(String.format("%-40s %-5s %-7.2f %-4.2f%%\n", name, qty, sale, percentage));
				}

				System.out.print("------------------------------------------------------------------------------\n");
				state = STATE.START;
				validinput = true;
			} else {
				System.out.println("Invalid number of items");
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid Input");

		}
		return validinput;
	}

	public boolean processStart(String userinput) {
		boolean validInput = false;

		if (0 == userinput.compareTo(mostsold)) {
			state = STATE.MOSTSOLD;
			validInput = true;
		}

		else if (0 == userinput.compareTo(supplier)) {
			state = STATE.SUPPLIER;
			validInput = true;
		}

		else if (0 == userinput.compareTo(pricechange)) {
			state = STATE.PRICECHANGE;
			validInput = true;
		}

		else if (0 == userinput.compareTo(bulkdiscount)) {
			state = STATE.BULKDISCOUNT;
			validInput = true;
		}

		else if (0 == userinput.compareTo(stock)) {
			state = STATE.STOCK;
			validInput = true;
		}

		return validInput;
	}

}
