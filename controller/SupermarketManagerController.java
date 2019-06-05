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
		this.view = (SupermarketManagerView) view;
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
			boolean goback = false;
			do {
				view.showMessage(String.format(
						"Product %s : Stock level: %s, Replenish Level: %s, Automatic restock quantity: %s \n",
						supermarket.getProduct(userinput).getName(), supermarket.getStockLevel(userinput),
						supermarket.getProduct(userinput).getReplenishLevel(),
						supermarket.getProduct(userinput).getRestockQty()));
				view.showMessage("\tRestock product         :      1");
				view.showMessage("\tSet Replenish Level     :      2");
				view.showMessage("\tSet restock quantity    :      3");
				view.showMessage("\tGo back to menu         :      4");

				Scanner stockScanner = new Scanner(System.in);
				String input = stockScanner.nextLine();
				switch (input) {
				case "1":
					view.showMessage("Enter the amount you want to restock: ");
					String stockqty = stockScanner.nextLine();
					try {
						int stockQty = Integer.parseInt(stockqty);
						supermarket.restock(userinput, stockQty);

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "2":
					view.showMessage("Enter the replenish level: ");
					String stockrep = stockScanner.nextLine();
					try {
						int stockRep = Integer.parseInt(stockrep);
						supermarket.getProduct(userinput).setReplenishLevel(stockRep);

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "3":
					view.showMessage("Enter the automatic restock level: ");
					String stockrestock = stockScanner.nextLine();
					try {
						int stockRestock = Integer.parseInt(stockrestock);
						supermarket.getProduct(userinput).setRestockQty(stockRestock);

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "4":
					state = STATE.START;
					validInput = true;
					goback = true;
					break;

				}
			} while (goback == false);

		} else {
			view.showMessage("Product not found!");
		}
		return validInput;
	}

	private boolean processBulkDiscount(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);

		if (found) {
			boolean goback = false;
			do {
				view.showMessage("Current bulk discount on product " + supermarket.getProduct(userinput).getName());
				if (!supermarket.hasBulkDiscount(userinput)) {
					view.showMessage("None");
				} else {
					supermarket.getBulkDiscounts(userinput).forEach(
							(k, v) -> view.showMessage("Quantity: " + k + ", Discount percentage: " + v * 100 + "%"));
				}

				view.showMessage("\tAdd/replace bulk sale   :      1");
				view.showMessage("\tRemove bulk sale        :      2");
				view.showMessage("\tGo back to menu         :      3");

				Scanner bulkScanner = new Scanner(System.in);
				String input = bulkScanner.nextLine();
				switch (input) {
				case "1":
					view.showMessage("Enter the quantity for the bulk sale you want to add: ");
					String bulkqtyadd = bulkScanner.nextLine();
					view.showMessage("Enter the percentage for the bulk sale you want to add: ");
					String bulkperadd = bulkScanner.nextLine();
					try {
						int bulkQtyAdd = Integer.parseInt(bulkqtyadd);
						double bulkPerAdd = Double.parseDouble(bulkperadd) / 100;
						supermarket.addBulkDiscount(userinput, bulkQtyAdd, bulkPerAdd);

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "2":
					view.showMessage("Enter the quantity for the bulk sale you want to remove(0 to remove all: ");
					String bulkqtyremove = bulkScanner.nextLine();
					try {
						int bulkQtyRemove = Integer.parseInt(bulkqtyremove);
						if (bulkQtyRemove == 0) {
							supermarket.removeAllBulkDiscount(userinput);
						} else if (supermarket.getBulkDiscounts(userinput).containsKey(bulkQtyRemove)) {
							supermarket.removeBulkDiscount(userinput, bulkQtyRemove);

						} else {
							view.showMessage("Incorrect value!");
						}

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "3":
					state = STATE.START;
					validInput = true;
					goback = true;
					break;
				}
			} while (goback == false);
		} else {
			view.showMessage("Product not found!");
		}
		return validInput;
	}

	private boolean processPriceChange(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			boolean goback = false;
			do {
				view.showMessage(String.format("Product %s price: $ %.2f \n",
						supermarket.getProduct(userinput).getName(), supermarket.getProduct(userinput).getPrice()));
				if (supermarket.hasPromotionDiscount(userinput)) {
					System.out.printf("Product current discounted price: $ %.2f \n",
							supermarket.getPromotionalDiscount(userinput));
				}
				view.showMessage("\tChange product price    :      1");
				view.showMessage("\tSet promotional discount:      2");
				view.showMessage("\tGo back to menu         :      3");
				
				Scanner priceScanner = new Scanner(System.in);
				String input = priceScanner.nextLine();
				switch (input) {
				case "1":
					view.showMessage("Enter new price: ");
					String priceInput = priceScanner.nextLine();

					try {
						double price = Double.parseDouble(priceInput);
						supermarket.getProduct(userinput).setPrice(price);
						
						

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "2":
					view.showMessage("Enter discounted price (O to remove promotion): ");
					String discountinput = priceScanner.nextLine();
					try {
						double discount = Double.parseDouble(discountinput);
						if (discount == 0) {
							supermarket.removePromotionDiscount(userinput);
							
							
						} else if (discount > 0 && discount < supermarket.getProduct(userinput).getPrice()) {
							supermarket.addPromotionDiscount(userinput, discount);
							
							
						} else {
							view.showMessage("Invalid input");
						}

					} catch (NumberFormatException e) {
						view.showMessage("Invalid input");
					}
					break;
				case "3":
					state = STATE.START;
					validInput = true;
					goback = true;
					break;
				}
			} while (goback == false);
		} else {
			view.showMessage("Product not found!");
		}
		return validInput;
	}

	private boolean processSupplier(String userinput) {
		boolean validInput = false;
		boolean found = supermarket.validProductId(userinput);
		if (found) {
			boolean goback = false;
			do {
			if (supermarket.getSuppliers().containsKey(userinput)) {
				view.showMessage("Supplier ID: " + supermarket.getSuppliers().get(userinput).getId());
				view.showMessage("Supplier Name: " + supermarket.getSuppliers().get(userinput).getName());

			} else {
				view.showMessage("This item doesn't have supplier details");
			}

			view.showMessage("\tUpdate supplier detail  :      1");
			view.showMessage("\tGo back to menu         :      2");

			
			Scanner supplierScanner = new Scanner(System.in);
			String supplierInput = supplierScanner.nextLine();
			switch (supplierInput) {
			case "1":
				view.showMessage("Enter supplier ID: ");
				String supplierID = supplierScanner.nextLine();

				view.showMessage("Enter supplier name: ");
				String supplierName = supplierScanner.nextLine();

				supermarket.getSuppliers().put(userinput, new Supplier(supplierID, supplierName));
				
				break;
			case "2":
				state = STATE.START;
				validInput = true;
				goback = true;
				break;
			}
			}while (goback == false);
		} else {
			view.showMessage("Product not found!");
		}
		return validInput;
	}

	private boolean processMostSold(String userinput) {
		boolean validinput = false;
		try {

			int inputno = Integer.parseInt(userinput);

			if (inputno >= 0 && inputno <= supermarket.getProductList().size()) {
				view.showMessage("------------------------------------------------------------------------------");
				view.showMessage(String.format("%-40s %-5s %-7s %-4s", "", "Qty", "Sale", "Percentage"));
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

					view.showMessage(String.format("%-40s %-5s %-7.2f %-4.2f%%", name, qty, sale, percentage));
				}

				view.showMessage("------------------------------------------------------------------------------");
				state = STATE.START;
				validinput = true;
			} else {
				view.showMessage("Invalid number of items");
			}

		} catch (NumberFormatException e) {
			view.showMessage("Invalid Input");

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
