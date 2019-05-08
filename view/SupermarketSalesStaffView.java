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
