package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.SupermarketSystem;

public abstract class SupermarketController {

	public final static String logout = "logout";
	
	protected SupermarketSystem supermarket;
	
	public SupermarketController(SupermarketSystem supermarket){
		this.supermarket = supermarket;
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
	
}
