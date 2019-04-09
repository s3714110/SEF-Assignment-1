package assignment1;

import java.util.List;

public class SalesStaff extends Employee{

	private List<Receipt> salesHistory;
	
	public SalesStaff(String empId, String empName, List<Receipt> SalesHistory) {
		super(empId, empName);
		this.salesHistory = salesHistory;
		}
	
	public List<Receipt> getSalesHistory(){
		return salesHistory;
		
	}
	
	//public void voidTransaction() {}
	
	//public void addToSalesHistory(Receipt) {}

	

}
