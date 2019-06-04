package controller;

import model.SupermarketSystem;
import sale.Sale;
import view.SupermarketSalesStaffView;
import view.SupermarketView;

public class SupermarketSalesStaffController extends SupermarketController{
	public static enum STATE { START, VIEWACTIVESALE, EDITSALE, VOIDSALE };
	
	public final static String viewactivesale = "1";
	public final static String editsale = "2";
	public final static String voidsale = "3";
	
	public final static String editQty = "1";
	public final static String remove = "2";
	
	private STATE state;
	private SupermarketSalesStaffView view;
	
	public SupermarketSalesStaffController(SupermarketSystem supermarket, SupermarketView view, String id) {
		super(supermarket, view);
		this.view = (SupermarketSalesStaffView)view;
		state = STATE.START;
	}

	public void setStateStart() {
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

		if(0 == userinput.toLowerCase().compareTo(back)) {
			state = STATE.START;
			return true;
		}
		
		
		// continue process input
		switch (state)
		{
			case START:
				validInput = processStart(userinput);
				break;
			case VIEWACTIVESALE:
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
		
		
		// process input
		if(0 == userinput.compareTo(viewactivesale)) {
			state = STATE.VIEWACTIVESALE;
			validInput = true;
		}
		else if(0 == userinput.compareTo(editsale)) {
			state = STATE.EDITSALE;
			validInput = true;
		}
		else if(0 == userinput.compareTo(voidsale)) {
			state = STATE.VOIDSALE;
			validInput = true;
		}
		
		return validInput;
	}
	
	public boolean processEditSale(String customerId) {
		boolean validInput = false;
		
		validInput = supermarket.validCustomerId(customerId);
		
		if(validInput) {
			Sale sale = supermarket.getActiveSale(customerId);
			
			if(sale != null) {
				view.showSale(sale);
				view.showSelectItem();
				
				String product = getUserInput();
				
				try {
					int index = Integer.parseInt(product);
					
					sale.removeItem(index);	
					state = STATE.START;
				}
				catch(NumberFormatException e) {
					
				}			
			}
			else {
				view.showMessage("No Active Sale");
			}
		}
		
		
		return validInput;
	}
	
	public boolean processVoidSale(String customerId) {
		boolean validInput = false;
		
		validInput = supermarket.validCustomerId(customerId);
		
		if(validInput) {
			supermarket.removeSale(customerId);
			state = STATE.START;
		}
		
		return validInput;
	}
	
}
