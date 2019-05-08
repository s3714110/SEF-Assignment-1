package controller;

import model.SupermarketSystem;
import view.SupermarketView;

public class SupermarketSystemController extends SupermarketController{

	public static enum STATE { START, LOGIN };
	public static enum LOGIN_TYPE { CUSTOMER, MANAGER, SALESSTAFF, WAREHOUSESTAFF };
	
	public final static String exit_supermarket = "exit";
	public final static String cancel = "cancel";
	
	public final static String login_customer = "1";
	public final static String login_manager = "2";
	public final static String login_salesstaff = "3";
	public final static String login_warehousestaff = "4";	
	
	private STATE state;
	private LOGIN_TYPE logintype;
	
	public SupermarketSystemController(SupermarketSystem supermarket, SupermarketView view){
		super(supermarket, view);
		state = STATE.START;
	}
	
	
	public void setExit(boolean exitSystem) {
		supermarket.setExit( exitSystem );
	}

	public STATE getState() {
		return state;
	}

	
	public boolean processInput(String userinput) {
		boolean validInput = false;
		
		switch (state)
		{
			case START:
				validInput = processStart(userinput);
				break;
			case LOGIN:
				validInput = processLogin(userinput);
				break;
		}
		
		return validInput;
	}
	
	public boolean processStart(String userinput) {
		boolean validInput = false;
		
		// exit system
		if(0 == userinput.toLowerCase().compareTo(exit_supermarket)) {
			setExit(true);
			return true;
		}
		
		
		// process input
		if(0 == userinput.compareTo(login_customer)) {
			state = STATE.LOGIN;
			logintype = LOGIN_TYPE.CUSTOMER;
			validInput = true;
		}
		else if(0 == userinput.compareTo(login_manager)) {
			state = STATE.LOGIN;
			logintype = LOGIN_TYPE.MANAGER;
			validInput = true;
		}
		else if(0 == userinput.compareTo(login_salesstaff)) {
			state = STATE.LOGIN;
			logintype = LOGIN_TYPE.SALESSTAFF;
			validInput = true;
		}
		else if(0 == userinput.compareTo(login_warehousestaff)) {
			state = STATE.LOGIN;
			logintype = LOGIN_TYPE.WAREHOUSESTAFF;
			validInput = true;
		} 
		
		return validInput;
	}
	
	public boolean processLogin(String userinput) {
		boolean validInput = false;
		
		// cancel login
		if(0 == userinput.toLowerCase().compareTo(cancel)) {
			this.state = STATE.START;
			return true;
		}
		
		
		// continue process input
		switch (logintype)
		{
			case CUSTOMER:
				validInput = supermarket.validCustomerId(userinput);
				break;
			
			case MANAGER:
				validInput = supermarket.validManagerId(userinput);
				break;
			
			case SALESSTAFF:
				validInput = supermarket.validSalesStaffId(userinput);
				break;
			
			case WAREHOUSESTAFF:
				validInput = supermarket.validWarehouseStaffId(userinput);
				break;		
			
		}
		
		if(validInput) {
			login(userinput);
			this.state = STATE.START;
		}
		
		return validInput;
	}
	
	
	public void login(String id) {
				
		switch (logintype)
		{
			case CUSTOMER:
				supermarket.login_Customer(id);
				break;
			
			case MANAGER:
				supermarket.login_Manager(id);
				break;
			
			case SALESSTAFF:
				supermarket.login_SalesStaff(id);
				break;
			
			case WAREHOUSESTAFF:
				supermarket.login_WarehouseStaff(id);
				break;		
			
		}
		
	}

	
}
