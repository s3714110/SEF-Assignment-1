package view;

import controller.SupermarketController;
import controller.SupermarketCustomerController;
import controller.SupermarketWarehouseStaffController;
import model.SupermarketSystem;

public class SupermarketWarehouseStaffView extends SupermarketView {

	SupermarketWarehouseStaffController controller;
	
	public SupermarketWarehouseStaffView(SupermarketSystem supermarket) {
		super(supermarket);
	}
	
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketWarehouseStaffController)controller;
	}
	
	
	
	public void show() {
	
		switch (controller.getState())
		{
			case START:
				showStart();
				controller.processInput(controller.getUserInput());
				break;
			case RESTOCK:
				showRestock();
				controller.processInput(controller.getUserInput());
				break;
			case CHECKSTOCK:
				showCheckStock();
				controller.processInput(controller.getUserInput());
				break;
			case VIEWPRODUCTLIST:
				showProductList();
				controller.setStateStart();
				break;
			case CHECKINVENTORY:
				supermarket.printWarehouseInventory();
				controller.setStateStart();
				break;
		}
		
		
	}
	
	public void showStart() {
		String show = "\n\n";
		
		show = show.concat("welcome " + controller.getEmployeeId() + "\n");
		show = show.concat("\tRestock            :  1\n");
		show = show.concat("\tCheck Stock        :  2\n");
		show = show.concat("\tView Product List  :  3\n");
		show = show.concat("\tCheck Inventory    :  4\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
	public void showRestock() {
		String show = "\n";
		
		show = show.concat("Enter Product Id: ");
		
		System.out.println(show);
	}
	
	public void showCheckStock() {
		String show = "\n";
		
		show = show.concat("Enter Product Id: ");
		
		System.out.println(show);
	}
	
}
