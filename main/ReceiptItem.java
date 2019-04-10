package main;
import java.util.*;
public class ReceiptItem {
	private Product product; 
	private int qty;
	private double price;
	private double subtotal;
	
	public ReceiptItem(Product product, int qty) {
		this.product = product;
		this.qty = qty;
		this.subtotal = 0.0;
	}

	public String getProductId() {
		return product.getId();
	}
	
	public int getQty() {
		return qty;
	}
	
	public double getSubtotal() {
		// needs a products (collection of product) to get the single price of item
		updateSubtotal();
		return subtotal;		
	}
	
	private void updateSubtotal() {
		double subtotal = qty * product.getPrice();
		double discount = calculateDiscount();
		
		
		this.subtotal = subtotal - discount;
		
//		System.out.println("\n\nsubtotal=" + Double.toString(subtotal));
//		System.out.println("discount=" + Double.toString(discount));
//		System.out.println("new subtotal=" + Double.toString(this.subtotal));
	}
	
	private double calculateDiscount() {
		double totaldiscount = 0.0;
		// System.out.println("\ncalculateDiscount()");
		
		
		Set<Integer> keys = product.getDiscounts().keySet();
		
		if(!keys.isEmpty()) {
			int amount = qty;
			double discount = 0.0;
			int discountNum = 0;
			
			
			int bulkqty = amount;
			while(bulkqty != 0) {
				// System.out.println("bulkqty= " + Integer.toString(bulkqty));
				if(product.getDiscounts().containsKey(bulkqty)) {
					discountNum = amount/bulkqty;
					discount = product.getDiscounts().get(bulkqty);
					totaldiscount += discount * discountNum;
					amount = (amount - bulkqty * discountNum);
					bulkqty = amount;
					
//					System.out.println("\tdiscountNum= " + Integer.toString(discountNum));
//					System.out.println("\tdiscount= " + Double.toString(discount));
//					System.out.println("\ttotaldiscount= " + Double.toString(totaldiscount));
//					System.out.println("\tamount= " + Integer.toString(amount));
//					System.out.println("\tbulkqty= " + Integer.toString(bulkqty));
//					System.out.println("\t\tBREAK!!");
					
					continue;
				}
				bulkqty--;
			}				
			
		}
		
		//System.out.println("\nreturn totalDiscount=" + Double.toString(totaldiscount));
		return totaldiscount;
	}
	
	
	public void add(int qty) {
		this.qty += qty;
	}
	
	
	public String toString() {
		String str = String.format("Product Id: %s, Quantity: %s, Total Price: %s \n", getProductId(), getQty(), getSubtotal());
		return str;
	}
	
	public boolean equals(Object object) {
		return product.equals(object);
	}
}
