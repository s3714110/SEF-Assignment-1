package sale;
import java.util.*;
public class SaleLineItem {
	private String productId; 
	private String productName;
	private int qty;
	private double discount;
	private double price;
	private double subtotal;
	
	public SaleLineItem(String productId, String productName, int qty, double price) {
		this.productId = productId;
		this.productName = productName;
		this.qty = qty;		
		this.price = price;
		discount = 0.0;
		subtotal = 0.0;
	}

	public String getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getQty() {
		return qty;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getSubtotal() {	
		calculateSubtotal();
		return subtotal;		
	}
	
	private void calculateSubtotal() {
		this.subtotal = price * qty - discount;
	}
	
		
	public void add(int qty) {
		this.qty += qty;
	}
	
	public void remove(int qty) {
		this.qty -= qty;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void setPrice(double price) {
		this.price = price;
		this.discount = 0.0;
	}
	
		
	public String toString() {
		String str = String.format("Product Id: %s, Quantity: %s, Total Price: %s \n", getProductId(), getQty(), getSubtotal());
		return str;
	}
	
	public boolean equals(Object object) {
		boolean equal = false;
		
	    SaleLineItem item = (SaleLineItem)object;
        if (0 == productId.compareTo(item.getProductId())) {
           equal = true;
        }
	    
		
		return equal;
	}
}
