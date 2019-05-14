package controller;

import model.SupermarketSystem;

public class SupermarketSalesStaffController extends SupermarketController{
	public static enum STATE { START, VIEWACTIVESALE, EDITSALE, VOIDSALE };

	public final static String viewactivesale = "1";
	public final static String editsale = "2";
	public final static String voidsale = "3";
	
	private STATE state;
	
	public SupermarketSalesStaffController(SupermarketSystem supermarket) {
		super(supermarket);
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
			case VIEWACTIVESALE:
				validInput = showActiveSale(userinput);
				break;
			case EDITSALE:
				validInput = processEditSale(userinput);
				break;
			case VOIDSALE:
				validInput = processVoidSale(userinput);
				break;
		}
		
		return validInput;
	}	
		
	
	public boolean processStart(String userinput) {
		boolean validInput = false;
		
		if (0 == userinput.compareTo(viewactivesale))
		{
			state = STATE.VIEWACTIVESALE;
			validInput = true;
		}
		
		else if (0 == userinput.compareTo(editsale))
		{
			state = STATE.EDITSALE;
			validInput = true;
		}
		
		else if (0 == userinput.compareTo(voidsale))
		{
			state = STATE.VOIDSALE;
			validInput = true;
		}
		
		return validInput;
	}
	
	public boolean showActiveSale(String userinput)
	{
		if (supermarket.validCustomerId(userinput))
		{
			
			
			
			
		}
		
		return false;
	}
	
	public boolean processEditSale(String userinput)
	{
		
		return false;
	}
	
	
	
	public boolean processVoidSale(String userinput)
	{
		
		return false;
	}
}
