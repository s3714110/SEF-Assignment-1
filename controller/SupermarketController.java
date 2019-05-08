package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.SupermarketSystem;
import view.SupermarketSystemView;
import view.SupermarketView;

public abstract class SupermarketController {

	public final static String logout = "logout";
	public final static String cancel = "cancel";
	public final static String back = "back";
	
	protected SupermarketSystem supermarket;
	protected SupermarketView view;
	
	public SupermarketController(SupermarketSystem supermarket, SupermarketView view){
		this.supermarket = supermarket;
		this.view = view;
	}
	
	public abstract boolean processInput(String userinput);
	
	
	public String getUserInput() {
		String userinput = "";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		try {
			userinput = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		return userinput;
	}
	
	public void logout() {
		supermarket.setView_System();
	}
	
	public int getQty() {
		int qty = -1;
		String userinput;
		
		
		view.showEnterQty();
		userinput = getUserInput();
		
		try {
			qty = Integer.parseInt(userinput);
			
		}
		catch(NumberFormatException e) {
			
		}		
		
		
		return qty;
	}
	
}
