package user;

import java.util.ArrayList;
import java.util.List;

import sale.Sale;

public class SalesStaff extends Employee{

	private List<Sale> salesHistory;
	
	public SalesStaff(String id, String name, String address, String phoneNo) {
		super(id, name, address, phoneNo);
		salesHistory = new ArrayList<Sale>();
		}
	
	public List<Sale> getSalesHistory(){
		return salesHistory;
		
	}
	
	//public void voidTransaction() {}
	
	public void addToSalesHistory(Sale sale) {
		salesHistory.add(sale);
	}

	

}
