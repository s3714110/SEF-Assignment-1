package controller;

import model.SupermarketSystem;
import view.SupermarketView;

public class SupermarketManagerController extends SupermarketController{

	public static enum STATE { START };
	private STATE state;
	
	public SupermarketManagerController(SupermarketSystem supermarket, SupermarketView view, String id) {
		super(supermarket, view);
		state = STATE.START;
	}

	public STATE getState() {
		return state;
	}
	
	// getUserInput() calls this function
	public boolean processInput(String userinput) {
		boolean validInput = false;
		
		// logout
		if(0 == userinput.toLowerCase().compareTo(logout)) {
			logout();
			return true;
		}
		
		
		// continue process input
		switch (state)
		{
			case START:
				validInput = processStart(userinput);
				break;
		}
		
		return validInput;
	}
	
	
	
	public boolean processStart(String userinput) {
		boolean validInput = false;
		
		
		
		return validInput;
	}
	
}