import java.util.*;
public class Receipt {

	private String receiptId;
	private Date date;
	private List<ReceiptItem> itemList;
	private String employeeId;
	private String customerId;
	private String total;
	
	public Receipt(String employeeId, String customerId, String receiptId) {
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.receiptId = receiptId;
		date = new Date();
		itemList = new LinkedList<ReceiptItem>();
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
	
	public void addItem(String productId) {
		itemList.add(new ReceiptItem(productId));
	}
	
	public void removeItem(String productId) {
		itemList.remove(productId);
	}
	
	public String toString() {
		int counter = 1;
		total = String.format("%-25s %s\n", "Employee ID:", getEmployeeId()) +
				String.format("%-25s %s\n", "Customer ID:", getCustomerId()) +
				String.format("%-25s %s\n", "Date:", getDate());
		for (ReceiptItem temp: itemList ) {
				total += counter + ") " + itemList.toString();
				counter ++;
		}
		return total;
	}
	
}
