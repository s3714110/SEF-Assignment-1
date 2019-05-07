package controller;

import model.SupermarketSystem;

public class SupermarketWarehouseStaffController extends SupermarketController{

	public static enum STATE { START, RESTOCK, CHECKSTOCK };
	
	public final static String restock = "1";
	public final static String checkstock = "2";
	
	
	private STATE state;
	
	String employeeId;
	
	
	public SupermarketWarehouseStaffController(SupermarketSystem supermarket, String employeeId) {
		super(supermarket);
		state = STATE.START;
		this.employeeId = employeeId;
	}

	public STATE getState() {
		return state;
	}
	public String getEmployeeId() {
		return employeeId;
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
			case RESTOCK:
				validInput = processRestock(userinput);
				break;
			case CHECKSTOCK:
				validInput = processCheckStock(userinput);
				break;
		}
		
		return validInput;
	}
	
	
	
	public boolean processStart(String userinput) {
		boolean validInput = false;
		
		if(0 == userinput.compareTo(restock)) {
			state = STATE.RESTOCK;
			validInput = true;
		}
		else if(0 == userinput.compareTo(checkstock)) {
			state = STATE.CHECKSTOCK;
			validInput = true;
		}
		
		return validInput;
	}
	
	public boolean processRestock(String userinput) {
		boolean validInput = false;
		
		if(supermarket.validProductId(userinput)) {
			
			String productId = userinput;
			int qty;
			
			do {
				qty = getQty();
				if(qty < 0) {
					System.out.println("Invalid Qty");
				}
			} while(qty < 0);
			
			
			supermarket.restock( productId, qty );
			state = STATE.START;
			validInput = true;
		}
		
		return validInput;
	}
	
	public boolean processCheckStock(String userinput) {
		boolean validInput = false;
		
		if(supermarket.validProductId(userinput)) {
			String productId = userinput;
			int stockLevel = supermarket.getStockLevel(productId);
			
			System.out.println("" + productId + ": " + stockLevel);
			
			state = STATE.START;
		}		
		
		
		return validInput;
	}
	
	public int getQty() {
		
		int qty = -1;
		String userinput;
		
		
		System.out.println("Enter Qty: ");		
		userinput = getUserInput();
		
		try {
			qty = Integer.parseInt(userinput);
			
		}
		catch(NumberFormatException e) {
			
		}		
		
		
		return qty;
	}
	
}
