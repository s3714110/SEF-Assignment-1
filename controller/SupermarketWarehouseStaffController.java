package controller;

import controller.SupermarketCustomerController.STATE;
import model.SupermarketSystem;
import view.SupermarketWarehouseStaffView;
import view.SupermarketView;

public class SupermarketWarehouseStaffController extends SupermarketController{

	public static enum STATE { START, RESTOCK, CHECKSTOCK, VIEWPRODUCTLIST, CHECKINVENTORY };
	
	public final static String restock = "1";
	public final static String checkstock = "2";
	public final static String viewproductlist = "3";
	public final static String checkinventory = "4";
	
	
	private STATE state;
	private SupermarketWarehouseStaffView view;
	
	String employeeId;
	
	
	public SupermarketWarehouseStaffController(SupermarketSystem supermarket, SupermarketView view, String employeeId) {
		super(supermarket, view);
		this.view = (SupermarketWarehouseStaffView)view;
		state = STATE.START;
		this.employeeId = employeeId;
	}

	public void setStateStart() {
		state = STATE.START;
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
			case VIEWPRODUCTLIST:
				break;
			case CHECKINVENTORY:
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
		else if(0 == userinput.compareTo(viewproductlist)) {
			state = STATE.VIEWPRODUCTLIST;
			validInput = true;
		}
		else if(0 == userinput.compareTo(checkinventory)) {
			state = STATE.CHECKINVENTORY;
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
			
			validInput = true;
			state = STATE.START;
		}		
		
		
		return validInput;
	}
	
	
}
