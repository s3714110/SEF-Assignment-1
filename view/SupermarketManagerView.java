package view;

import controller.SupermarketController;
import controller.SupermarketManagerController;
import controller.SupermarketSalesStaffController;
import model.SupermarketSystem;

public class SupermarketManagerView extends SupermarketView {

	SupermarketManagerController controller;

	public SupermarketManagerView(SupermarketSystem supermarket) {
		super(supermarket);
	}

	@Override
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketManagerController)controller;
		
	}
	
	public void show() {

		switch (controller.getState()) {
		case START:
			showStart();
			break;
		case MOSTSOLD:
			showMostSoldItems();
			break;
		case SUPPLIER:
			showAskProductID();
			break;
		case PRICECHANGE:
			showAskProductID();
			break;
		case BULKDISCOUNT:
			showAskProductID();
			break;
		case STOCK:
			showAskProductID();
			break;
		}

		String userinput = controller.getUserInput();
		controller.processInput(userinput);
	}

	

	public void showStart() {
		String show = "\n\n";

		show = show.concat("Manager Show Start\n");
		show = show.concat("\tView Most Sold items    :  1\n");
		show = show.concat("\tView Supplier Details   :  2\n");
		show = show.concat("\tUpdate Product Price    :  3\n");
		show = show.concat("\tSet Bulk Discount       :  4\n");
		show = show.concat("\tManage Product Stock    :  5\n");
		show = show.concat("\tTo Logout enter 'logout'\n");

		System.out.println(show);
	}

	public void showMostSoldItems() {
		String show = "\n\n";

		show = show.concat("How many items do you want to display (type 0 to display all): ");

		System.out.println(show);
	}

	private void showAskProductID() {
		String show = "\n\n";

		show = show.concat("Enter product ID: ");

		System.out.println(show);

	}

	
	
	

}
