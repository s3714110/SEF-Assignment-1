package view;

import controller.SupermarketController;
import controller.SupermarketSalesStaffController;
import model.SupermarketSystem;

public class SupermarketSalesStaffView extends SupermarketView {

	
	SupermarketSalesStaffController controller;
	
	public SupermarketSalesStaffView(SupermarketSystem supermarket) {
		super(supermarket);
	}
	
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketSalesStaffController)controller;
	}
	
	
	
	public void show() {
	
		switch (controller.getState())
		{
			case START:
				showStart();
				controller.processInput(controller.getUserInput());
				break;
			case VIEWACTIVESALE:
				showActiveSales();
				controller.setStateStart();
				break;
			case EDITSALE:
				showEditSale();
				controller.processInput(controller.getUserInput());
				break;
			case VOIDSALE:
				showVoidSale();
				controller.processInput(controller.getUserInput());
				
		}
		
	}
	
	public void showStart() {
		String show = "\n\n";
		
		show = show.concat("Welcome \n");
		show = show.concat("\tView Active Sale    :  1\n");
		show = show.concat("\tEdit Sale           :  2\n");
		show = show.concat("\tVoid Sale           :  3\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
	
	public void showEditSale() {
		String show = "\n";
		
		show = show.concat("Enter Customer ID:");
		
		System.out.println(show);
	}
	
	public void showSelectItem() {
		String show = "\n";
		
		show = show.concat("Select Item:");
		
		System.out.println(show);
	}
	
	public void showVoidSale() {
		String show = "\n";
		
		show = show.concat("Enter Customer ID:");
		
		System.out.println(show);
	}
	
}
