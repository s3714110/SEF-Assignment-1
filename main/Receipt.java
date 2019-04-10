package main;
import java.util.*;
public class Receipt {

	private String receiptId;
	private Date date;
	private List<ReceiptItem> itemList;
	private String employeeId;
	private String customerId;
	private double total;
	
	public Receipt(String employeeId, String customerId, String receiptId) {
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.receiptId = receiptId;
		this.total = 0.0;
		date = new Date();
		itemList = new ArrayList<ReceiptItem>();
	}
	
	public String getReceiptId() {
		return receiptId;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public List<ReceiptItem> getItemList() {
		return itemList;
	}
	
	public double getTotal() {
		updateTotal();
		return total;
	}
	
	private void updateTotal() {
		double total = 0;
		for ( ReceiptItem item : itemList) {
			total += item.getSubtotal();
		}
		
		this.total = total;
	}
	
	
	public void addItem(Product product, int qty) {
		// If already in the list. Update itemList
		if(itemList.contains(product)) {
			ReceiptItem item = itemList.get(itemList.indexOf(product));
			item.add(qty);
		}
		else { // If not already in itemList. Add to itemList
			itemList.add(new ReceiptItem(product, qty));
		}		
	}
	
	
	public void removeItem(String productId) {
		itemList.remove(productId);
	}
	
//	public String toString() {
//		int counter = 1;
//		total = String.format("%-25s %s\n", "Employee ID:", getEmployeeId()) +
//				String.format("%-25s %s\n", "Customer ID:", getCustomerId()) +
//				String.format("%-25s %s\n", "Date:", getDate());
//		for (ReceiptItem temp: itemList ) {
//				total += counter + ") " + itemList.toString();
//				counter ++;
//		}
//		return total;
//	}
	
}
