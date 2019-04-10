import java.util.*;
public class Receipt {

	private String receiptId;
	private Date date;
	private List<ReceiptItem> itemList;
	private String employeeId;
	private String customerId;
	private String display;
	private double total;
	private ProductList productList;
	
	public Receipt(String employeeId, String customerId, String receiptId, ProductList productList) {
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.receiptId = receiptId;
		this.productList = productList;
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
		itemList.add(new ReceiptItem(productId, productList));
	}
	
	public void removeItem(String productId) {
		for (ReceiptItem temp: itemList ) {
			if(temp.getProductId() == productId) {
				itemList.remove(temp);
			}
		}
	}
	
	public ReceiptItem getItem(String productId) {
		for (ReceiptItem temp: itemList ) {
			if(temp.getProductId() == productId) {
				return temp;
			}
		}
		
		return null;
		
	}
	
	public double getTotalPrice() {
		total = 0;
		for (ReceiptItem temp: itemList ) {
			total += temp.getTotal();
		}
		return total;
	}
		
	
	public String toString() {
		int counter = 1;
		display = String.format("%-25s %s\n", "Employee ID:", employeeId) +
				String.format("%-25s %s\n", "Customer ID:", customerId) +
				String.format("%-25s %s\n", "Date:", getDate());
		for (ReceiptItem temp: itemList ) {
				display += counter + ") " + itemList.toString();
				counter ++;
		}
		return display;
	}
	
}
