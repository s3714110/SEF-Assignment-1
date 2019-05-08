package view;

import controller.SupermarketController;
import controller.SupermarketSystemController;
import model.SupermarketSystem;

public class SupermarketSystemView extends SupermarketView {

	SupermarketSystemController controller;
	
	public SupermarketSystemView(SupermarketSystem supermarket) {
		super(supermarket);
	}
	
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketSystemController)controller;
	}
	
	
	public void show() {
		
		switch (controller.getState())
		{
			case START:
				showStart();
				break;
			case LOGIN:
				showLogin();
				break;	
		}

		boolean validInput = false;
		
		
		String userinput = controller.getUserInput();		
		controller.processInput(userinput);		
	}
	
	public void showStart() {
		String show = "\n\n";
		
		show = show.concat("Login Options:\n");
		show = show.concat("\tCustomer        :  " + SupermarketSystemController.login_customer + "\n");
		show = show.concat("\tManager         :  " + SupermarketSystemController.login_manager + "\n");
		show = show.concat("\tSales Staff     :  " + SupermarketSystemController.login_salesstaff + "\n");
		show = show.concat("\tWarehouse Staff :  " + SupermarketSystemController.login_warehousestaff + "\n");
		show = show.concat("\tExit         :  " + SupermarketSystemController.exit_supermarket + "\n\n");
		
		System.out.println(show);
	}
	
	public void showLogin() {
		String show = "\n\n";
		
		show = show.concat("To Cancel enter 'cancel'\n");
		show = show.concat("Enter User Id:\n");
		
		System.out.println(show);
	}
	
	public void showInputError() {
		String show = "";
		
		show = show.concat("\t\tInput Error\n");
		
		System.out.println(show);
	}	

}
