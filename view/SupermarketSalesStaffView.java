package view;

import controller.SupermarketSalesStaffController;

public class SupermarketSalesStaffView implements SupermarketView {

	
	SupermarketSalesStaffController controller;
	
	public SupermarketSalesStaffView(SupermarketSalesStaffController controller) {
		this.controller = controller;
	}
	
	
	public void show() {
	
		switch (controller.getState())
		{
			case START:
				showStart();
				break;
		}
		
		String userinput = controller.getUserInput();
		controller.processInput(userinput);
		
	}
	
	public void showStart() {
		String show = "\n\n";
		
		show = show.concat("Sales Staff Show Start\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
}
