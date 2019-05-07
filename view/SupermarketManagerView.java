package view;

import controller.SupermarketManagerController;

public class SupermarketManagerView implements SupermarketView {

	SupermarketManagerController controller;
	
	public SupermarketManagerView(SupermarketManagerController controller) {
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
		
		show = show.concat("Manager Show Start\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
}
