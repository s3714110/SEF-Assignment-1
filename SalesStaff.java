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
	
	public void voidTransaction(Receipt receipt) {
		receipt = null;
	}
	
	public void addToSalesHistory(Receipt receipt) {
		salesHistory.add(receipt);
		///I am unsure, does this keep adding receipts to the salesHistory list, or does it overwrite the first receipt?
	}
	
//	What about this?
//	public void addToSalesHistory() {
//		for (Receipt addReceipt : salesHistory) {
//			salesHistory.add(addReceipt);
//		}

}
