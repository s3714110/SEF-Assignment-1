package view;


import controller.SupermarketCustomerController;

public class SupermarketCustomerView implements SupermarketView {

	SupermarketCustomerController controller;
	
	public SupermarketCustomerView(SupermarketCustomerController controller) {
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
		
		show = show.concat("Customer Show Start\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	

}
