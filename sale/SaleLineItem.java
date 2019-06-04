package sale;

import warehouse.Product;
public class SaleLineItem {
	private Product product; 
	private int qty;
	double unitprice;
	private double discount;
	private double subtotal;
	
	public SaleLineItem(Product product, int qty) {
		this.product = product;
		this.qty = qty;	
		unitprice = product.getPrice();
		discount = 0.0;
		subtotal = 0.0;
	}

	public String getProductId() {
		return product.getId();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public int getQty() {
		return qty;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public double getUnitPrice() {
		return unitprice;
	}
	
	public double getSubtotal() {	
		calculateSubtotal();
		return subtotal;		
	}
	
	private void calculateSubtotal() {
		this.subtotal = product.getPrice() * qty - discount;
	}
	
		
	public void add(int qty) {
		this.qty += qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void setUnitPrice(double price) {
		this.unitprice = price;
		this.discount = 0.0;
	}
	
		
	public String toString() {
		String str = String.format("Product Id: %s, Quantity: %s, Total Price: %s \n", getProductId(), getQty(), getSubtotal());
		return str;
	}
	
	public boolean equals(Object object) {
		boolean equal = false;
		
	    SaleLineItem item = (SaleLineItem)object;
        if (0 == product.getId().compareTo(item.getProductId())) {
           equal = true;
        }
	    
		
		return equal;
	}
}
