package view;

import controller.SupermarketController;
import controller.SupermarketManagerController;
import model.SupermarketSystem;

public class SupermarketManagerView extends SupermarketView {

	SupermarketManagerController controller;
	
	public SupermarketManagerView(SupermarketSystem supermarket) {
		super(supermarket);
	}
	
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketManagerController)controller;
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
		
		show = show.concat("Manager Show Start\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
}
