package view;

import controller.SupermarketWarehouseStaffController;

public class SupermarketWarehouseStaffView implements SupermarketView {

	SupermarketWarehouseStaffController controller;
	
	public SupermarketWarehouseStaffView(SupermarketWarehouseStaffController controller) {
		this.controller = controller;
	}
	
	
	public void show() {
	
		switch (controller.getState())
		{
			case START:
				showStart();
				break;
			case RESTOCK:
				showRestock();
				break;
			case CHECKSTOCK:
				showCheckStock();
				break;
		}
		
		String userinput = controller.getUserInput();
		controller.processInput(userinput);
	}
	
	public void showStart() {
		String show = "\n\n";
		
		show = show.concat("welcome " + controller.getEmployeeId() + "\n");
		show = show.concat("\tRestock    :  1\n");
		show = show.concat("\tCheck Stock :  2\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
	public void showRestock() {
		String show = "\n\n";
		
		show = show.concat("Enter Product Id: ");
		
		System.out.println(show);
	}
	
	public void showCheckStock() {
		String show = "\n\n";
		
		show = show.concat("Enter Product Id: ");
		
		System.out.println(show);
	}
	
}
