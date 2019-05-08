package sale;
import java.util.*;
public class Sale {

	private final double dollarPerPoint = 10.00;
	
	private String saleId;
	private String customerId;
	private Date date;
	private List<SaleLineItem> itemList;
	private double total;
	
	public Sale(String saleId, String customerId) {
		this.saleId = saleId;
		this.customerId = customerId;

		this.total = 0.0;
		date = new Date();
		itemList = new ArrayList<SaleLineItem>();
	}
	
	public String getSaleId() {
		return saleId;
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
		
	public void removeItem(String productId) {
		
		SaleLineItem item = getItem(productId);
		if(item != null) {	
			itemList.remove(item);					
		}
	}
	
	public void removeAllItems() {
		itemList.clear();
	}
	
	public void setUnitPrice(String productId, double price) {		
		SaleLineItem item = getItem(productId);
		if(item != null) {	
			item.setUnitPrice(price);
		}
	}
		
	public double calculateCustomerPoints() {	
		calculateTotal();
		return total/dollarPerPoint;		
	}
	
	
	
	
}
