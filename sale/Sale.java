package sale;
import java.util.*;
public class Sale {

	private final double dollarPerPoint = 10.00;
	
	private String saleId;
	private Date date;
	private List<SaleLineItem> itemList;
	private String employeeId;
	private String customerId;
	private double total;
	
	public Sale(String employeeId, String customerId, String saleId) {
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.saleId = saleId;
		this.total = 0.0;
		date = new Date();
		itemList = new ArrayList<SaleLineItem>();
	}
	
	public String getSaleId() {
		return saleId;
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
	
	public List<SaleLineItem> getItemList() {
		return itemList;
	}
	
	public SaleLineItem getItem(String productId) {
		SaleLineItem item = null;
				
		for (SaleLineItem salelineitem : itemList) {
			if(0 == salelineitem.getProductId().compareTo(productId)) {
				item = salelineitem;
				break;
			}
		}		
		
		return item;
	}
	
	public double getTotal() {
		calculateTotal();
		return total;
	}
	
	private void calculateTotal() {
		double total = 0;
		for ( SaleLineItem item : itemList) {
			total += item.getSubtotal();
		}
		
		this.total = total;
	}
		
	public void addItem(SaleLineItem newItem) {
		// If already in the list. Update itemList
		if(itemList.contains(newItem)) {
			SaleLineItem item = itemList.get(itemList.indexOf(newItem));			
			item.add(item.getQty());
		}
		else { // If not already in itemList. Add to itemList
			itemList.add(newItem);
		}		
	}
		
	public void removeItem(String productId, int qty) {
		
		SaleLineItem item = getItem(productId);
		if(item != null) {	
			item.remove(qty);
			if(item.getQty() <= 0) {
				itemList.remove(item);
			}			
		}
	}
	
	public void removeAllItems() {
		itemList.clear();
	}
	
	public void setPrice(String productId, double price) {
		double discount = 0.0;
		
		SaleLineItem item = getItem(productId);
		if(item != null) {	
			item.setPrice(price);
		}
	}
	
	public double calculateCustomerPoints() {	
		calculateTotal();
		return total/dollarPerPoint;		
	}
	
	
	
	
}
