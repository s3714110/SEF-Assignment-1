package main;

import java.util.ArrayList;
import java.util.List;

public class SalesStaff extends Employee{

	private List<Receipt> salesHistory;
	
	public SalesStaff(String id, String name, String address, String phoneNo) {
		super(id, name, address, phoneNo);
		salesHistory = new ArrayList<Receipt>();
		}
	
	public List<Receipt> getSalesHistory(){
		return salesHistory;
		
	}
	
	//public void voidTransaction() {}
	
	//public void addToSalesHistory(Receipt) {}

	

}
